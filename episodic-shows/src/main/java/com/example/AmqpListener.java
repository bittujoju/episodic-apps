package com.example;

import com.example.shows.episodes.Episode;
import com.example.shows.episodes.EpisodeRepository;
import com.example.users.User;
import com.example.users.UserRepository;
import com.example.viewings.Viewing;
import com.example.viewings.ViewingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import queue.EpisodicProgress;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by trainer11 on 5/23/17.
 */
@Configuration
public class AmqpListener implements RabbitListenerConfigurer {

    @Autowired
    ViewingRepository viewingRepository;

    @Autowired
    EpisodeRepository episodeRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    @RabbitListener(queues = "episodic-progress")
    public void receiveMessage(final EpisodicProgress episodicProgress) {
        Viewing viewing = new Viewing();
        List<Viewing> viewings = new ArrayList<>();
        List<Episode> episodes = new ArrayList<>();
        List<User> users = new ArrayList<>();
        Boolean invalid = false;

        final Long showId;

        viewingRepository.findAll().forEach(viewings::add);

        viewings = viewings.stream()
                .filter(v -> (v.getEpisodeId() == episodicProgress.getEpisodeId()) && v.getUserId() == episodicProgress.getUserId() )
                .collect(toList());

        episodeRepository.findAll().forEach(episodes::add);

        episodes = episodes.stream()
                .filter(e -> e.getId() == episodicProgress.getEpisodeId())
                .collect(toList());

        userRepository.findAll().forEach(users::add);

        users = users.stream()
                .filter(u -> (u.getId() == episodicProgress.getUserId()))
                .collect(toList());

        if (episodes.isEmpty() || (users.isEmpty())){
            invalid = true;
            showId = 0L;}
        else {
            showId = episodes.get(0).getShowId();
        }

        if (!invalid) {
            if (!viewings.isEmpty()) {
                viewing.setId(viewings.get(0).getId());
            }
            viewing.setShowId(showId);
            viewing.setEpisodeId(episodicProgress.getEpisodeId());
            viewing.setUserId(episodicProgress.getUserId());
            viewing.setUpdatedAt(episodicProgress.getCreatedAt());
            viewing.setTimecode(episodicProgress.getOffset());
            viewingRepository.save(viewing);
        }
        else
            System.out.println("INVALID USER/SHOW");

    }


    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(new MappingJackson2MessageConverter());
        return factory;
    }

    @Override
    public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

}
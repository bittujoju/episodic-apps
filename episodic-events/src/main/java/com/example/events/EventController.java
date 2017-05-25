package com.example.events;

import com.example.ProgressMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import sun.net.ProgressEvent;

import java.util.Map;

/**
 * Created by trainer11 on 5/22/17.
 */

@RestController
@RequestMapping("/")
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("recent")
    public Page<Event> getEvents() {
        return this.eventRepository.findAll(new PageRequest(0, 20));
    }

    @PostMapping("")
    public Object createProduct(@RequestBody Event event) {

        if(event.getType().equalsIgnoreCase("progress")){
            Progress progressEvent = (Progress)event;

            rabbitTemplate.convertAndSend("my-exchange", "my-routing-key", new ProgressMessage(
                    progressEvent.getUserId(),
                    progressEvent.getEpisodeId(),
                    progressEvent.getCreatedAt(),
                    progressEvent.getData().getOffset()
            ));
        }

        return this.eventRepository.save(event);
    }
}
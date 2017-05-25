package com.example;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by trainer11 on 5/24/17.
 */
@Configuration
public class AmqpPublisher {

    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange("my-exchange");
    }

    @Bean
    public Queue queue() {
        return new Queue("my-publish-queue");
    }

    @Bean
    public Binding declareBinding() {
        return BindingBuilder.bind(queue()).to(appExchange()).with("my-routing-key");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        Jackson2JsonMessageConverter messageConverter = new Jackson2JsonMessageConverter();
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

}
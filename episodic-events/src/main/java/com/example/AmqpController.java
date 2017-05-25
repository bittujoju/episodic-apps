package com.example;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by trainer11 on 5/24/17.
 */
@RestController
@RequestMapping("/amqp")
public class AmqpController {

    private final RabbitTemplate rabbitTemplate;

    public AmqpController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    public void publishEvent(@RequestBody String body){
        rabbitTemplate.convertAndSend("my-exchange", "my-routing-key", new MyCustomMessage(body, 42));
    }

    public static class MyCustomMessage {
        private final String sentence;
        private final int someNumber;

        public MyCustomMessage(String sentence, int someNumber) {
            this.sentence = sentence;
            this.someNumber = someNumber;
        }

        public String getSentence() {
            return sentence;
        }

        public int getSomeNumber() {
            return someNumber;
        }
    }
}
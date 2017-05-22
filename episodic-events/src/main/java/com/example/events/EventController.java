package com.example.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * Created by trainer11 on 5/22/17.
 */

@RestController
@RequestMapping("/")
public class EventController {

    @Autowired
    EventRepository eventRepository;


    @GetMapping("recent")
    public Page<Event> getEvents() {
        return this.eventRepository.findAll(new PageRequest(0, 20));
    }

    @PostMapping("")
    public Object createProduct(@RequestBody Event event) {
        return eventRepository.save(event);
    }


}
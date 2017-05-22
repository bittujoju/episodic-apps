package com.example.events;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by trainer11 on 5/22/17.
 */
public interface EventRepository extends MongoRepository<Event, String> {
    public abstract List<Event> findAll();
}

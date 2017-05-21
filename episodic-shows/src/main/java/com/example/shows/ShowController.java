package com.example.shows;

import com.example.shows.episodes.Episode;
import com.example.shows.episodes.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by trainer11 on 5/18/17.
 */
@RestController
@RequestMapping("/shows")
public class ShowController {

    @Autowired
    ShowRepository showRepository;

    @GetMapping("")
    public Iterable<Show> show() throws Exception{
        return this.showRepository.findAll();
    }

    @PostMapping("")
    public Show create(@RequestBody Show show) throws Exception {
        return this.showRepository.save(show);
    }
}

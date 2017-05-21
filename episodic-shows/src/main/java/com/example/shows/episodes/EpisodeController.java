package com.example.shows.episodes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by trainer11 on 5/18/17.
 */
@RestController
@RequestMapping("/shows")
public class EpisodeController {


    @Autowired
    EpisodeRepository episodeRepository;

    @GetMapping("{id}/episodes")
    public Iterable<EpisodeWithTitle> getEpisodesForShow(@PathVariable Long id) throws Exception{

        List<Episode> episodes = new ArrayList<>();

         episodeRepository.findAll().forEach(episodes::add);

                return episodes.stream()
                                .filter(episode -> episode.getShowId() == id)
                                .map(episode -> {
                                 EpisodeWithTitle episodeWithTitle = new EpisodeWithTitle(episode);
                                 return episodeWithTitle;
                })
                .collect(toList());
    }

    @PostMapping("{id}/episodes")
    public EpisodeWithTitle createEpisode(@RequestBody Episode episode, @PathVariable Long id) throws Exception {
        episode.setShowId(id);
        this.episodeRepository.save(episode);
        EpisodeWithTitle episodeWithTitle = new EpisodeWithTitle(episode);
        return episodeWithTitle;
    }
}

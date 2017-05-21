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
    public Iterable<DetailedEpisode> getEpisodesForShow(@PathVariable Long id) throws Exception{

        List<Episode> episodes = new ArrayList<>();

         episodeRepository.findAll().forEach(episodes::add);

                return episodes.stream()
                                .filter(episode -> episode.getShowId() == id)
                                .map(episode -> {
                                 DetailedEpisode detailedEpisode = new DetailedEpisode(episode);
                                 return detailedEpisode;
                })
                .collect(toList());
    }

    @PostMapping("{id}/episodes")
    public DetailedEpisode createEpisode(@RequestBody Episode episode, @PathVariable Long id) throws Exception {
        episode.setShowId(id);
        this.episodeRepository.save(episode);
        DetailedEpisode detailedEpisode = new DetailedEpisode(episode);
        return detailedEpisode;
    }
}

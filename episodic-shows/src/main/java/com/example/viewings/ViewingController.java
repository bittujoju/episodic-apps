package com.example.viewings;

import com.example.shows.ShowRepository;
import com.example.shows.episodes.Episode;
import com.example.shows.episodes.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;


/**
 * Created by trainer11 on 5/19/17.
 */
@RestController
@RequestMapping("/users")
public class ViewingController {

    @Autowired
    ViewingRepository viewingRepository;

    @Autowired
    EpisodeRepository episodeRepository;

    @Autowired
    ShowRepository showRepository;

    @PatchMapping("{id}/viewings")
    public void updateViewing(@RequestBody Viewing viewing, @PathVariable Long id) throws Exception {

        List<Viewing> viewings = new ArrayList<>();
        List<Episode> episodes = new ArrayList<>();

        viewingRepository.findAll().forEach(viewings::add);
        episodeRepository.findAll().forEach(episodes::add);

        episodes = episodes.stream()
                .filter(e -> e.getId() == viewing.getEpisodeId())
                .collect(toList());
        final Long showId = episodes.get(0).getShowId();


        viewings = viewings.stream()
                .filter(v -> (v.getUserId() == id) && (showId == v.getShowId()) )
                .collect(toList());

        if (!viewings.isEmpty())
            viewing.setId(viewings.get(0).getId());
        viewing.setShowId(showId);
        viewing.setUserId(id);
        viewingRepository.save(viewing);

        return;
    }

    @GetMapping("{id}/recently-watched")
        public List<DetailedViewing> getViewings(@PathVariable Long id) throws Exception {
            List<Viewing> viewings = new ArrayList<>();
            viewingRepository.findAll().forEach(viewings::add);
            return viewings.stream()
                .filter(v -> v.getUserId() == id)
                .map(v -> {
                    DetailedViewing detailedViewing = new DetailedViewing(v, showRepository, episodeRepository);
                    return detailedViewing;
                })
                .collect(toList());
    }
}

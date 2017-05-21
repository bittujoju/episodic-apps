package com.example.viewings;

import com.example.shows.Show;
import com.example.shows.ShowRepository;
import com.example.shows.episodes.Episode;
import com.example.shows.episodes.EpisodeRepository;
import com.example.shows.episodes.EpisodeWithTitle;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

/**
 * Created by trainer11 on 5/19/17.
 */
public class DetailedViewing {

    private Long id;

    private Show show;
    private EpisodeWithTitle episode;
    private Timestamp updatedAt;
    private Integer timecode;

    public DetailedViewing(Viewing viewing, ShowRepository showRepository, EpisodeRepository episodeRepository) {
        this.id = viewing.getId();
        this.show = showRepository.findOne(viewing.getShowId());
        this.episode = new EpisodeWithTitle(episodeRepository.findOne(viewing.getEpisodeId()));
        this.updatedAt = viewing.getUpdatedAt();
        this.timecode = viewing.getTimecode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public EpisodeWithTitle getEpisode() {
        return episode;
    }

    public void setEpisode(EpisodeWithTitle episode) {
        this.episode = episode;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getTimecode() {
        return timecode;
    }

    public void setTimecode(Integer timecode) {
        this.timecode = timecode;
    }
}

package com.example.viewings;

import com.example.shows.Show;
import com.example.shows.ShowRepository;
import com.example.shows.episodes.DetailedEpisode;
import com.example.shows.episodes.Episode;
import com.example.shows.episodes.EpisodeRepository;

import java.sql.Timestamp;

/**
 * Created by trainer11 on 5/19/17.
 */
public class DetailedViewing {

    private Long id;

    private Show show;
    private DetailedEpisode episode;
    private Timestamp updatedAt;
    private Integer timecode;

    public DetailedViewing(Viewing viewing, ShowRepository showRepository, EpisodeRepository episodeRepository) {
        this.id = viewing.getId();
        this.show = showRepository.findOne(viewing.getShowId());
        this.episode = new DetailedEpisode(episodeRepository.findOne(viewing.getEpisodeId()));
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

    public DetailedEpisode getEpisode() {
        return episode;
    }

    public void setEpisode(DetailedEpisode episode) {
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

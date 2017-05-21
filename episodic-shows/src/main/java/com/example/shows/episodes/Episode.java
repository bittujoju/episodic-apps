package com.example.shows.episodes;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by trainer11 on 5/18/17.
 */
@Entity(name = "episodes")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long showId;
    private Integer seasonNumber;
    private Integer episodeNumber;

    public Episode(){};

    public Episode(Long showId, Integer seasonNumber, Integer episodeNumber) {
        this.showId = showId;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }
}


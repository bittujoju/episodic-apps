package com.example.shows.episodes;


/**
 * Created by trainer11 on 5/18/17.
 */

public class DetailedEpisode {


    private Long id;

    private Long showId;
    private Integer seasonNumber;
    private Integer episodeNumber;
    private String title;

    public DetailedEpisode(Episode episode){
        this.id = episode.getId();
        this.showId = episode.getShowId();
        this.seasonNumber = episode.getSeasonNumber();
        this.episodeNumber = episode.getEpisodeNumber();
        this.title = "S" + episode.getSeasonNumber() + " " + "E" + episode.getEpisodeNumber();
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

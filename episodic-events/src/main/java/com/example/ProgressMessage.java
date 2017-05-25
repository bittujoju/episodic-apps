package com.example;

import com.example.events.Event;

/**
 * Created by trainer11 on 5/25/17.
 */
public class ProgressMessage {

    private Long userId;
    private Long episodeId;
    private String createdAt;
    private Integer Offset;

    public ProgressMessage(Long userId, Long episodeId, String createdAt, Integer offset) {
        this.userId = userId;
        this.episodeId = episodeId;
        this.createdAt = createdAt;
        Offset = offset;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(Long episodeId) {
        this.episodeId = episodeId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getOffset() {
        return Offset;
    }

    public void setOffset(Integer offset) {
        Offset = offset;
    }
}

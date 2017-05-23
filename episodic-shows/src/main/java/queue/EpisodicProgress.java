package queue;

import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * Created by trainer11 on 5/23/17.
 */
@NoArgsConstructor
public class EpisodicProgress {

    private Long userId;
    private Long episodeId;
    private Timestamp createdAt;
    private Integer offset;

    public EpisodicProgress(Long userId, Long episodeId, Timestamp createdAt, Integer offset) {
        this.userId = userId;
        this.episodeId = episodeId;
        this.createdAt = createdAt;
        this.offset = offset;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}

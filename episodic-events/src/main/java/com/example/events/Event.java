package com.example.events;

import com.example.data.Data;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;


/**
 * Created by trainer11 on 5/22/17.
 */
@Getter
@Setter
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Event.class, name = "event"),
        @JsonSubTypes.Type(value = Play.class, name = "play"),
        @JsonSubTypes.Type(value = Pause.class, name = "pause"),
        @JsonSubTypes.Type(value = FastForward.class, name = "fastForward"),
        @JsonSubTypes.Type(value = Rewind.class, name = "rewind"),
        @JsonSubTypes.Type(value = Progress.class, name = "progress"),
        @JsonSubTypes.Type(value = Scrub.class, name = "scrub")
})
public abstract class Event {

    @Id
    private String id;

    private Long userId;
    private Long showId;
    private Long episodeId;
    private String createdAt;

    public Event(Long userId, Long showId, Long episodeId, String createdAt) {
        this.userId = userId;
        this.showId = showId;
        this.episodeId = episodeId;
        this.createdAt = createdAt;
    }

    public String getType(){return "event"; };
}


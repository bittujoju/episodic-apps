package com.example.events;

import com.example.data.DetailedData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Created by trainer11 on 5/22/17.
 */
@Getter
@Setter
@NoArgsConstructor
public class Rewind extends Event {

    private DetailedData data;

    public Rewind(DetailedData data) {
        this.data = data;
    }

    public Rewind(Long userId, Long showId, Long episodeId, String createdAt, DetailedData data) {
        super(userId, showId, episodeId, createdAt);
        this.data = data;
    }

    public String getType() {
        return "rewind";
    }
}
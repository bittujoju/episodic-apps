package com.example.events;

import com.example.data.DataWithOffset;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by trainer11 on 5/22/17.
 */
@Getter
@Setter
@NoArgsConstructor
public class Scrub extends Event {

    private DataWithOffset data;

    public Scrub(DataWithOffset data) {
        this.data = data;
    }

    public Scrub(Long userId, Long showId, Long episodeId, String createdAt, DataWithOffset data) {
        super(userId, showId, episodeId, createdAt);
        this.data = data;
    }

    public String getType() {
        return "scrub";
    }

}
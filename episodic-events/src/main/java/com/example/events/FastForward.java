package com.example.events;

import com.example.data.DataWithSpeed;
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
public class FastForward extends Event {

    private DataWithSpeed data;

    public String getType() {
        return "fastForward";
    }

    public FastForward(DataWithSpeed data) {
        this.data = data;
    }

    public FastForward(Long userId, Long showId, Long episodeId, String createdAt, DataWithSpeed data) {

        super(userId, showId, episodeId, createdAt);
        this.data = data;
    }
}

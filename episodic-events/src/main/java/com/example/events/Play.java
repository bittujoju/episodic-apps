package com.example.events;

import com.example.data.Data;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

/**
 * Created by trainer11 on 5/22/17.
 */
@Getter
@Setter
@NoArgsConstructor
public class Play extends Event {

    private Data data;

    public Play(Long userId, Long showId, Long episodeId, String createdAt, Data data) {
        super(userId, showId, episodeId, createdAt);
        this.data = data;
    }

    public Play(Data data) {
        this.data = data;
    }

    public String getType() {
        return "play";
    }
}

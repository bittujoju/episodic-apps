package com.example.events;

import com.example.data.Data;
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
public class Progress extends Event {

    private Data data;

    public Progress(Long userId, Long showId, Long episodeId, String createdAt, Data data) {
        super(userId, showId, episodeId, createdAt);
        this.data = data;
    }

    public Progress(Data data) {
        this.data = data;
    }

    public String getType() {
        return "progress";
    }
}

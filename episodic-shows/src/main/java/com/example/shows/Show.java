package com.example.shows;

/**
 * Created by trainer11 on 5/18/17.
 */


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity(name = "shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.example.users;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by trainer11 on 5/17/17.
 */

@Entity(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
}

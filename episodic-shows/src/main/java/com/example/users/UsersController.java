package com.example.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by trainer11 on 5/17/17.
 */

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    public Iterable<User> show() throws Exception{
        return this.userRepository.findAll();
    }

    @PostMapping("")
    public User create(@RequestBody User user) throws Exception {
        return this.userRepository.save(user);
    }


}

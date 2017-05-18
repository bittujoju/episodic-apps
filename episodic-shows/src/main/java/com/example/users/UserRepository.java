package com.example.users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by trainer11 on 5/17/17.
 */
public interface UserRepository extends CrudRepository<User, Long> {

}

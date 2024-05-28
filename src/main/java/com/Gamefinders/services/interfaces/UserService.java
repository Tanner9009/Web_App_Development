package com.Gamefinders.services.interfaces;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.Gamefinders.domain.classes.User;
import java.util.List;

public interface UserService extends MongoRepository<User, String>{
    
    @SuppressWarnings("null")
    List<User> findAll();
    User findByUsername();
}
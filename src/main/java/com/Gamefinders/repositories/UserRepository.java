package com.Gamefinders.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.Gamefinders.domain.classes.User;

public interface UserRepository extends MongoRepository<User, String>{    
    User findByUsername(String username);
}

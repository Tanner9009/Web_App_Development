package com.Gamefinders.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.Gamefinders.domain.classes.User;

public interface UserRepository extends MongoRepository<User, String>{
    
    @Query("{username: '?$0'}")
    User findByUsername(String username);
}

package com.Gamefinders.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.Gamefinders.domain.classes.Review;
import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String>{

    List<Review> findByAuthorUsername(String authorUsername);
    List<Review> findByGameId(String gameId);
}      
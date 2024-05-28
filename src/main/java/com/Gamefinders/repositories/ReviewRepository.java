package com.Gamefinders.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.Gamefinders.domain.classes.BoardGame;
import com.Gamefinders.domain.classes.Review;
import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String>{

    @SuppressWarnings("null")
    List<Review> findAll();
    List<Review> findByAuthorId(String authorId);
    List<BoardGame> findByUserScoreGreaterThan(float userScore);
    List<BoardGame> findByUserScoreLessThan(float userScore);

}      
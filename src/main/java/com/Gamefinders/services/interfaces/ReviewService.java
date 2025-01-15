package com.Gamefinders.services.interfaces;

import com.Gamefinders.domain.classes.Review;
import java.util.List;

public interface ReviewService {
    List<Review> findAll();
    void save(Review review);
    List<Review> findByAuthorUsername(String authorUsername);
    List<Review> findByGameId(String gameId);
}
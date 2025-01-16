package com.Gamefinders.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Gamefinders.domain.classes.Review;
import com.Gamefinders.repositories.ReviewRepository;
import com.Gamefinders.services.interfaces.ReviewService;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public void save(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public List<Review> findByAuthorUsername(String authorUsername) {
        return reviewRepository.findByAuthorUsername(authorUsername);
    }

    @Override
    public List<Review> findByGameId(String gameId) {
        return reviewRepository.findByGameId(gameId);
    }

    @Override
    public void deleteById(String reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
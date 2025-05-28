package com.pscode.nourish_now.service;

import com.pscode.nourish_now.entity.Review;
import com.pscode.nourish_now.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }
    public Review findById(Long id) {
        return reviewRepository.findReviewById(id);
    }

    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    public Review deleteReviewById(Long reviewId) {
        return reviewRepository.deleteReviewById(reviewId);
    }
}

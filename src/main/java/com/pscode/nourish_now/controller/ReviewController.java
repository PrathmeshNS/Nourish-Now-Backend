package com.pscode.nourish_now.controller;

import com.pscode.nourish_now.entity.Review;
import com.pscode.nourish_now.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/save-review")
    public ResponseEntity<?> saveReview(Review review) {
        Review review1 = reviewService.save(review);
        if (review1 != null) {
            return new ResponseEntity<>("Data Inserted Successfully!!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something Went Wrong!!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/delete/{review-id}")
    public ResponseEntity<?> deleteReview(@PathVariable("review-id") Long reviewId) {
        Review review = reviewService.deleteReviewById(reviewId);
        if (review != null) {
            return new ResponseEntity<>("Data Deleted Successfully!!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something Went Wrong!!", HttpStatus.NOT_FOUND);
    }

    @GetMapping("get-all-review")
    public ResponseEntity<?> findAllReview() {
        return ResponseEntity.ok(reviewService.findAll());
    }

    @GetMapping("review-id")
    public ResponseEntity<?> findReviewById(@RequestParam("review-id") Long reviewId) {
        return ResponseEntity.ok(reviewService.findById(reviewId));
    }
}

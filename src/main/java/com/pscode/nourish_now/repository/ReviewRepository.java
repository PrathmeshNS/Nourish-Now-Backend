package com.pscode.nourish_now.repository;

import com.pscode.nourish_now.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("SELECT r FROM Review r WHERE r.reviewId = :id")
    Review findReviewById(@Param("id") Long reviewId);

    @Modifying
    @Query("DELETE FROM Review r WHERE r.reviewId = :id")
    Review deleteReviewById(Long reviewId);
}

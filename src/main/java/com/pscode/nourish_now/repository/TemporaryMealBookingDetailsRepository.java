package com.pscode.nourish_now.repository;

import com.pscode.nourish_now.entity.TemporaryMealBookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface TemporaryMealBookingDetailsRepository extends JpaRepository<TemporaryMealBookingDetails, Long> {
}

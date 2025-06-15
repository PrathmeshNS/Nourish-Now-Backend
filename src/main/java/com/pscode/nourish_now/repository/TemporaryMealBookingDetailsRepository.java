package com.pscode.nourish_now.repository;

import com.pscode.nourish_now.entity.TemporaryMealBookingDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
@Transactional
public interface TemporaryMealBookingDetailsRepository extends JpaRepository<TemporaryMealBookingDetails, Long> {

    @Query("SELECT a FROM TemporaryMealBookingDetails a WHERE a.tmdId = ?1 ")
    TemporaryMealBookingDetails findByTmdId(Long tmdId);


    @Modifying
    @Query("DELETE FROM TemporaryMealBookingDetails a WHERE a.tmdId = ?1 ")
    int deleteByTmdId(Long tmdId);
}

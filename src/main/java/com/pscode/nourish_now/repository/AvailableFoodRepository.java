package com.pscode.nourish_now.repository;

import com.pscode.nourish_now.entity.AvailableFood;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
@EnableJpaRepositories
public interface AvailableFoodRepository extends JpaRepository<AvailableFood, Long> {

    @Modifying
    @Query("DELETE FROM AvailableFood WHERE aId =?1")
    int deleteByFoodId(long foodId);

    @Query("SELECT a FROM AvailableFood a WHERE a.aId = ?1")
    AvailableFood findfoodById(int aId);
}

package com.pscode.nourish_now.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pscode.nourish_now.entity.AvailableFood;

public interface AvailableFoodRepository extends JpaRepository<AvailableFood, Long> {

//	@Query("SELECT a FROM a")
//	AvailableFood findById()
}

package com.pscode.nourish_now.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.pscode.nourish_now.entity.History;



@Repository
@EnableJpaRepositories
public interface HistoryRepository extends JpaRepository<History, Long> {

	
	@Query("SELECT h FROM History h WHERE h.hId = :hId")
	List<History> findByHId(Long hId);
	
	
}

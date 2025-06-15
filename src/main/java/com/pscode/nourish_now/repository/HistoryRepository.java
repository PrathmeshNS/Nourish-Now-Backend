package com.pscode.nourish_now.repository;

import com.pscode.nourish_now.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@EnableJpaRepositories
public interface HistoryRepository extends JpaRepository<History, Long> {


    @Query("SELECT h FROM History h WHERE h.hId = :hId")
    History findByHId(Long hId);


}

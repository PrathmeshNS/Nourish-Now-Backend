package com.pscode.nourish_now.repository;

import com.pscode.nourish_now.entity.UserProfile;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface ProfileRepository extends JpaRepository<UserProfile, Long> {

    @Query("SELECT p FROM UserProfile p WHERE p.profileName = :name")
    UserProfile findByName(@Param("name") String profileName);
}

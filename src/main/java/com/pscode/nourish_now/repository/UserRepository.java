package com.pscode.nourish_now.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pscode.nourish_now.entity.Users;
import com.pscode.nourish_now.enums.ReviewStatus;
import com.pscode.nourish_now.utility.PreDefineMessage;

import jakarta.transaction.Transactional;


@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<Users, Long> {

	
	
	@Query(PreDefineMessage.FIND_USER_BY_EMAIL)
	Users findByEmail(@Param("email")String email);

	
	@Modifying
	@Transactional
	@Query(PreDefineMessage.UPDATE_USER_STATUS_BY_ID)
	int approveUser(@Param("status") ReviewStatus status,  @Param("id") Long id);
	
	@Query(PreDefineMessage.FIND_USER_BY_ID)
	Users findByUserId(@Param("id") Long id);

}

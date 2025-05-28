package com.pscode.nourish_now.repository;

import com.pscode.nourish_now.entity.Donation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


@Repository
@EnableJpaRepositories(basePackageClasses = DonationRepository.class)
@Transactional
public interface DonationRepository extends JpaRepository<Donation, Long> {
}

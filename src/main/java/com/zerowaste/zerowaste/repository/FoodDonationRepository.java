package com.zerowaste.zerowaste.repository;

import com.zerowaste.zerowaste.entity.FoodDonation;
import com.zerowaste.zerowaste.enums.DonationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodDonationRepository extends JpaRepository<FoodDonation, Long> {

    List<FoodDonation> findByStatus(DonationStatus status);
    long countByStatus(DonationStatus status);
    Page<FoodDonation> findByStatus(DonationStatus status, Pageable pageable);
}
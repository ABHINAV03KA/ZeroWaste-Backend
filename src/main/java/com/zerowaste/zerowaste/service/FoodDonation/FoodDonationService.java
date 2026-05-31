package com.zerowaste.zerowaste.service.FoodDonation;


import com.zerowaste.zerowaste.dto.DashboardStats.Response.DashboardStatsResponse;
import com.zerowaste.zerowaste.dto.FoodDonation.Request.FoodDonationRequest;
import com.zerowaste.zerowaste.entity.FoodDonation;
import com.zerowaste.zerowaste.enums.DonationStatus;
import com.zerowaste.zerowaste.exception.DonationNotFoundException;
import com.zerowaste.zerowaste.repository.FoodDonationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodDonationService {

    private final FoodDonationRepository foodDonationRepository;

    public String createDonation(FoodDonationRequest request) {

        FoodDonation donation = FoodDonation.builder()
                .foodName(request.getFoodName())
                .quantity(request.getQuantity())
                .description(request.getDescription())
                .pickupAddress(request.getPickupAddress())
                .expiryTime(request.getExpiryTime())
                .status(DonationStatus.AVAILABLE)
                .build();

        foodDonationRepository.save(donation);

        return "Food donation created successfully";
    }

    public List<FoodDonation> getAvailableDonations() {
        return foodDonationRepository.findByStatus(DonationStatus.AVAILABLE);
    }
    public String claimDonation(Long id) {

        FoodDonation donation = foodDonationRepository.findById(id)
                .orElseThrow(() ->
                        new DonationNotFoundException("Donation not found")
                );

        if (donation.getStatus() != DonationStatus.AVAILABLE) {
            throw new RuntimeException("Donation already claimed");
        }

        donation.setStatus(DonationStatus.CLAIMED);

        foodDonationRepository.save(donation);

        return "Donation claimed successfully";
    }
    public String markAsDelivered(Long id) {

        FoodDonation donation = foodDonationRepository.findById(id)
                .orElseThrow(() ->
                        new DonationNotFoundException("Donation not found")
                );

        if (donation.getStatus() != DonationStatus.CLAIMED) {
            throw new RuntimeException("Only claimed donations can be delivered");
        }

        donation.setStatus(DonationStatus.DELIVERED);

        foodDonationRepository.save(donation);

        return "Donation marked as delivered";
    }
    public List<FoodDonation> getAllDonations() {
        return foodDonationRepository.findAll();
    }
    public List<FoodDonation> getClaimedDonations() {
        return foodDonationRepository.findByStatus(DonationStatus.CLAIMED);
    }
    public DashboardStatsResponse getDashboardStats() {

        long totalDonations = foodDonationRepository.count();

        long availableDonations =
                foodDonationRepository.countByStatus(DonationStatus.AVAILABLE);

        long claimedDonations =
                foodDonationRepository.countByStatus(DonationStatus.CLAIMED);

        long deliveredDonations =
                foodDonationRepository.countByStatus(DonationStatus.DELIVERED);

        return new DashboardStatsResponse(
                totalDonations,
                availableDonations,
                claimedDonations,
                deliveredDonations
        );
    }
    public Page<FoodDonation> getAllDonations(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return foodDonationRepository.findAll(pageable);
    }
    public Page<FoodDonation> getAvailableDonations(
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return foodDonationRepository.findByStatus(
                DonationStatus.AVAILABLE,
                pageable
        );
    }

    public Page<FoodDonation> getClaimedDonations(
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return foodDonationRepository.findByStatus(
                DonationStatus.CLAIMED,
                pageable
        );
    }

}
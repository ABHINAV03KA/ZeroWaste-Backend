package com.zerowaste.zerowaste.controller;

import com.zerowaste.zerowaste.dto.DashboardStats.Response.DashboardStatsResponse;
import com.zerowaste.zerowaste.dto.FoodDonation.Request.FoodDonationRequest;
import com.zerowaste.zerowaste.entity.FoodDonation;
import com.zerowaste.zerowaste.service.FoodDonation.FoodDonationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
@RequiredArgsConstructor
public class FoodDonationController {

    private final FoodDonationService foodDonationService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_HOTEL')")
    public String createDonation(
            @Valid @RequestBody FoodDonationRequest request
    ) {
        return foodDonationService.createDonation(request);
    }
    @GetMapping
    public Page<FoodDonation> getAvailableDonations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size
    ) {
        return foodDonationService.getAvailableDonations(page, size);
    }

    @PutMapping("/{id}/claim")
    @PreAuthorize("hasAuthority('ROLE_NGO')")
    public String claimDonation(@PathVariable Long id) {
        return foodDonationService.claimDonation(id);
    }

    @PutMapping("/{id}/delivered")
        @PreAuthorize("hasAuthority('ROLE_VOLUNTEER')")
    public String markAsDelivered(@PathVariable Long id) {
        return foodDonationService.markAsDelivered(id);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Page<FoodDonation> getAllDonations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size
    ) {
        return foodDonationService.getAllDonations(page, size);
    }

    @GetMapping("/claimed")
    @PreAuthorize("hasAuthority('ROLE_VOLUNTEER')")
    public Page<FoodDonation> getClaimedDonations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size
    ) {
        return foodDonationService.getClaimedDonations(page, size);
    }
    @GetMapping("/stats")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public DashboardStatsResponse getDashboardStats() {
        return foodDonationService.getDashboardStats();
    }

}
package com.zerowaste.zerowaste.dto.DashboardStats.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DashboardStatsResponse {

    private long totalDonations;
    private long availableDonations;
    private long claimedDonations;
    private long deliveredDonations;

}
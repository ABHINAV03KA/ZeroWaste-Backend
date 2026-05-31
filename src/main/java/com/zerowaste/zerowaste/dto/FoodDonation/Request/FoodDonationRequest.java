package com.zerowaste.zerowaste.dto.FoodDonation.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FoodDonationRequest {

    @NotBlank(message = "Food name is required")
    private String foodName;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than 0")
    private Integer quantity;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Pickup address is required")
    private String pickupAddress;

    @NotNull(message = "Expiry time is required")
    private LocalDateTime expiryTime;

}
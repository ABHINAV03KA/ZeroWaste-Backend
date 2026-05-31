package com.zerowaste.zerowaste.entity;

import com.zerowaste.zerowaste.enums.DonationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "food_donations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodDonation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String foodName;

    private Integer quantity;

    private String description;

    private String pickupAddress;

    private LocalDateTime expiryTime;

    @Enumerated(EnumType.STRING)
    private DonationStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
package com.zerowaste.zerowaste.entity;


import com.zerowaste.zerowaste.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String phone;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean verified = false;

    private LocalDateTime createdAt = LocalDateTime.now();

}
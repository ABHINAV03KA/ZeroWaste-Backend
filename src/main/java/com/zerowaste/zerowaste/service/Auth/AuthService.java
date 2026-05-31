package com.zerowaste.zerowaste.service.Auth;

import com.zerowaste.zerowaste.dto.RegisterRequest;
import com.zerowaste.zerowaste.dto.auth.Request.LoginRequest;
import com.zerowaste.zerowaste.dto.auth.Response.AuthResponse;
import com.zerowaste.zerowaste.entity.User;
import com.zerowaste.zerowaste.repository.UserRepository;
import com.zerowaste.zerowaste.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already registered";
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .address(request.getAddress())
                .role(request.getRole())
                .verified(false)
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return new AuthResponse(token, "Login successful", user.getRole().name());
    }
}
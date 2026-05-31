package com.zerowaste.zerowaste.dto.auth.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponse {

    private String token;
    private String message;
    private String role;

}
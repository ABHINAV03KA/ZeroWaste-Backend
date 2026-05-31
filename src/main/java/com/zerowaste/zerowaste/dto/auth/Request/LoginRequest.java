package com.zerowaste.zerowaste.dto.auth.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String email;
    private String password;

}
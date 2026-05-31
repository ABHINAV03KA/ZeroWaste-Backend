package com.zerowaste.zerowaste.dto;

import com.zerowaste.zerowaste.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private Role role;

}
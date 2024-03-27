package com.ewertonilima.authuser.dtos;

import javax.validation.constraints.NotBlank;

public class LoginDto {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

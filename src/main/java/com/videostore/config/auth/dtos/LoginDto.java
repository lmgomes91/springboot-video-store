package com.videostore.config.auth.dtos;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginDto {
    private String email;
    private String password;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken convert() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

}

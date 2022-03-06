package com.videostore.config.auth.controllers;

import com.videostore.config.auth.dtos.LoginDto;
import com.videostore.config.auth.dtos.TokenDto;
import com.videostore.config.crypto.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<Object> auth(@RequestBody @Valid LoginDto loginDto){
        UsernamePasswordAuthenticationToken loginData = loginDto.convert();
        try {
            Authentication authenticate = authenticationManager.authenticate(loginData);
            String token = tokenService.generateToken(authenticate);

            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        }catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }
    }
}

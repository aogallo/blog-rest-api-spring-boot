package com.aogallo.blog.controllers;

import com.aogallo.blog.domain.dtos.AuthResponse;
import com.aogallo.blog.domain.dtos.LoginRequest;
import com.aogallo.blog.services.interfaces.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth/login")
@RequiredArgsConstructor
public class AuthController {
    private final IAuthenticationService authenticationService;

    //    @PostMapping(path = "/login")
    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        UserDetails user = authenticationService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
         authenticationService.generateToken(user);
         String token = authenticationService.generateToken(user);
         AuthResponse authResponse =  AuthResponse
                 .builder()
                 .token(token)
                 .expiresIn(86400)
                 .build();

         return ResponseEntity.ok(authResponse);
    }
}

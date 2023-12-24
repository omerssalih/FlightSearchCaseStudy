package com.example.flightSearch.controller;


import com.example.flightSearch.security.AuthenticationRequest;
import com.example.flightSearch.security.AuthenticationResponse;
import com.example.flightSearch.service.AuthenticationService;
import com.example.flightSearch.service.entity.UserCreateRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;}

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest authenticationRequest){
        return authenticationService.login(authenticationRequest);
    }

    @PostMapping("/register")
    public AuthenticationResponse register(@RequestBody UserCreateRequest userCreateRequest){
        return authenticationService.register(userCreateRequest);
    }

}
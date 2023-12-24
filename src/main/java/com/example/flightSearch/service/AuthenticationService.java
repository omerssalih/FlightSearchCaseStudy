package com.example.flightSearch.service;


import com.example.flightSearch.model.Role;
import com.example.flightSearch.model.User;
import com.example.flightSearch.repository.UserRepository;
import com.example.flightSearch.security.AuthenticationRequest;
import com.example.flightSearch.security.AuthenticationResponse;
import com.example.flightSearch.service.entity.UserCreateRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImplementation userDetailService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(JwtService jwtService,
                                 UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 UserDetailsServiceImplementation userDetailService,
                                 AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailService = userDetailService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
        );



        //TODO
        // USER NOT FOUND EXCEPTION
        User user = userRepository.findByEmail(authenticationRequest.getEmail());
        UserDetails userDetails = userDetailService.loadUserByUsername(user.getEmail());
        String token = jwtService.generateToken(userDetails);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse register(UserCreateRequest userCreateRequest) {

        User newUser = User.builder()
                .email(userCreateRequest.getEmail())
                .password(passwordEncoder.encode(userCreateRequest.getPassword()))
                .name(userCreateRequest.getName())
                .surname(userCreateRequest.getSurname())
                .role(Role.USER)
                .build();

        UserDetails user = userDetailService.loadUserByUsername(userRepository.save(newUser).getEmail());
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
}
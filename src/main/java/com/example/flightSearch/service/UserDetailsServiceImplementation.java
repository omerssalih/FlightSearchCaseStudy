package com.example.flightSearch.service;

import com.example.flightSearch.security.JwtUser;
import com.example.flightSearch.model.User;
import com.example.flightSearch.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        return new JwtUser(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getRole().getAuthorities()
        );
    }

}

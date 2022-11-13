package com.example.dipdip.security;

import com.example.dipdip.model.Client;
import com.example.dipdip.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    ClientRepo clientRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var client = clientRepo.findByName(username);
        if (client == null) {
            throw new UsernameNotFoundException("Unknown user: " + username);
        }

        return User.builder()
                .username(client.getName())
                .password(client.getPassword())
                .authorities(client.getRole())
                .build();
    }
}

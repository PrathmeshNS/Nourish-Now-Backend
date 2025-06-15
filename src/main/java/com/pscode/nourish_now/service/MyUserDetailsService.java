package com.pscode.nourish_now.service;

import com.pscode.nourish_now.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users users = service.fetchByUsername(username);
        return User.builder()
                .username(users.getEmail())
                .password(users.getPassword())
                .roles(users.getRole().toString())
                .build();
    }

}

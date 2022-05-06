package com.example.foodsearchapp.service;

import com.example.foodsearchapp.model.User;
import com.example.foodsearchapp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    User user;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with this username not found: " + username));
        return UserDetailsImpl.build(user);
    }
}

package com.jdbl_12_oauth.oauth2.service;

import com.jdbl_12_oauth.oauth2.model.User;
import com.jdbl_12_oauth.oauth2.model.UserRequest;
import com.jdbl_12_oauth.oauth2.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service

public class UserService implements UserManager {

   @Autowired
   UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {

        return userRepo.findByUserName(user)
                .orElseThrow(()-> new UsernameNotFoundException("user does not found"));

    }
}

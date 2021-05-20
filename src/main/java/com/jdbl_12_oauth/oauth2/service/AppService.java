package com.jdbl_12_oauth.oauth2.service;

import com.jdbl_12_oauth.oauth2.model.Role;
import com.jdbl_12_oauth.oauth2.model.User;
import com.jdbl_12_oauth.oauth2.model.UserRequest;
import com.jdbl_12_oauth.oauth2.repository.RoleRepo;
import com.jdbl_12_oauth.oauth2.repository.UserRepo;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
@AllArgsConstructor
@NoArgsConstructor
@Getter

public class AppService {


    @Autowired
    UserManager userManager;
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    User myuser=null;
    public  User signup(UserRequest userRequest)  throws Exception
    {
        String myuser= userRequest.getUserName();
        if(userRepo.findByUserName(myuser).isPresent())
        {
            throw new Exception(" user already exist");
        }

        Role myrole=  null;
        if(roleRepo.findByRole("user").isPresent())
        {
            myrole= roleRepo.findByRole("user").get();

        }
        else {
            myrole = Role.builder().role("user").build();
        }

         User my= User.builder().userName(userRequest.getUserName())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .roles(Arrays.asList(myrole))
                 .isAccountNonExpired(true)
                 .isAccountNonLocked(true)
                 .isEnabled(true)
                 .isCredentialsNonExpired(true)

                .build();
        userRepo.save(
             my
        );


         return my;

    }





}

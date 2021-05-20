package com.jdbl_12_oauth.oauth2.api;

import com.jdbl_12_oauth.oauth2.model.User;
import com.jdbl_12_oauth.oauth2.model.UserRequest;
import com.jdbl_12_oauth.oauth2.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;

@RestController
public class Api {
   @Autowired
    AppService appService;
    @GetMapping("/home")
    @PreAuthorize(value = "isAuthenticated()")
    public  String  homepage()
    {
        return "welcome to the home page";

    }
    @GetMapping("/secured")
    @PreAuthorize(value = "hasAnyRole('admin')")
    public  String  secured()
    {
        return " you are at secured page";
    }


     User x=null;
    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody UserRequest userRequest)
    {
        try
        {
            x= appService.signup(userRequest);
            return ResponseEntity.ok(x);

        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(e);
        }
    }




}

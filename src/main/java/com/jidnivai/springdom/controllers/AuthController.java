package com.jidnivai.springdom.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jidnivai.springdom.dto.LoginRequest;
import com.jidnivai.springdom.dto.UserSignupDto;
import com.jidnivai.springdom.service.AuthService;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/auth")
public class AuthController {


   

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(
            @RequestBody UserSignupDto signUpRequest,
            @RequestParam(required = false) MultipartFile profilePicture) {
        return authService.registerUser(signUpRequest, profilePicture);
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        return authService.logoutUser();
    }

    @GetMapping("/echo")
    public String echo(@RequestParam String param) {
        // System.out.println(param);
        return new String("Echo: " + param);
    }
    
    @GetMapping("/emailAvailable")
    public Boolean emailAvailable(@RequestParam String email) {
        return !authService.existsByEmail(email);
    }
    
    @GetMapping("/usernameAvailable")
    public Boolean usernameAvailable(@RequestParam String username) {
        return !authService.existsByUsername(username);
    }
}

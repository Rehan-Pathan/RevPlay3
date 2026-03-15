package com.revplay.userservice.controller;

import com.revplay.userservice.entity.User;
import com.revplay.userservice.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        authService.register(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String,String> data) {

        String token = authService.login(
                data.get("username"),
                data.get("password")
        );

        return ResponseEntity.ok(token);
    }
}
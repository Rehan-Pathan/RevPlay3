package com.revplay.userservice.controller;

import com.revplay.userservice.entity.User;
import com.revplay.userservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public User me(Authentication auth){

        return userService.getUser(auth.getName());

    }

    @PostMapping("/upload-profile")
    public User uploadProfile(
            Authentication auth,
            @RequestParam("file") MultipartFile file
    ) throws Exception {

        return userService.uploadProfileImage(auth.getName(), file);
    }
}
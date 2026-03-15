package com.revplay.userservice.service;

import com.revplay.userservice.entity.User;
import com.revplay.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final String uploadDir =
            System.getProperty("user.dir") + "/services/user-service/uploads/profile/";

    public User getUser(String username){

        return userRepository.findByUsername(username)
                .orElseThrow();

    }

    public User uploadProfileImage(String username, MultipartFile file) throws IOException {

        User user = userRepository.findByUsername(username)
                .orElseThrow();

        String filename = username + "_" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Paths.get(uploadDir + filename);

        Files.createDirectories(path.getParent());

        Files.write(path, file.getBytes());

        user.setProfileImagePath("/uploads/profile/" + filename);

        return userRepository.save(user);
    }
}
package com.github.codergate.controllers;

import com.github.codergate.dto.controller.UserRequest;
import com.github.codergate.dto.controller.UserResponse;
import com.github.codergate.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/getUserDetails")
    public String getUser(@RequestParam("githubAccessToken") String githubAccessToken) throws IOException {
        String accessToken = githubAccessToken;
        return userService.getUserDetails(accessToken);
    }
}

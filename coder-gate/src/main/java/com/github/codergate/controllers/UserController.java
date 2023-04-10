package com.github.codergate.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.codergate.services.UserService;

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

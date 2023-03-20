package com.github.codergate.controllers;

import com.github.codergate.dto.controller.UserRequest;
import com.github.codergate.dto.controller.UserResponse;
import com.github.codergate.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/getUserDetails")
    public ResponseEntity<UserResponse> getUser(@RequestBody UserRequest userRequest) {
        LOGGER.debug("getUserImage : Entering the method");
        UserResponse userResponse = userService.getUserResponse(userRequest.getUserName());
        LOGGER.debug("getUserImage : Exiting the method");
        return ResponseEntity.ok(userResponse);
    }
}

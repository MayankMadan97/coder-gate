package com.github.codergate.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.codergate.dto.controller.RepositoryResponse;
import com.github.codergate.services.RepositoryService;

@RestController
public class RepositoryController {
    @Autowired
    RepositoryService repositoryService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryController.class);

    @GetMapping("/getRepositories/{userId}")
    public ResponseEntity<RepositoryResponse> fetchRepositories(@PathVariable String userId) {
        LOGGER.debug("fetchRepositories : Entering the method");
        RepositoryResponse repositoryResponse = new RepositoryResponse();
        if (userId != null && !userId.trim().isEmpty()) {
            repositoryResponse = repositoryService.getRepositoryResponse(Long.parseLong(userId));
        }
        LOGGER.debug("fetchRepositories : Exiting the method");
        return ResponseEntity.ok(repositoryResponse);
    }
}

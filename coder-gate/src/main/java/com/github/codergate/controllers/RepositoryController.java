package com.github.codergate.controllers;

import com.github.codergate.dto.controller.UserRequest;
import com.github.codergate.dto.installation.RepositoriesAddedDTO;
import com.github.codergate.services.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RepositoryController {
    @Autowired
    RepositoryService repositoryService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryController.class);

    @GetMapping("/getRepositories")
    public ResponseEntity<List<RepositoriesAddedDTO>> fetchRepositories(@RequestBody UserRequest userRequest) {
        LOGGER.debug("fetchRepositories : Entering the method");
        List <RepositoriesAddedDTO> repositoriesAddedDto = new ArrayList<>();
        if (userRequest.getUserId() != null && !userRequest.getUserId().isEmpty()) {
            repositoriesAddedDto =repositoryService.getRepositoryFromUserId(Long.parseLong(userRequest.getUserId()));
        }
        LOGGER.debug("fetchRepositories : Exiting the method");
        return ResponseEntity.ok(repositoriesAddedDto);
    }
}

package com.github.codergate.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.github.codergate.services.AnalysisService;
import com.github.codergate.services.EventService;

@RestController
public class DashboardController {

    @Autowired
    EventService eventService;

    @Autowired
    AnalysisService analysisService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);

    @GetMapping("/getCollaborators/{userId}")
    public ResponseEntity<Integer> fetchCollaborators(@PathVariable("userId") String userId) {
        LOGGER.debug("fetchCollaborators : Entering the method");
        Integer collaborators = eventService.getCollaborators(userId);
        return ResponseEntity.ok(collaborators);
    }

    @GetMapping("/getCodeScans/{userId}")
    public ResponseEntity<Integer> fetchCodeScans(@PathVariable("userId") String userId) {
        LOGGER.debug("fetchCollaborators : Entering the method");
        Integer codeScans = eventService.getCodeScans(userId);
        return ResponseEntity.ok(codeScans);
    }


}

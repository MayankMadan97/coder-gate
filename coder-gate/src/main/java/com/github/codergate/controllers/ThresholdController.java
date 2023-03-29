package com.github.codergate.controllers;

import com.github.codergate.dto.threshold.ThresholdDTO;
import com.github.codergate.services.ThresholdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ThresholdController {

    @Autowired
    ThresholdService thresholdService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ThresholdController.class);

    @PostMapping("/threshold/{repoID}")
    public ResponseEntity<ThresholdDTO> addThresholdValues(@RequestBody ThresholdDTO thresholdRequest, @PathVariable int repoID) {

        if(thresholdRequest != null && repoID != 0)
        {
            return ResponseEntity.ok(thresholdService.addThreshold(thresholdRequest, repoID));
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
    }

    @GetMapping("/threshold/{repoID}")
    public ResponseEntity<ThresholdDTO> getThresholdValues(@PathVariable int repoID) {
        LOGGER.info("Getting Threshold values for repository with ID {}", repoID);
        return ResponseEntity.ok(thresholdService.getThresholdByID(repoID));
    }
}

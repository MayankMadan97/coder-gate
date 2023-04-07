package com.github.codergate.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.codergate.dto.insight.TimeStampDTO;
import com.github.codergate.services.BranchService;
import com.github.codergate.services.InsightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BranchController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BranchController.class);

    @Autowired
    BranchService branchService;

    @GetMapping("/getBranches/{repoId}")
    public ResponseEntity<List<String>> fetchTimeStampInsights(@PathVariable("repoId") String repoId) {
        LOGGER.debug("fetchTimeStampInsights : Entering the method");
        List<String> output = branchService.getBranchesByRepoId(repoId);
        return ResponseEntity.ok(output);
    }

}

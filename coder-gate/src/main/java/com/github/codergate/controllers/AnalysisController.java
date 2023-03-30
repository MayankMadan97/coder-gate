package com.github.codergate.controllers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.codergate.entities.AnalysisEntity;
import com.github.codergate.services.AnalysisService;

@RestController
public class AnalysisController {

    @Autowired
    AnalysisService analysisService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalysisController.class);

    @PostMapping(value = "/analysis/{repo}/{branch}")
    public ResponseEntity<AnalysisEntity> convertXmlToJson(@RequestParam("file") MultipartFile file,
            @PathVariable String repo,
            @PathVariable String branch) throws NumberFormatException, IOException {
        LOGGER.debug("convertXmlToJson : Entering the method");
        if (file != null && !file.isEmpty()) {
            AnalysisEntity analysisEntity = analysisService.processAnalysis(file, Integer.parseInt(repo), branch);
            LOGGER.debug("convertXmlToJson : Exiting the method");
            return ResponseEntity.ok(analysisEntity);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}

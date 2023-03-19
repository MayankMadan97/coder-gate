package com.github.codergate.controllers;

import com.github.codergate.dto.analysis.AnalysisDTO;
import com.github.codergate.services.AnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
public class AnalysisController {
    @Autowired
    AnalysisService analysisService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalysisController.class);

    @PostMapping(value = "/analysis")
    public ResponseEntity<String> convertXmlToJson(@RequestParam("file") MultipartFile file) throws Exception {
        LOGGER.debug("convertXmlToJson : Entering the method");
        //AnalysisDTO analysisDTO = new AnalysisDTO();
        if (file != null && !file.isEmpty()) {
            String json = analysisService.processAnalysis(file);
            LOGGER.debug("convertXmlToJson : Exiting the method");
            return ResponseEntity.ok(json);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}

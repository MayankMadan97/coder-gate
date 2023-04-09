package com.github.codergate.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.codergate.dto.insight.OccurrencesDTO;
import com.github.codergate.dto.insight.TimeStampDTO;
import com.github.codergate.services.InsightService;
import com.github.codergate.utils.InsightUtil;

@RestController
public class InsightController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InsightController.class);

    @Autowired
    InsightService insightService;

    @Autowired
    InsightUtil iUtil;

    @GetMapping("/getTimeStampInsight/{repoId}/{branchId}")
    public ResponseEntity<String> fetchTimeStampInsights(@PathVariable("repoId") String repoId,
            @PathVariable("branchId") String branchId) throws JsonProcessingException {
        LOGGER.debug("fetchTimeStampInsights : Entering the method");
        TimeStampDTO timeStampInsightSeries = iUtil.getTimeStampInsightSeries(repoId, branchId);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String output = ow.writeValueAsString(timeStampInsightSeries);
        return ResponseEntity.ok(output);
    }

    @GetMapping("/getOccurrencesInsight/{repoId}/{branchId}")
    public ResponseEntity<String> fetchOccurrencesInsights(@PathVariable("repoId") String repoId,
            @PathVariable("branchId") String branchId) throws JsonProcessingException {
        LOGGER.debug("fetchOccurrencesInsights : Entering the method");
        OccurrencesDTO occurrencesInsight = insightService.getOccurrencesInsight(repoId, branchId);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String output = ow.writeValueAsString(occurrencesInsight);
        return ResponseEntity.ok(output);
    }

}

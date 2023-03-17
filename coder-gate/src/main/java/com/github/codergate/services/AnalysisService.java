package com.github.codergate.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.codergate.controllers.AnalysisController;
import com.github.codergate.dto.installation.InstallationPayloadDTO;
import com.github.codergate.entities.AnalysisEntity;
import com.github.codergate.repositories.AnalysisRepository;
import com.github.codergate.utils.Mapper;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

@Service
public class AnalysisService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalysisService.class);
    AnalysisRepository analysisRepository;

    public void createAnalysis() {
        AnalysisEntity analysisEntity = new AnalysisEntity();
        analysisRepository.save(analysisEntity);
    }

    public void convertDTOToEntity() {
        AnalysisEntity analysisEntity = new AnalysisEntity();
    }

    public void convertEntityToDto(AnalysisEntity analysisEntity) {

    }

    public void processAnalysis(MultipartFile file) throws Exception {
        try (InputStream inputStream = file.getInputStream()) {
            byte[] bytes = inputStream.readAllBytes();
            String xml = new String(bytes);
            JSONObject jsonObject = XML.toJSONObject(xml);
            LinkedHashMap<String, Object> analysisPayload = new ObjectMapper()
                    .readValue(jsonObject.toString(), LinkedHashMap.class);
        }
    }
}

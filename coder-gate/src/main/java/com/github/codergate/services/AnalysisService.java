package com.github.codergate.services;

import com.github.codergate.entities.AnalysisEntity;
import com.github.codergate.repositories.AnalysisRepository;
import org.springframework.stereotype.Service;

@Service
public class AnalysisService {

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
}

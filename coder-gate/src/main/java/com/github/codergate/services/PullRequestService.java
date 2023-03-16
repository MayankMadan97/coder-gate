package com.github.codergate.services;

import com.github.codergate.entities.AnalysisEntity;
import com.github.codergate.entities.ThresholdEntity;
import com.github.codergate.repositories.AnalysisRepository;
import com.github.codergate.repositories.ThresholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PullRequestService {

    @Autowired
    ThresholdRepository thresholdRepository;

    @Autowired
    AnalysisRepository analysisRepository;
    Boolean pullRequestCheck(String owner, String repositoryName,Integer pullNumber,Integer repositoryId){
        ThresholdEntity thresholdEntity = thresholdRepository.findById((long)repositoryId).get();
        AnalysisEntity analysisEntity = analysisRepository.findAnalysisByRepositoryId(repositoryId);

        return true;
    }
}

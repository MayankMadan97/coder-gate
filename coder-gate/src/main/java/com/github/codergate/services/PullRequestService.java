package com.github.codergate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.codergate.entities.AnalysisEntity;
import com.github.codergate.entities.ThresholdEntity;
import com.github.codergate.repositories.AnalysisRepository;
import com.github.codergate.repositories.ThresholdRepository;
import com.github.codergate.utils.PullRequestUtil;
@Service
public class PullRequestService {

    @Autowired
    ThresholdRepository thresholdRepository;

    @Autowired
    AnalysisRepository analysisRepository;

    @Autowired
    PullRequestUtil pullRequestUtil;

    public List<String> pullRequestCheck(Integer repositoryId){
        ThresholdEntity thresholdEntity = thresholdRepository.findByRepositoryId(repositoryId);
        AnalysisEntity analysisEntity = analysisRepository.findLatestAnalysisByRepositoryId(repositoryId);
        return pullRequestUtil.getFalseThresholdKeys(analysisEntity,thresholdEntity);
    }
}

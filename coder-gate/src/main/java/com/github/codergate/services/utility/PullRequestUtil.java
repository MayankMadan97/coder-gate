package com.github.codergate.services.utility;

import com.github.codergate.entities.AnalysisEntity;
import com.github.codergate.entities.ThresholdEntity;
import org.springframework.stereotype.Component;

@Component
public class PullRequestUtil {
     public Boolean checkThreshold(AnalysisEntity analysisEntity, ThresholdEntity thresholdEntity){
        if(
            analysisEntity.getBugs() > thresholdEntity.getBugs()||
            analysisEntity.getCodeSmell() > thresholdEntity.getCodeSmell() ||
            analysisEntity.getCyclomaticComplexity() > thresholdEntity.getCyclomaticComplexity()||
            analysisEntity.getDocumentedLines() > thresholdEntity.getDocumentedLines()||
            analysisEntity.getDuplicatedLines() > thresholdEntity.getDuplicatedLines()||
            analysisEntity.getTestCoverage() > thresholdEntity.getTestCoverage()||
            analysisEntity.getVulnerabilities() > thresholdEntity.getVulnerabilities()
        ){
            return false;
        }
        return true;
    }
}

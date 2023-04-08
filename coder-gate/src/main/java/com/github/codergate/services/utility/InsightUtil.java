package com.github.codergate.services.utility;

import com.github.codergate.dto.insight.DataDTO;
import com.github.codergate.entities.AnalysisEntity;
import com.github.codergate.repositories.AnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class InsightUtil {

    @Autowired
    AnalysisRepository analysisRepository;


    public DataDTO getCodeSmellsData(String repoId, String branchId){
        DataDTO codeSmellsData = new DataDTO();
        List<AnalysisEntity> analysisList = analysisRepository.findAnalysisByRepositoryAndBranchId(Integer.parseInt(repoId), branchId);
        Map<Long, Double> timeStampMap = new LinkedHashMap<>();
        for(AnalysisEntity analysis : analysisList){
            timeStampMap.put(analysis.getTimestamp(), analysis.getCodeSmell());
        }
        codeSmellsData.setDataValuesMap(timeStampMap);
        return codeSmellsData;
    }

    public DataDTO getDesignSmellDensityData(String repoId, String branchId){
        DataDTO designSmellDensityData = new DataDTO();
        List<AnalysisEntity> analysisList = analysisRepository.findAnalysisByRepositoryAndBranchId(Integer.parseInt(repoId), branchId);
        Map<Long, Double> timeStampMap = new LinkedHashMap<>();
        for(AnalysisEntity analysis : analysisList){
            timeStampMap.put(analysis.getTimestamp(), analysis.getDesignSmellDensity());
        }
        designSmellDensityData.setDataValuesMap(timeStampMap);
        return designSmellDensityData;
    }

    public DataDTO getImplementationSmellDensityData(String repoId, String branchId){
        DataDTO implementationSmellDensityData = new DataDTO();
        List<AnalysisEntity> analysisList = analysisRepository.findAnalysisByRepositoryAndBranchId(Integer.parseInt(repoId), branchId);
        Map<Long, Double> timeStampMap = new LinkedHashMap<>();
        for(AnalysisEntity analysis : analysisList){
            timeStampMap.put(analysis.getTimestamp(), analysis.getImpSmellDensity());
        }
        implementationSmellDensityData.setDataValuesMap(timeStampMap);
        return implementationSmellDensityData;
    }

    public DataDTO getLocData(String repoId, String branchId){
        DataDTO locData = new DataDTO();
        List<AnalysisEntity> analysisList = analysisRepository.findAnalysisByRepositoryAndBranchId(Integer.parseInt(repoId), branchId);
        Map<Long, Double> timeStampMap = new LinkedHashMap<>();
        for(AnalysisEntity analysis : analysisList){
            timeStampMap.put(analysis.getTimestamp(), analysis.getDocumentedLines());
        }
        locData.setDataValuesMap(timeStampMap);
        return locData;
    }

    public DataDTO getDuplicatedLinesData(String repoId, String branchId){
        DataDTO duplicatedLinesData = new DataDTO();
        List<AnalysisEntity> analysisList = analysisRepository.findAnalysisByRepositoryAndBranchId(Integer.parseInt(repoId), branchId);
        Map<Long, Double> timeStampMap = new LinkedHashMap<>();
        for(AnalysisEntity analysis : analysisList){
            timeStampMap.put(analysis.getTimestamp(), analysis.getDuplicatedLines());
        }
        duplicatedLinesData.setDataValuesMap(timeStampMap);
        return duplicatedLinesData;
    }
}

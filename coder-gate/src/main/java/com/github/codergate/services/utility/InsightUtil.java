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
    public DataDTO getBugsData(String repoId, String branchId){
        DataDTO bugsData = new DataDTO();
        List<AnalysisEntity> analysisList = analysisRepository.findAnalysisByRepositoryAndBranchId(Integer.parseInt(repoId), branchId);
        Map<Long, Integer> timeStampMap = new LinkedHashMap<>();
        for(AnalysisEntity analysis : analysisList){
            timeStampMap.put(analysis.getTimestamp(), analysis.getBugs());
        }
        bugsData.setDataValuesMap(timeStampMap);
        return bugsData;
    }

    public DataDTO getVulnerabilitiesData(String repoId, String branchId){
        DataDTO vulnerabilitiesData = new DataDTO();
        List<AnalysisEntity> analysisList = analysisRepository.findAnalysisByRepositoryAndBranchId(Integer.parseInt(repoId), branchId);
        Map<Long, Integer> timeStampMap = new LinkedHashMap<>();
        for(AnalysisEntity analysis : analysisList){
            timeStampMap.put(analysis.getTimestamp(), analysis.getVulnerabilities());
        }
        vulnerabilitiesData.setDataValuesMap(timeStampMap);
        return vulnerabilitiesData;
    }

    public DataDTO getCodeSmellsData(String repoId, String branchId){
        DataDTO codeSmellsData = new DataDTO();
        List<AnalysisEntity> analysisList = analysisRepository.findAnalysisByRepositoryAndBranchId(Integer.parseInt(repoId), branchId);
        Map<Long, Integer> timeStampMap = new LinkedHashMap<>();
        for(AnalysisEntity analysis : analysisList){
            timeStampMap.put(analysis.getTimestamp(), (int)analysis.getCodeSmell());
        }
        codeSmellsData.setDataValuesMap(timeStampMap);
        return codeSmellsData;
    }

    public DataDTO getDesignSmellDensityData(String repoId, String branchId){
        DataDTO designSmellDensityData = new DataDTO();
        List<AnalysisEntity> analysisList = analysisRepository.findAnalysisByRepositoryAndBranchId(Integer.parseInt(repoId), branchId);
        Map<Long, Integer> timeStampMap = new LinkedHashMap<>();
        for(AnalysisEntity analysis : analysisList){
            timeStampMap.put(analysis.getTimestamp(), (int)analysis.getDesignSmellDensity());
        }
        designSmellDensityData.setDataValuesMap(timeStampMap);
        return designSmellDensityData;
    }

    public DataDTO getImplementationSmellDensityData(String repoId, String branchId){
        DataDTO implementationSmellDensityData = new DataDTO();
        List<AnalysisEntity> analysisList = analysisRepository.findAnalysisByRepositoryAndBranchId(Integer.parseInt(repoId), branchId);
        Map<Long, Integer> timeStampMap = new LinkedHashMap<>();
        for(AnalysisEntity analysis : analysisList){
            timeStampMap.put(analysis.getTimestamp(), (int)analysis.getImpSmellDensity());
        }
        implementationSmellDensityData.setDataValuesMap(timeStampMap);
        return implementationSmellDensityData;
    }

    public DataDTO getLocData(String repoId, String branchId){
        DataDTO locData = new DataDTO();
        List<AnalysisEntity> analysisList = analysisRepository.findAnalysisByRepositoryAndBranchId(Integer.parseInt(repoId), branchId);
        Map<Long, Integer> timeStampMap = new LinkedHashMap<>();
        for(AnalysisEntity analysis : analysisList){
            timeStampMap.put(analysis.getTimestamp(), (int)analysis.getDocumentedLines());
        }
        locData.setDataValuesMap(timeStampMap);
        return locData;
    }

    public DataDTO getDuplicatedLinesData(String repoId, String branchId){
        DataDTO duplicatedLinesData = new DataDTO();
        List<AnalysisEntity> analysisList = analysisRepository.findAnalysisByRepositoryAndBranchId(Integer.parseInt(repoId), branchId);
        Map<Long, Integer> timeStampMap = new LinkedHashMap<>();
        for(AnalysisEntity analysis : analysisList){
            timeStampMap.put(analysis.getTimestamp(), (int)analysis.getDuplicatedLines());
        }
        duplicatedLinesData.setDataValuesMap(timeStampMap);
        return duplicatedLinesData;
    }
}

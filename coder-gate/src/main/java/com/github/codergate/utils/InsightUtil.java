package com.github.codergate.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.codergate.dto.insight.DataDTO;
import com.github.codergate.dto.insight.SeriesDTO;
import com.github.codergate.dto.insight.TimeStampDTO;
import com.github.codergate.entities.AnalysisEntity;
import com.github.codergate.repositories.AnalysisRepository;

@Component
public class InsightUtil {

    @Autowired
    AnalysisRepository repo;

    public DataDTO getCodeSmellsData(String repoId, String branchId) {
        DataDTO codeSmellsData = new DataDTO();
        int id = Integer.parseInt(repoId);
        List<AnalysisEntity> analysisList = repo
                .findAnalysisByRepositoryAndBranchId(id, branchId);
        Map<Long, Double> timeStampMap = new LinkedHashMap<>();
        for (AnalysisEntity analysis : analysisList) {
            timeStampMap.put(analysis.getTimestamp(), analysis.getCodeSmell());
        }
        codeSmellsData.setDataValuesMap(timeStampMap);
        return codeSmellsData;
    }

    public DataDTO getArchSmellDensityData(String repoId, String branchId) {
        DataDTO archSmellDensityData = new DataDTO();
        int id = Integer.parseInt(repoId);
        List<AnalysisEntity> analysisList = repo
                .findAnalysisByRepositoryAndBranchId(id, branchId);
        Map<Long, Double> timeStampMap = new LinkedHashMap<>();
        for (AnalysisEntity analysis : analysisList) {
            timeStampMap.put(analysis.getTimestamp(), analysis.getArchSmellDensity());
        }
        archSmellDensityData.setDataValuesMap(timeStampMap);
        return archSmellDensityData;
    }

    public DataDTO getDesignSmellDensityData(String repoId, String branchId) {
        DataDTO designSmellDensityData = new DataDTO();
        int id = Integer.parseInt(repoId);
        List<AnalysisEntity> analysisList = repo.findAnalysisByRepositoryAndBranchId(id, branchId);
        Map<Long, Double> timeStampMap = new LinkedHashMap<>();
        for (AnalysisEntity analysis : analysisList) {
            timeStampMap.put(analysis.getTimestamp(), analysis.getDesignSmellDensity());
        }
        designSmellDensityData.setDataValuesMap(timeStampMap);
        return designSmellDensityData;
    }

    public DataDTO getImplementationSmellDensityData(String repoId, String branchId) {
        DataDTO implSmellDensityData = new DataDTO();
        int id = Integer.parseInt(repoId);
        List<AnalysisEntity> analysisList = repo.findAnalysisByRepositoryAndBranchId(id, branchId);
        Map<Long, Double> timeStampMap = new LinkedHashMap<>();
        for (AnalysisEntity analysis : analysisList) {
            timeStampMap.put(analysis.getTimestamp(), analysis.getImpSmellDensity());
        }
        implSmellDensityData.setDataValuesMap(timeStampMap);
        return implSmellDensityData;
    }

    public DataDTO getLocData(String repoId, String branchId) {
        DataDTO locData = new DataDTO();
        int id = Integer.parseInt(repoId);
        List<AnalysisEntity> analysisList = repo.findAnalysisByRepositoryAndBranchId(id, branchId);
        Map<Long, Double> timeStampMap = new LinkedHashMap<>();
        for (AnalysisEntity analysis : analysisList) {
            timeStampMap.put(analysis.getTimestamp(), analysis.getDocumentedLines());
        }
        locData.setDataValuesMap(timeStampMap);
        return locData;
    }

    public DataDTO getDuplicatedLinesData(String repoId, String branchId) {
        DataDTO duplicatedLinesData = new DataDTO();
        int id = Integer.parseInt(repoId);
        List<AnalysisEntity> analysisList = repo.findAnalysisByRepositoryAndBranchId(id, branchId);
        Map<Long, Double> timeStampMap = new LinkedHashMap<>();
        for (AnalysisEntity analysis : analysisList) {
            timeStampMap.put(analysis.getTimestamp(), analysis.getDuplicatedLines());
        }
        duplicatedLinesData.setDataValuesMap(timeStampMap);
        return duplicatedLinesData;
    }

    public TimeStampDTO getTimeStampInsightSeries(String repoId, String branchId) {
        TimeStampDTO timeStampDTO = new TimeStampDTO();
        List<SeriesDTO> seriesDTOList = new ArrayList<>();
    
        DataDTO codeSmellsData = getCodeSmellsData(repoId, branchId);
        SeriesDTO codeSmellsSeries = new SeriesDTO();
        codeSmellsSeries.setName("Code Smells");
        codeSmellsSeries.setData(codeSmellsData);
        seriesDTOList.add(codeSmellsSeries);
    
        DataDTO archSmellDensityData = getArchSmellDensityData(repoId, branchId);
        SeriesDTO archSmellDensitySeries = new SeriesDTO();
        archSmellDensitySeries.setName("Architectural Smell Density");
        archSmellDensitySeries.setData(archSmellDensityData);
        seriesDTOList.add(archSmellDensitySeries);
    
        DataDTO designSmellDensityData = getDesignSmellDensityData(repoId, branchId);
        SeriesDTO designSmellDensitySeries = new SeriesDTO();
        designSmellDensitySeries.setName("Design Smell Density");
        designSmellDensitySeries.setData(designSmellDensityData);
        seriesDTOList.add(designSmellDensitySeries);
    
        DataDTO implSmellDensityData = getImplementationSmellDensityData(repoId, branchId);
        SeriesDTO implSmellDensitySeries = new SeriesDTO();
        implSmellDensitySeries.setName("Implementation Smell Density");
        implSmellDensitySeries.setData(implSmellDensityData);
        seriesDTOList.add(implSmellDensitySeries);
    
        DataDTO locData = getLocData(repoId, branchId);
        SeriesDTO locSeries = new SeriesDTO();
        locSeries.setName("LOC");
        locSeries.setData(locData);
        seriesDTOList.add(locSeries);
    
        DataDTO duplicatedLinesData = getDuplicatedLinesData(repoId, branchId);
        SeriesDTO duplicatedLinesSeries = new SeriesDTO();
        duplicatedLinesSeries.setName("Duplicated Lines");
        duplicatedLinesSeries.setData(duplicatedLinesData);
        seriesDTOList.add(duplicatedLinesSeries);
    
        timeStampDTO.setSeriesList(seriesDTOList);
        return timeStampDTO;
      }
    
}

package com.github.codergate.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.codergate.dto.insight.DataDTO;
import com.github.codergate.dto.insight.OccurrencesDTO;
import com.github.codergate.dto.insight.SeriesDTO;
import com.github.codergate.dto.insight.TimeStampDTO;
import com.github.codergate.entities.AnalysisEntity;
import com.github.codergate.repositories.AnalysisRepository;
import com.github.codergate.services.utility.InsightUtil;

@Service
public class InsightService {

  @Autowired
  InsightUtil insightUtil;

  @Autowired
  private AnalysisRepository analysisRepository;

  public TimeStampDTO getTimeStampInsightSeries(String repoId, String branchId) {
    TimeStampDTO timeStampDTO = new TimeStampDTO();
    List<SeriesDTO> seriesDTOList = new ArrayList<>();

    DataDTO codeSmellsData = insightUtil.getCodeSmellsData(repoId, branchId);
    SeriesDTO codeSmellsSeries = new SeriesDTO();
    codeSmellsSeries.setName("Code Smells");
    codeSmellsSeries.setData(codeSmellsData);
    seriesDTOList.add(codeSmellsSeries);

    DataDTO archSmellDensityData = insightUtil.getArchSmellDensityData(repoId, branchId);
    SeriesDTO archSmellDensitySeries = new SeriesDTO();
    archSmellDensitySeries.setName("Architectural Smell Density");
    archSmellDensitySeries.setData(archSmellDensityData);
    seriesDTOList.add(archSmellDensitySeries);

    DataDTO designSmellDensityData = insightUtil.getDesignSmellDensityData(repoId, branchId);
    SeriesDTO designSmellDensitySeries = new SeriesDTO();
    designSmellDensitySeries.setName("Design Smell Density");
    designSmellDensitySeries.setData(designSmellDensityData);
    seriesDTOList.add(designSmellDensitySeries);

    DataDTO implementationSmellDensityData = insightUtil.getImplementationSmellDensityData(repoId, branchId);
    SeriesDTO implementationSmellDensitySeries = new SeriesDTO();
    implementationSmellDensitySeries.setName("Implementation Smell Density");
    implementationSmellDensitySeries.setData(implementationSmellDensityData);
    seriesDTOList.add(implementationSmellDensitySeries);

    DataDTO locData = insightUtil.getLocData(repoId, branchId);
    SeriesDTO locSeries = new SeriesDTO();
    locSeries.setName("LOC");
    locSeries.setData(locData);
    seriesDTOList.add(locSeries);

    DataDTO duplicatedLinesData = insightUtil.getDuplicatedLinesData(repoId, branchId);
    SeriesDTO duplicatedLinesSeries = new SeriesDTO();
    duplicatedLinesSeries.setName("Duplicated Lines");
    duplicatedLinesSeries.setData(duplicatedLinesData);
    seriesDTOList.add(duplicatedLinesSeries);

    timeStampDTO.setSeriesList(seriesDTOList);
    return timeStampDTO;
  }

  public OccurrencesDTO getOccurrencesInsight(String repoId, String branchId) {
    OccurrencesDTO occurrencesDTO = new OccurrencesDTO();
    Map<String, Double> occurrencesMap = new HashMap<>();
    AnalysisEntity latestAnalysisByRepositoryIdAndBranchId = analysisRepository
        .findLatestAnalysisByRepositoryIdAndBranchId(Integer.parseInt(repoId), branchId);
    occurrencesMap.put("Complex Conditional", (double) latestAnalysisByRepositoryIdAndBranchId.getComplexConditional());
    occurrencesMap.put("Cyclic Dependencies", (double) latestAnalysisByRepositoryIdAndBranchId.getCyclicDependency());
    occurrencesMap.put("Empty Catch Clause", (double) latestAnalysisByRepositoryIdAndBranchId.getEmptyCatchClause());
    occurrencesMap.put("Empty Test", (double) latestAnalysisByRepositoryIdAndBranchId.getEmptyTest());
    occurrencesMap.put("God Components", (double) latestAnalysisByRepositoryIdAndBranchId.getGodComponents());
    occurrencesMap.put("Insufficient Modularization",
        (double) latestAnalysisByRepositoryIdAndBranchId.getInsufficientModularization());
    occurrencesMap.put("Missing Assertion", (double) latestAnalysisByRepositoryIdAndBranchId.getMissingAssertion());
    occurrencesMap.put("CyclomaticComplexity", latestAnalysisByRepositoryIdAndBranchId.getCyclomaticComplexity());
    occurrencesMap.put("Duplicated Lines", latestAnalysisByRepositoryIdAndBranchId.getDuplicatedLines());
    occurrencesMap.put("Documented Lines", latestAnalysisByRepositoryIdAndBranchId.getDocumentedLines());
    occurrencesMap.put("Test Coverage", latestAnalysisByRepositoryIdAndBranchId.getTestCoverage());
    occurrencesMap.put("Cyclic Dependent Modularization",
        (double) latestAnalysisByRepositoryIdAndBranchId.getCyclicallyDependentModularization());
    occurrencesMap.put("Architecture Smell Density",
        latestAnalysisByRepositoryIdAndBranchId.getArchSmellDensity());
    occurrencesMap.put("Implementation Smell Density",
        latestAnalysisByRepositoryIdAndBranchId.getImpSmellDensity());
    occurrencesMap.put("Design Smell Density", latestAnalysisByRepositoryIdAndBranchId.getDesignSmellDensity());
    occurrencesMap.put("LOC", (double)latestAnalysisByRepositoryIdAndBranchId.getLoc());
    occurrencesMap.put("Method Count", (double)latestAnalysisByRepositoryIdAndBranchId.getMethodCount());
    occurrencesMap.put("Total Architecture Smell", (double)latestAnalysisByRepositoryIdAndBranchId.getTotalArchSmellCount());
    occurrencesMap.put("Total Design Smell", (double)latestAnalysisByRepositoryIdAndBranchId.getTotalDesignSmellCount());
    occurrencesMap.put("Total Implementation Smell", (double)latestAnalysisByRepositoryIdAndBranchId.getTotalImplSmellCount());
    occurrencesMap.put("Component Count", (double)latestAnalysisByRepositoryIdAndBranchId.getComponentCount());
    occurrencesMap.put("Metric Violations", (double)latestAnalysisByRepositoryIdAndBranchId.getMetricVoilations());
    occurrencesMap.put("Type Count", (double)latestAnalysisByRepositoryIdAndBranchId.getTypeCount());
    occurrencesDTO.setOccurrencesSeries(occurrencesMap);

    return occurrencesDTO;
  }
}

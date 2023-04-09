package com.github.codergate.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.codergate.dto.insight.OccurrencesDTO;
import com.github.codergate.entities.AnalysisEntity;
import com.github.codergate.repositories.AnalysisRepository;
import com.github.codergate.utils.InsightUtil;

@Service
public class InsightService {

  @Autowired
  InsightUtil insightUtil;

  @Autowired
  private AnalysisRepository analysisRepository;

  public OccurrencesDTO getOccurrencesInsight(String repoId, String branchId) {
    OccurrencesDTO occurrencesDTO = new OccurrencesDTO();
    Map<String, Double> occurrencesMap = new HashMap<>();
    AnalysisEntity analysis = analysisRepository
        .findLatestAnalysisByRepositoryIdAndBranchId(Integer.parseInt(repoId), branchId);
    occurrencesMap.put("Complex Conditional", (double) analysis.getComplexConditional());
    occurrencesMap.put("Cyclic Dependencies", (double) analysis.getCyclicDependency());
    occurrencesMap.put("Empty Catch Clause", (double) analysis.getEmptyCatchClause());
    occurrencesMap.put("Empty Test", (double) analysis.getEmptyTest());
    occurrencesMap.put("God Components", (double) analysis.getGodComponents());
    occurrencesMap.put("Insufficient Modularization",
        (double) analysis.getInsufficientModularization());
    occurrencesMap.put("Missing Assertion", (double) analysis.getMissingAssertion());
    occurrencesMap.put("CyclomaticComplexity", analysis.getCyclomaticComplexity());
    occurrencesMap.put("Duplicated Lines", analysis.getDuplicatedLines());
    occurrencesMap.put("Documented Lines", analysis.getDocumentedLines());
    occurrencesMap.put("Test Coverage", analysis.getTestCoverage());
    occurrencesMap.put("Cyclic Dependent Modularization",
        (double) analysis.getCyclicallyDependentModularization());
    occurrencesMap.put("Architecture Smell Density",
        analysis.getArchSmellDensity());
    occurrencesMap.put("Implementation Smell Density",
        analysis.getImpSmellDensity());
    occurrencesMap.put("Design Smell Density", analysis.getDesignSmellDensity());
    occurrencesMap.put("LOC", (double) analysis.getLoc());
    occurrencesMap.put("Method Count", (double) analysis.getMethodCount());
    occurrencesMap.put("Total Architecture Smell", (double) analysis.getTotalArchSmellCount());
    occurrencesMap.put("Total Design Smell", (double) analysis.getTotalDesignSmellCount());
    occurrencesMap.put("Total Implementation Smell", (double) analysis.getTotalImplSmellCount());
    occurrencesMap.put("Component Count", (double) analysis.getComponentCount());
    occurrencesMap.put("Metric Violations", (double) analysis.getMetricVoilations());
    occurrencesMap.put("Type Count", (double) analysis.getTypeCount());
    occurrencesDTO.setOccurrencesSeries(occurrencesMap);

    return occurrencesDTO;
  }
}

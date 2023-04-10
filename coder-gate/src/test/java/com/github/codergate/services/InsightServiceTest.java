package com.github.codergate.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.codergate.dto.insight.DataDTO;
import com.github.codergate.dto.insight.OccurrencesDTO;
import com.github.codergate.dto.insight.SeriesDTO;
import com.github.codergate.dto.insight.TimeStampDTO;
import com.github.codergate.entities.AnalysisEntity;
import com.github.codergate.entities.BranchEntity;
import com.github.codergate.entities.BranchId;
import com.github.codergate.entities.RepositoryEntity;
import com.github.codergate.repositories.AnalysisRepository;
import com.github.codergate.utils.InsightUtil;

@ExtendWith(MockitoExtension.class)
class InsightServiceTest {
    @Mock
    InsightUtil insightUtil;
    @Mock
    AnalysisRepository analysisRepository;
    @InjectMocks
    InsightService insightService;
    private final double codeSmell = 4.30;
    private final double duplicatedLines5 = 1;
    private final double documentedLines  = 10;
    private final double testCoverage = 40.6;
    private final double cyclomaticComplexity = 0.198938992;
    private final int cyclicDependency = 31;
    private final int godComponents = 1;
    private final int cycDependentMod = 80;
    private final int insufficientModularization = 70;
    private final int unnecessaryAbstraction = 60;
    private final int complexConditional = 50;
    private final int emptyCatchClause = 40;
    private final int emptyTest = 20;
    private final int missingAssertion = 30;
    private final double archSmellDensity = 10.1;
    private final double designSmellDensity = 20.2;
    private final double impSmellDensity = 30.3;
    private final long timestamp = 1000101;
    private final int repositoryID = 101;
    private final String repoID = "101";
    private final String branchName = "main";
    private Integer LOC = 10000;
    private Integer methodCount = 300;
    private Integer totalArchSmell = 60;
    private Integer totalDesignSmell = 80;
    private Integer totalImpSmell = 160;
    private Integer componentCount = 80;
    private Integer metricViolations = 100;
    private Integer typeCount = 75;

    private AnalysisEntity setUpAnalysisEntity() {
        AnalysisEntity analysisEntity = new AnalysisEntity();

        analysisEntity.setCodeSmell(codeSmell);
        analysisEntity.setDuplicatedLines(duplicatedLines5);
        analysisEntity.setCyclomaticComplexity(cyclomaticComplexity);

        analysisEntity.setCyclicDependency(cyclicDependency);
        analysisEntity.setGodComponents(godComponents);

        analysisEntity.setCyclicallyDependentModularization(cycDependentMod);
        analysisEntity.setInsufficientModularization(insufficientModularization);
        analysisEntity.setUnnecessaryAbstraction(unnecessaryAbstraction);

        analysisEntity.setComplexConditional(complexConditional);
        analysisEntity.setEmptyCatchClause(emptyCatchClause);

        analysisEntity.setArchSmellDensity(archSmellDensity);
        analysisEntity.setDesignSmellDensity(designSmellDensity);
        analysisEntity.setImpSmellDensity(impSmellDensity);

        analysisEntity.setLoc(LOC);
        analysisEntity.setMethodCount(methodCount);
        analysisEntity.setTotalArchSmellCount(totalArchSmell);
        analysisEntity.setTotalDesignSmellCount(totalDesignSmell);
        analysisEntity.setTotalImplSmellCount(totalImpSmell);
        analysisEntity.setComponentCount(componentCount);
        analysisEntity.setMetricVoilations(metricViolations);
        analysisEntity.setTypeCount(typeCount);

        analysisEntity.setEmptyTest(emptyTest);
        analysisEntity.setMissingAssertion(missingAssertion);
        analysisEntity.setDocumentedLines(documentedLines);
        analysisEntity.setTestCoverage(testCoverage);


        analysisEntity.setTimestamp(timestamp);

        BranchId branchId = new BranchId(repositoryID, branchName);
        BranchEntity branchEntity = new BranchEntity();
        branchEntity.setBranchId(branchId);
        RepositoryEntity repositoryEntity = new RepositoryEntity();
        repositoryEntity.setRepositoryId(repositoryID);
        branchEntity.setRepositoryIdInBranch(repositoryEntity);

        analysisEntity.setBranchId(branchEntity);
        return analysisEntity;
    }

    private SeriesDTO returnCodeSmellSeries() {
        DataDTO codeSmellData = new DataDTO();
        Map<Long, Double> timeStampMap = new LinkedHashMap<>();
        timeStampMap.put(timestamp, codeSmell);
        codeSmellData.setDataValuesMap(timeStampMap);
        SeriesDTO codeSmellSeries = new SeriesDTO();
        codeSmellSeries.setName("Code Smells");
//        codeSmellSeries.setData(codeSmellData);
        return codeSmellSeries;
    }

    private SeriesDTO returnArchSmellDensSeries() {
        DataDTO archSmellDenData = new DataDTO();
        Map<Long, Double> timeStampMap = new LinkedHashMap<>();
        timeStampMap.put(timestamp, archSmellDensity);
        archSmellDenData.setDataValuesMap(timeStampMap);
        SeriesDTO archSmellSeries = new SeriesDTO();
        archSmellSeries.setName("Architectural Smell Density");
//        archSmellSeries.setData(archSmellDenData);
        return archSmellSeries;
    }

    private SeriesDTO returnDesignSmellSeries() {
        DataDTO designSmellData = new DataDTO();
        Map<Long, Double> timeStampMap = new LinkedHashMap<>();
        timeStampMap.put(timestamp, designSmellDensity);
        designSmellData.setDataValuesMap(timeStampMap);
        SeriesDTO designSmellSeries = new SeriesDTO();
        designSmellSeries.setName("Design Smell Density");
//        designSmellSeries.setData(designSmellData);
        return designSmellSeries;
    }

    private SeriesDTO returnImpSmellSeries() {
        DataDTO impSmellData = new DataDTO();
        Map<Long, Double> timeStampMap = new LinkedHashMap<>();
        timeStampMap.put(timestamp, codeSmell);
        impSmellData.setDataValuesMap(timeStampMap);
        SeriesDTO impSmellSeries = new SeriesDTO();
        impSmellSeries.setName("Implementation Smell Density");
//        impSmellSeries.setData(impSmellData);
        return impSmellSeries;
    }

    private SeriesDTO returnLOCSeries() {
        DataDTO locData = new DataDTO();
        Map<Long, Double> timeStampMap = new LinkedHashMap<>();
        timeStampMap.put(timestamp, codeSmell);
        locData.setDataValuesMap(timeStampMap);
        SeriesDTO locSeries = new SeriesDTO();
        locSeries.setName("LOC");
//        locSeries.setData(locData);
        return locSeries;
    }

    private SeriesDTO returnDuplicatedLinesSeries() {
        DataDTO dupLineData = new DataDTO();
        Map<Long, Double> timeStampMap = new LinkedHashMap<>();
        timeStampMap.put(timestamp, codeSmell);
        dupLineData.setDataValuesMap(timeStampMap);
        SeriesDTO dupLineSeries = new SeriesDTO();
        dupLineSeries.setName("Duplicated Lines");
//        dupLineSeries.setData(dupLineData);
        return dupLineSeries;
    }

    private OccurrencesDTO setUpOccurancesDTO() {
        OccurrencesDTO occurrencesDTO = new OccurrencesDTO();
        Map<String, Double> occurrencesMap = new HashMap<>();

        occurrencesMap.put("Complex Conditional", (double) complexConditional);
        occurrencesMap.put("Cyclic Dependencies", (double) cyclicDependency);
        occurrencesMap.put("Empty Catch Clause", (double) emptyCatchClause);
        occurrencesMap.put("Empty Test", (double) emptyTest);
        occurrencesMap.put("God Components", (double) godComponents);
        occurrencesMap.put("Insufficient Modularization",
                (double) insufficientModularization);
        occurrencesMap.put("Missing Assertion", (double) missingAssertion);
        occurrencesMap.put("CyclomaticComplexity", cyclomaticComplexity);
        occurrencesMap.put("Duplicated Lines", duplicatedLines5);
        occurrencesMap.put("Documented Lines", documentedLines);
        occurrencesMap.put("Test Coverage", testCoverage);
        occurrencesMap.put("Cyclic Dependent Modularization",
                (double) cycDependentMod);
        occurrencesMap.put("Architecture Smell Density",
                archSmellDensity);
        occurrencesMap.put("Implementation Smell Density",
                impSmellDensity);
        occurrencesMap.put("Design Smell Density", designSmellDensity);
        occurrencesMap.put("LOC", (double) LOC);
        occurrencesMap.put("Method Count", (double) methodCount);
        occurrencesMap.put("Total Architecture Smell", (double) totalArchSmell);
        occurrencesMap.put("Total Design Smell", (double) totalDesignSmell);
        occurrencesMap.put("Total Implementation Smell", (double) totalImpSmell);
        occurrencesMap.put("Component Count", (double) componentCount);
        occurrencesMap.put("Metric Violations", (double)metricViolations);
        occurrencesMap.put("Type Count", (double) typeCount);
        occurrencesDTO.setOccurrencesSeries(occurrencesMap);

        return occurrencesDTO;
    }

//     @Test
//     void getTimeStampInsightSeriesTest() {
//         TimeStampDTO expected = new TimeStampDTO();
//         List<SeriesDTO> seriesDTOList = new ArrayList<>();

//         AnalysisEntity analysisEntity = setUpAnalysisEntity();
//         List<AnalysisEntity> listOfAnalysisEntities = new ArrayList<>();
//         listOfAnalysisEntities.add(analysisEntity);

// //        when(analysisRepository.findAnalysisByRepositoryAndBranchId(repositoryID, branchName)).thenReturn(listOfAnalysisEntities);

//         seriesDTOList.add(returnCodeSmellSeries());
//         seriesDTOList.add(returnArchSmellDensSeries());
//         seriesDTOList.add(returnDesignSmellSeries());
//         seriesDTOList.add(returnImpSmellSeries());
//         seriesDTOList.add(returnLOCSeries());
//         seriesDTOList.add(returnDuplicatedLinesSeries());

//         expected.setSeriesList(seriesDTOList);

//         TimeStampDTO actual = insightUtil.getTimeStampInsightSeries(repoID, branchName);

//         assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
//     }

    @Test
    void getOccurancesInsightTest() {
        OccurrencesDTO expected = setUpOccurancesDTO();
        AnalysisEntity analysisEntity = setUpAnalysisEntity();

        when(analysisRepository.findLatestAnalysisByRepositoryIdAndBranchId(repositoryID, branchName)).thenReturn(analysisEntity);

        OccurrencesDTO actual = insightService.getOccurrencesInsight(repoID, branchName);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
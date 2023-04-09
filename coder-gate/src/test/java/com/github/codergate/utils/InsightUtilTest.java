package com.github.codergate.utils;

import com.github.codergate.dto.insight.DataDTO;
import com.github.codergate.entities.AnalysisEntity;
import com.github.codergate.repositories.AnalysisRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class InsightUtilTest {


    @Mock
    AnalysisRepository analysisRepository;

    @InjectMocks
    InsightUtil insightUtil;

    DataDTO codeSmellsData = new DataDTO();

    String repoId = "1234";
    String branchId = "88";
    double codeSmell = 9;
    long timestamp  =5;
    double designSmellDensity = 12;
    double archSmellDensity = 20;
    double duplicatedLines =11;
    double implementationSmell = 33;


    List<AnalysisEntity> analysisList = new ArrayList<>();
    AnalysisEntity analysis1 = new AnalysisEntity();
    DataDTO expectedData = new DataDTO();
    Map<Long, Double> expectedMap = new LinkedHashMap<>();



    @Test
    public void testGetCodeSmellData()
    {
        analysis1.setCodeSmell(codeSmell);
        analysis1.setTimestamp(timestamp);
        analysisList.add(analysis1);

        when(analysisRepository.findAnalysisByRepositoryAndBranchId(eq(Integer.parseInt(repoId)), eq(branchId)))
                .thenReturn(analysisList);
        expectedMap.put(timestamp,codeSmell);
        expectedData.setDataValuesMap(expectedMap);
        DataDTO actualData = insightUtil.getCodeSmellsData(repoId, branchId);
        assertEquals(expectedData.getDataValuesMap(), actualData.getDataValuesMap());

    }

    @Test
    public void testGetArchSmellData(){
    AnalysisEntity analysis = new AnalysisEntity();
    analysis.setArchSmellDensity(archSmellDensity);
    analysis.setTimestamp(timestamp);
    analysisList.add(analysis);
    when(analysisRepository.findAnalysisByRepositoryAndBranchId(eq(Integer.parseInt(repoId)), eq(branchId)))
            .thenReturn(analysisList);
    expectedMap.put(timestamp, archSmellDensity);
    expectedData.setDataValuesMap(expectedMap);
    DataDTO actualData = insightUtil.getArchSmellDensityData(repoId, branchId);
    assertEquals(expectedData.getDataValuesMap(), actualData.getDataValuesMap());
}

    @Test
    public void testGetDesignSmellData() {
        AnalysisEntity analysis = new AnalysisEntity();
        analysis.setDesignSmellDensity(designSmellDensity);
        analysis.setTimestamp(timestamp);
        analysisList.add(analysis);
        when(analysisRepository.findAnalysisByRepositoryAndBranchId(eq(Integer.parseInt(repoId)), eq(branchId)))
                .thenReturn(analysisList);
        expectedMap.put(timestamp, designSmellDensity);
        expectedData.setDataValuesMap(expectedMap);
        DataDTO actualData = insightUtil.getDesignSmellDensityData(repoId, branchId);
        assertEquals(expectedData.getDataValuesMap(), actualData.getDataValuesMap());
    }

    @Test
    public void testGetImplementationSmellData() {
        AnalysisEntity analysis = new AnalysisEntity();
        analysis.setImpSmellDensity(implementationSmell);
        analysis.setTimestamp(timestamp);
        analysisList.add(analysis);
        when(analysisRepository.findAnalysisByRepositoryAndBranchId(eq(Integer.parseInt(repoId)), eq(branchId)))
                .thenReturn(analysisList);
        expectedMap.put(timestamp, implementationSmell);
        expectedData.setDataValuesMap(expectedMap);
        DataDTO actualData = insightUtil.getImplementationSmellDensityData(repoId, branchId);
        assertEquals(expectedData.getDataValuesMap(), actualData.getDataValuesMap());
    }

    @Test
    public void testGetDuplicatedLinesData() {
        AnalysisEntity analysis = new AnalysisEntity();
        analysis.setDuplicatedLines(duplicatedLines);
        analysis.setTimestamp(timestamp);
        analysisList.add(analysis);
        when(analysisRepository.findAnalysisByRepositoryAndBranchId(eq(Integer.parseInt(repoId)), eq(branchId)))
                .thenReturn(analysisList);
        expectedMap.put(timestamp, duplicatedLines);
        expectedData.setDataValuesMap(expectedMap);
        DataDTO actualData = insightUtil.getDuplicatedLinesData(repoId, branchId);
        assertEquals(expectedData.getDataValuesMap(), actualData.getDataValuesMap());
    }





}
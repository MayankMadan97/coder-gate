package com.github.codergate.services;

import com.github.codergate.dto.insight.DataDTO;
import com.github.codergate.dto.insight.OccurrencesDTO;
import com.github.codergate.dto.insight.SeriesDTO;
import com.github.codergate.dto.insight.TimeStampDTO;
import com.github.codergate.entities.AnalysisEntity;
import com.github.codergate.repositories.AnalysisRepository;
import com.github.codergate.services.utility.InsightUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InsightService {

    @Autowired
    InsightUtil insightUtil;


    private static final Logger LOGGER = LoggerFactory.getLogger(InsightService.class);
    @Autowired
    private AnalysisRepository analysisRepository;

    public TimeStampDTO getTimeStampInsightSeries(String repoId, String branchId){
        TimeStampDTO timeStampDTO = new TimeStampDTO();
        List<SeriesDTO> seriesDTOList = new ArrayList<>();

        DataDTO bugsData = insightUtil.getBugsData(repoId, branchId);
        SeriesDTO bugsSeries = new SeriesDTO();
        bugsSeries.setName("Bugs");
        bugsSeries.setData(bugsData);
        seriesDTOList.add(bugsSeries);

        DataDTO vulnerabilitiesData = insightUtil.getVulnerabilitiesData(repoId, branchId);
        SeriesDTO vulnerabilitiesSeries = new SeriesDTO();
        vulnerabilitiesSeries.setName("Vulnerabilities");
        vulnerabilitiesSeries.setData(vulnerabilitiesData);
        seriesDTOList.add(vulnerabilitiesSeries);

        DataDTO codeSmellsData = insightUtil.getCodeSmellsData(repoId, branchId);
        SeriesDTO codeSmellsSeries = new SeriesDTO();
        codeSmellsSeries.setName("Code Smells");
        codeSmellsSeries.setData(codeSmellsData);
        seriesDTOList.add(codeSmellsSeries);

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
        public OccurrencesDTO getOccurrencesInsight(String repoId, String branchId){
        OccurrencesDTO occurrencesDTO = new OccurrencesDTO();
        Map<String,Integer> occurrencesMap = new HashMap<>();
        AnalysisEntity latestAnalysisByRepositoryIdAndBranchId = analysisRepository.findLatestAnalysisByRepositoryIdAndBranchId(Integer.parseInt(repoId), branchId);
        occurrencesMap.put("Complex Conditional",latestAnalysisByRepositoryIdAndBranchId.getComplexConditional());
        occurrencesMap.put("Cyclic Dependencies",latestAnalysisByRepositoryIdAndBranchId.getCyclicDependency());
        occurrencesMap.put("Empty Catch Clause",latestAnalysisByRepositoryIdAndBranchId.getEmptyCatchClause());
        occurrencesMap.put("Empty Test",latestAnalysisByRepositoryIdAndBranchId.getEmptyTest());
        occurrencesMap.put("God Components",latestAnalysisByRepositoryIdAndBranchId.getGodComponents());
        occurrencesMap.put("Insufficient Modularization",latestAnalysisByRepositoryIdAndBranchId.getInsufficientModularization());
        occurrencesMap.put("Missing Assertion",latestAnalysisByRepositoryIdAndBranchId.getMissingAssertion());
        occurrencesMap.put("CyclomaticComplexity",(int)latestAnalysisByRepositoryIdAndBranchId.getCyclomaticComplexity());
        occurrencesMap.put("Duplicated Lines",(int)latestAnalysisByRepositoryIdAndBranchId.getDuplicatedLines());
        occurrencesMap.put("Documented Lines",(int)latestAnalysisByRepositoryIdAndBranchId.getDocumentedLines());
        occurrencesMap.put("Test Coverage",(int)latestAnalysisByRepositoryIdAndBranchId.getTestCoverage());
        occurrencesMap.put("Cyclic Dependent Modularization",latestAnalysisByRepositoryIdAndBranchId.getCyclicallyDependentModularization());
        occurrencesMap.put("Architecture Smell Density",(int)latestAnalysisByRepositoryIdAndBranchId.getArchSmellDensity());
        occurrencesMap.put("Implementation Smell Density",(int)latestAnalysisByRepositoryIdAndBranchId.getImpSmellDensity());
        occurrencesMap.put("Design Smell Density",(int)latestAnalysisByRepositoryIdAndBranchId.getDesignSmellDensity());
        occurrencesDTO.setOccurrencesSeries(occurrencesMap);

            return occurrencesDTO;
        }
}

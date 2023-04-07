package com.github.codergate.services;

import com.github.codergate.dto.insight.DataDTO;
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
import java.util.List;

@Service
public class InsightService {

    @Autowired
    InsightUtil insightUtil;


    private static final Logger LOGGER = LoggerFactory.getLogger(InsightService.class);

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
}

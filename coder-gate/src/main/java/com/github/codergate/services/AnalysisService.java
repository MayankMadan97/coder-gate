package com.github.codergate.services;

import com.github.codergate.dto.analysis.AnalysisDTO;
import com.github.codergate.entities.AnalysisEntity;
import com.github.codergate.entities.EventEntity;
import com.github.codergate.entities.RepositoryEntity;
import com.github.codergate.repositories.AnalysisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class AnalysisService {

    @Autowired
    AnalysisRepository analysisRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalysisService.class);

    public AnalysisDTO addAnalysis(AnalysisDTO analysisToAdd, long eventID, int repositoryID) {
        AnalysisDTO analysisDTO = null;
        AnalysisEntity analysisEntity = convertAnalysisDtoToEntity(analysisToAdd, eventID, repositoryID);
        if(analysisEntity != null)
        {
            AnalysisEntity savedEntity = analysisRepository.save(analysisEntity);
            LOGGER.info("addAnalysis : The analysis information has been added {}", analysisEntity);
            analysisDTO = convertAnalysisEntityToDto(savedEntity);
        }
        return analysisDTO;
    }

    public AnalysisDTO getAnalysisByID(int repositoryID) {
        AnalysisDTO analysisDTO = null;
        AnalysisEntity analysisEntity = analysisRepository.findAnalysisByRepositoryId(repositoryID);
        if(analysisEntity != null)
        {
            analysisDTO = convertAnalysisEntityToDto(analysisEntity);
            LOGGER.info("getAnalysisByID : Successfully retrieved latest analysis with repositoryID {}", repositoryID);
        } else {
            LOGGER.warn("getAnalysisByID : Analysis with repositoryID {} not found", repositoryID);
        }
        return analysisDTO;
    }

    public AnalysisDTO updateAnalysisByRepositoryID(int repositoryID, AnalysisDTO newInformation) {
        AnalysisDTO analysisDTO = null;
        AnalysisEntity analysisEntity = analysisRepository.findLatestAnalysisByRepositoryId(repositoryID);
        if(analysisEntity != null)
        {
            if(newInformation != null)
            {
                if(newInformation.getAnalysisType() != null)
                {
                    analysisEntity.setType(newInformation.getAnalysisType());
                }
                if(repositoryID != 0)
                {
                    RepositoryEntity repositoryEntity = new RepositoryEntity();
                    repositoryEntity.setRepositoryId(repositoryID);
                    analysisEntity.setRepositoryIdInAnalysis(repositoryEntity);
                }
                if(newInformation.getBugs() != 0)
                {
                    analysisEntity.setBugs(newInformation.getBugs());
                }
                if(newInformation.getVulnerabilities() != 0)
                {
                    analysisEntity.setVulnerabilities(newInformation.getVulnerabilities());
                }
                if(newInformation.getCodeSmell() != 0)
                {
                    analysisEntity.setCodeSmell(newInformation.getCodeSmell());
                }
                if(newInformation.getTestCoverage() != 0)
                {
                    analysisEntity.setTestCoverage(newInformation.getTestCoverage());
                }
                if(newInformation.getDuplicatedLines() != 0)
                {
                    analysisEntity.setDuplicatedLines(newInformation.getDuplicatedLines());
                }
                if(newInformation.getCyclomaticComplexity() != 0)
                {
                    analysisEntity.setCyclomaticComplexity(newInformation.getCyclomaticComplexity());
                }
                if(newInformation.getDocumentedLines() != 0)
                {
                    analysisEntity.setDocumentedLines(newInformation.getDocumentedLines());
                }
                if(newInformation.getCyclicDependency() != 0)
                {
                    analysisEntity.setCyclicDependency(newInformation.getCyclomaticComplexity());
                }
                if(newInformation.getGodComponents() != 0)
                {
                    analysisEntity.setGodComponents(newInformation.getGodComponents());
                }
                if(newInformation.getCyclicallyDependentModularization() != 0)
                {
                    analysisEntity.setCyclicallyDependentModularization(newInformation.getCyclicallyDependentModularization());
                }
                if(newInformation.getInsufficientModularization() != 0)
                {
                    analysisEntity.setInsufficientModularization(newInformation.getInsufficientModularization());
                }
                if(newInformation.getUnnecessaryAbstraction() != 0)
                {
                    analysisEntity.setUnnecessaryAbstraction(newInformation.getUnnecessaryAbstraction());
                }
                if(newInformation.getComplexMethod() != 0)
                {
                    analysisEntity.setComplexMethod(newInformation.getComplexMethod());
                }
                if(newInformation.getComplexConditional() != 0)
                {
                    analysisEntity.setComplexConditional(newInformation.getComplexConditional());
                }
                if(newInformation.getEmptyCatchClause() != 0)
                {
                    analysisEntity.setEmptyCatchClause(newInformation.getEmptyCatchClause());
                }
                if(newInformation.getMissingAssertion() != 0)
                {
                    analysisEntity.setMissingAssertion(newInformation.getMissingAssertion());
                }
                if(newInformation.getEmptyTest() != 0)
                {
                    analysisEntity.setEmptyTest(newInformation.getEmptyTest());
                }
                Date date = new Date();
                analysisEntity.setTimestamp(date.getTime());
            }
            AnalysisEntity savedEntity = analysisRepository.save(analysisEntity);
            LOGGER.info("updateAnalysisByID : Updating the analysis information with repositoryID {}", repositoryID);
            analysisDTO = convertAnalysisEntityToDto(savedEntity);
        }
        return analysisDTO;
    }

//    public boolean deleteAnalysisByID(int repositoryID) {
//        boolean isDeleted = false;
//        if(repositoryID != 0)
//        {
//            analysisRepository.deleteByRepositoryId(repositoryID);
//            isDeleted = true;
//            LOGGER.info("deleteAnalysisByID : Deleting analysis information with repositoryID {}", repositoryID);
//        }
//        return isDeleted;
//    }

    public AnalysisEntity convertAnalysisDtoToEntity(AnalysisDTO analysisDTO, long eventID, int repositoryID) {
        AnalysisEntity analysisEntity = null;

        if(analysisDTO != null)
        {
            analysisEntity = new AnalysisEntity();
            if(analysisDTO.getAnalysisType() != null)
            {
                analysisEntity.setType(analysisDTO.getAnalysisType());
            }
            if(repositoryID != 0)
            {
                RepositoryEntity repositoryEntity = new RepositoryEntity();
                repositoryEntity.setRepositoryId(repositoryID);
                analysisEntity.setRepositoryIdInAnalysis(repositoryEntity);
            }
            if(analysisDTO.getBugs() != 0)
            {
                analysisEntity.setBugs(analysisDTO.getBugs());
            }
            if(analysisDTO.getVulnerabilities() != 0)
            {
                analysisEntity.setVulnerabilities(analysisDTO.getVulnerabilities());
            }
            if(analysisDTO.getCodeSmell() != 0)
            {
                analysisEntity.setCodeSmell(analysisDTO.getCodeSmell());
            }
            if(analysisDTO.getTestCoverage() != 0)
            {
                analysisEntity.setTestCoverage(analysisDTO.getTestCoverage());
            }
            if(analysisDTO.getDuplicatedLines() != 0)
            {
                analysisEntity.setDuplicatedLines(analysisDTO.getDuplicatedLines());
            }
            if(analysisDTO.getCyclomaticComplexity() != 0)
            {
                analysisEntity.setCyclomaticComplexity(analysisDTO.getCyclomaticComplexity());
            }
            if(analysisDTO.getDocumentedLines() != 0)
            {
                analysisEntity.setDocumentedLines(analysisDTO.getDocumentedLines());
            }
            if(analysisDTO.getCyclicDependency() != 0)
            {
                analysisEntity.setCyclicDependency(analysisDTO.getCyclomaticComplexity());
            }
            if(analysisDTO.getGodComponents() != 0)
            {
                analysisEntity.setGodComponents(analysisDTO.getGodComponents());
            }
            if(analysisDTO.getCyclicallyDependentModularization() != 0)
            {
                analysisEntity.setCyclicallyDependentModularization(analysisDTO.getCyclicallyDependentModularization());
            }
            if(analysisDTO.getInsufficientModularization() != 0)
            {
                analysisEntity.setInsufficientModularization(analysisDTO.getInsufficientModularization());
            }
            if(analysisDTO.getUnnecessaryAbstraction() != 0)
            {
                analysisEntity.setUnnecessaryAbstraction(analysisDTO.getUnnecessaryAbstraction());
            }
            if(analysisDTO.getComplexMethod() != 0)
            {
                analysisEntity.setComplexMethod(analysisDTO.getComplexMethod());
            }
            if(analysisDTO.getComplexConditional() != 0)
            {
                analysisEntity.setComplexConditional(analysisDTO.getComplexConditional());
            }
            if(analysisDTO.getEmptyCatchClause() != 0)
            {
                analysisEntity.setEmptyCatchClause(analysisDTO.getEmptyCatchClause());
            }
            if(analysisDTO.getMissingAssertion() != 0)
            {
                analysisEntity.setMissingAssertion(analysisDTO.getMissingAssertion());
            }
            if(analysisDTO.getEmptyTest() != 0)
            {
                analysisEntity.setEmptyTest(analysisDTO.getEmptyTest());
            }
            if(eventID != 0)
            {
                EventEntity eventEntity = new EventEntity();
                eventEntity.setEventId(eventID);
                analysisEntity.setEventIdInAnalysis(eventEntity);
            }
            Date date = new Date();
            analysisEntity.setTimestamp(date.getTime());
            LOGGER.info("convertAnalysisDtoToEntity : Converted AnalysisDTO to Entity {}", analysisEntity);
        }
        else {
            LOGGER.warn("convertAnalysisDtoToEntity : AnalysisDTO value is null");
        }
        return analysisEntity;
    }

    public AnalysisDTO convertAnalysisEntityToDto(AnalysisEntity analysisEntity) {
        AnalysisDTO analysisDTO = null;
        if(analysisEntity != null)
        {
            analysisDTO = new AnalysisDTO();
            if(analysisEntity.getType() != null)
            {
                analysisDTO.setAnalysisType(analysisEntity.getType());
            }
            if(analysisEntity.getBugs() != 0)
            {
                analysisDTO.setBugs(analysisEntity.getBugs());
            }
            if(analysisEntity.getVulnerabilities() != 0)
            {
                analysisDTO.setVulnerabilities(analysisEntity.getVulnerabilities());
            }
            if(analysisEntity.getCodeSmell() != 0)
            {
                analysisDTO.setCodeSmell(analysisEntity.getCodeSmell());
            }
            if(analysisEntity.getTestCoverage() != 0)
            {
                analysisDTO.setTestCoverage(analysisEntity.getTestCoverage());
            }
            if(analysisEntity.getDuplicatedLines() != 0)
            {
                analysisDTO.setDuplicatedLines(analysisEntity.getDuplicatedLines());
            }
            if(analysisEntity.getCyclomaticComplexity() != 0)
            {
                analysisDTO.setCyclomaticComplexity(analysisEntity.getCyclomaticComplexity());
            }
            if(analysisEntity.getDocumentedLines() != 0)
            {
                analysisDTO.setDocumentedLines(analysisEntity.getDocumentedLines());
            }
            if(analysisEntity.getCyclicDependency() != 0)
            {
                analysisDTO.setCyclicDependency(analysisEntity.getCyclomaticComplexity());
            }
            if(analysisEntity.getGodComponents() != 0)
            {
                analysisDTO.setGodComponents(analysisEntity.getGodComponents());
            }
            if(analysisEntity.getCyclicallyDependentModularization() != 0)
            {
                analysisDTO.setCyclicallyDependentModularization(analysisEntity.getCyclicallyDependentModularization());
            }
            if(analysisEntity.getInsufficientModularization() != 0)
            {
                analysisDTO.setInsufficientModularization(analysisEntity.getInsufficientModularization());
            }
            if(analysisEntity.getUnnecessaryAbstraction() != 0)
            {
                analysisDTO.setUnnecessaryAbstraction(analysisEntity.getUnnecessaryAbstraction());
            }
            if(analysisEntity.getComplexMethod() != 0)
            {
                analysisDTO.setComplexMethod(analysisEntity.getComplexMethod());
            }
            if(analysisEntity.getComplexConditional() != 0)
            {
                analysisDTO.setComplexConditional(analysisEntity.getComplexConditional());
            }
            if(analysisEntity.getEmptyCatchClause() != 0)
            {
                analysisDTO.setEmptyCatchClause(analysisEntity.getEmptyCatchClause());
            }
            if(analysisEntity.getMissingAssertion() != 0)
            {
                analysisDTO.setMissingAssertion(analysisEntity.getMissingAssertion());
            }
            if(analysisEntity.getEmptyTest() != 0)
            {
                analysisDTO.setEmptyTest(analysisEntity.getEmptyTest());
            }
            LOGGER.info("convertAnalysisDtoToEntity : Converted Analysis Entity to DTO {}", analysisDTO);
        } else {
            LOGGER.warn("convertAnalysisEntityToDto : Analysis Entity is null");
        }
        return analysisDTO;
    }
}

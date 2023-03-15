package com.github.codergate.services;

import com.github.codergate.dto.analysis.AnalysisDTO;
import com.github.codergate.entities.AnalysisEntity;
import com.github.codergate.entities.EventEntity;
import com.github.codergate.repositories.AnalysisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

@Service
public class AnalysisService {

    @Autowired
    AnalysisRepository analysisRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalysisService.class);

    public AnalysisDTO addAnalysis(AnalysisDTO analysisToAdd, long eventID) {
        AnalysisDTO analysisDTO = null;
        AnalysisEntity analysisEntity = convertAnalysisDtoToEntity(analysisToAdd, eventID);
        if(analysisEntity != null)
        {
            AnalysisEntity savedEntity = analysisRepository.save(analysisEntity);
            LOGGER.info("addAnalysis : The analysis information has been added {}", analysisEntity);
            analysisDTO = convertAnalysisEntityToDto(savedEntity);
        }
        return analysisDTO;
    }

    public AnalysisDTO getAnalysisByID(int analysisID) {
        AnalysisDTO analysisDTO = null;
        Optional<AnalysisEntity> analysisEntity = analysisRepository.findById(analysisID);
        if(analysisEntity.isPresent())
        {
            analysisDTO = convertAnalysisEntityToDto(analysisEntity.get());
            LOGGER.info("getAnalysisByID : Successfully retrieved account with ID {}", analysisDTO);
        } else {
            LOGGER.warn("getAnalysisByID : Analysis with ID {} not found", analysisID);
        }
        return analysisDTO;
    }

    public AnalysisDTO updateAnalysisByID(int analysisID, AnalysisDTO newInformationDto) {
        AnalysisDTO analysisDTO = null;
        AnalysisEntity analysisEntity = analysisRepository.findById(analysisID).orElse(null);
        if(analysisEntity != null)
        {
            AnalysisEntity updatedEntity = convertAnalysisDtoToEntity(newInformationDto, analysisEntity.getEventIdInAnalysis().getEventId());
            AnalysisEntity savedEntity = analysisRepository.save(updatedEntity);
            LOGGER.info("updateAnalysisByID : Updating the analysis information");
            analysisDTO = convertAnalysisEntityToDto(savedEntity);
        }
        return analysisDTO;
    }

    public boolean deleteAnalysisByID(int analysisID) {
        boolean isDeleted = false;
        if(analysisID != 0)
        {
            analysisRepository.deleteById(analysisID);
            isDeleted = true;
            LOGGER.info("deleteAnalysisByID : Deleting analysis information with ID {}", analysisID);
        }
        return isDeleted;
    }

    public AnalysisEntity convertAnalysisDtoToEntity(AnalysisDTO analysisDTO, long eventID) {
        AnalysisEntity analysisEntity = null;

        if(analysisDTO != null)
        {
            analysisEntity = new AnalysisEntity();
            if(analysisDTO.getAnalysisType() != null)
            {
                analysisEntity.setType(analysisDTO.getAnalysisType());
            }
            if(analysisDTO.getRepositoryID() != 0)
            {
                analysisEntity.setRepositoryID(analysisDTO.getRepositoryID());
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
            EventEntity eventEntity = new EventEntity();
            eventEntity.setEventId(eventID);
            analysisEntity.setEventIdInAnalysis(eventEntity);
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
            if(analysisEntity.getRepositoryID() != 0)
            {
                analysisDTO.setRepositoryID(analysisEntity.getRepositoryID());
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

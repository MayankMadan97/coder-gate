package com.github.codergate.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.codergate.dto.threshold.ThresholdDTO;
import com.github.codergate.entities.RepositoryEntity;
import com.github.codergate.entities.ThresholdEntity;
import com.github.codergate.repositories.ThresholdRepository;

@Service
public class ThresholdService {

    @Autowired
    ThresholdRepository thresholdRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ThresholdService.class);

    public ThresholdDTO addThreshold(ThresholdDTO thresholdToAdd, int repositoryID) {
        ThresholdDTO thresholdDTO = null;
        if (thresholdRepository.findByRepositoryId(repositoryID) != null) {
            updateThresholdByID(thresholdToAdd, repositoryID);
        } else {

            ThresholdEntity thresholdEntity = convertThresholdDtoToEntity(thresholdToAdd, repositoryID);
            if (thresholdEntity != null) {
                ThresholdEntity saveEntity = thresholdRepository.save(thresholdEntity);
                LOGGER.info("addThreshold : The threshold information has been added");
                thresholdDTO = convertThresholdEntityToDTO(saveEntity);
            }
        }
        return thresholdDTO;
    }

    public ThresholdDTO updateThresholdByID(ThresholdDTO newInformation, int repositoryID) {
        ThresholdDTO thresholdDTO = null;
        ThresholdEntity thresholdEntity = thresholdRepository.findByRepositoryId(repositoryID);
        if (thresholdEntity != null) {
            if (newInformation != null) {
                if (repositoryID != 0) {
                    RepositoryEntity repositoryEntity = new RepositoryEntity();
                    repositoryEntity.setRepositoryId(repositoryID);
                    thresholdEntity.setRepositoryIdInThreshold(repositoryEntity);
                }
                thresholdEntity.setAllowAction(newInformation.isAllowAction());
                thresholdEntity.setBugs(newInformation.getBugs());
                thresholdEntity.setVulnerabilities(newInformation.getVulnerabilities());
                thresholdEntity.setCodeSmell(newInformation.getCodeSmell());
                thresholdEntity.setTestCoverage(newInformation.getTestCoverage());
                thresholdEntity.setDuplicatedLines(newInformation.getDuplicatedLines());
                thresholdEntity.setCyclomaticComplexity(newInformation.getCyclomaticComplexity());
                thresholdEntity.setDocumentedLines(newInformation.getDocumentedLines());
                thresholdEntity.setCyclicDependency(newInformation.getCyclicDependency());
                thresholdEntity.setGodComponents(newInformation.getGodComponents());
                thresholdEntity.setCycDependentMod(newInformation.getCycDependentMod());
                thresholdEntity.setInsufficientModularization(newInformation.getInsufficientModularization());
                thresholdEntity.setUnnecessaryAbstraction(newInformation.getUnnecessaryAbstraction());
                thresholdEntity.setComplexConditional(newInformation.getComplexConditional());
                thresholdEntity.setEmptyCatchClause(newInformation.getEmptyCatchClause());
                thresholdEntity.setMissingAssertion(newInformation.getMissingAssertion());
                thresholdEntity.setEmptyTest(newInformation.getEmptyTest());
            }
            ThresholdEntity savedEntity = thresholdRepository.save(thresholdEntity);
            LOGGER.info("updateThresholdByID : Updating the threshold information with repositoryID {}", repositoryID);
            thresholdDTO = convertThresholdEntityToDTO(savedEntity);
        }
        return thresholdDTO;

    }

    public ThresholdDTO getThresholdByID(int repositoryID) {
        ThresholdDTO thresholdDTO = null;
        ThresholdEntity thresholdEntity = thresholdRepository.findByRepositoryId(repositoryID);
        if (thresholdEntity != null) {
            thresholdDTO = convertThresholdEntityToDTO(thresholdEntity);
            LOGGER.info("getThresholdByID : Successfully retrieved threshold with repositoryID {}", repositoryID);
        } else {
            LOGGER.warn("getThresholdByID : Threshold Entity with repositoryID {} not found", repositoryID);
        }
        return thresholdDTO;
    }

    public ThresholdEntity convertThresholdDtoToEntity(ThresholdDTO thresholdDTO, int repositoryID) {
        ThresholdEntity thresholdEntity = new ThresholdEntity();

        if (thresholdDTO != null) {
            if (repositoryID != 0) {
                RepositoryEntity repositoryEntity = new RepositoryEntity();
                repositoryEntity.setRepositoryId(repositoryID);
                thresholdEntity.setRepositoryIdInThreshold(repositoryEntity);
            }
            thresholdEntity.setAllowAction(thresholdDTO.isAllowAction());
            thresholdEntity.setBugs(thresholdDTO.getBugs());
            thresholdEntity.setVulnerabilities(thresholdDTO.getVulnerabilities());
            thresholdEntity.setCodeSmell(thresholdDTO.getCodeSmell());
            thresholdEntity.setTestCoverage(thresholdDTO.getTestCoverage());
            thresholdEntity.setDuplicatedLines(thresholdDTO.getDuplicatedLines());
            thresholdEntity.setCyclomaticComplexity(thresholdDTO.getCyclomaticComplexity());
            thresholdEntity.setDocumentedLines(thresholdDTO.getDocumentedLines());
            thresholdEntity.setCyclicDependency(thresholdDTO.getCyclicDependency());
            thresholdEntity.setGodComponents(thresholdDTO.getGodComponents());
            thresholdEntity.setCycDependentMod(thresholdDTO.getCycDependentMod());
            thresholdEntity.setInsufficientModularization(thresholdDTO.getInsufficientModularization());
            thresholdEntity.setUnnecessaryAbstraction(thresholdDTO.getUnnecessaryAbstraction());
            thresholdEntity.setComplexConditional(thresholdDTO.getComplexConditional());
            thresholdEntity.setEmptyCatchClause(thresholdDTO.getEmptyCatchClause());
            thresholdEntity.setMissingAssertion(thresholdDTO.getMissingAssertion());
            thresholdEntity.setEmptyTest(thresholdDTO.getEmptyTest());
            thresholdEntity.setArchSmellDensity(thresholdDTO.getArchSmellDensity());
            thresholdEntity.setDesignSmellDensity(thresholdDTO.getDesignSmellDensity());
            thresholdEntity.setImpSmellDensity(thresholdDTO.getImpSmellDensity());
            LOGGER.info("convertThresholdDtoToEntity : Converted Threshold DTO to Entity {}", thresholdEntity);
        } else {
            LOGGER.warn("convertThresholdDtoToEntity : ThresholdDTO value is null");
        }
        return thresholdEntity;
    }

    public ThresholdDTO convertThresholdEntityToDTO(ThresholdEntity thresholdEntity) {
        ThresholdDTO thresholdDTO = new ThresholdDTO();
        if (thresholdEntity != null) {
            thresholdDTO.setAllowAction(thresholdEntity.isAllowAction());
            thresholdDTO.setBugs(thresholdEntity.getBugs());
            thresholdDTO.setVulnerabilities(thresholdEntity.getVulnerabilities());
            thresholdDTO.setCodeSmell(thresholdEntity.getCodeSmell());
            thresholdDTO.setTestCoverage(thresholdEntity.getTestCoverage());
            thresholdDTO.setDuplicatedLines(thresholdEntity.getDuplicatedLines());
            thresholdDTO.setCyclomaticComplexity(thresholdEntity.getCyclomaticComplexity());
            thresholdDTO.setDocumentedLines(thresholdEntity.getDocumentedLines());
            thresholdDTO.setCyclicDependency(thresholdEntity.getCyclicDependency());
            thresholdDTO.setGodComponents(thresholdEntity.getGodComponents());
            thresholdDTO.setCycDependentMod(thresholdEntity.getCycDependentMod());
            thresholdDTO.setInsufficientModularization(thresholdEntity.getInsufficientModularization());
            thresholdDTO.setUnnecessaryAbstraction(thresholdEntity.getUnnecessaryAbstraction());
            thresholdDTO.setComplexConditional(thresholdEntity.getComplexConditional());
            thresholdDTO.setEmptyCatchClause(thresholdEntity.getEmptyCatchClause());
            thresholdDTO.setMissingAssertion(thresholdEntity.getMissingAssertion());
            thresholdDTO.setEmptyTest(thresholdEntity.getEmptyTest());
            thresholdDTO.setArchSmellDensity(thresholdEntity.getArchSmellDensity());
            thresholdDTO.setDesignSmellDensity(thresholdEntity.getDesignSmellDensity());
            thresholdDTO.setImpSmellDensity(thresholdEntity.getImpSmellDensity());
            LOGGER.info("convertThresholdDtoToEntity : Converted Threshold Entity to DTO {}", thresholdDTO);
        } else {
            LOGGER.warn("convertThresholdDtoToEntity : Threshold Entity is null");
        }
        return thresholdDTO;
    }

}

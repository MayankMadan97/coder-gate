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
                if (newInformation.getBugs() != 0) {
                    thresholdEntity.setBugs(newInformation.getBugs());
                }
                if (newInformation.getVulnerabilities() != 0) {
                    thresholdEntity.setVulnerabilities(newInformation.getVulnerabilities());
                }
                if (newInformation.getCodeSmell() != 0D) {
                    thresholdEntity.setCodeSmell(newInformation.getCodeSmell());
                }
                if (newInformation.getTestCoverage() != 0D) {
                    thresholdEntity.setTestCoverage(newInformation.getTestCoverage());
                }
                if (newInformation.getDuplicatedLines() != 0D) {
                    thresholdEntity.setDuplicatedLines(newInformation.getDuplicatedLines());
                }
                if (newInformation.getCyclomaticComplexity() != 0D) {
                    thresholdEntity.setCyclomaticComplexity(newInformation.getCyclomaticComplexity());
                }
                if (newInformation.getDocumentedLines() != 0D) {
                    thresholdEntity.setDocumentedLines(newInformation.getDocumentedLines());
                }
                if(newInformation.getCyclicDependency() != 0)
                {
                    thresholdEntity.setCyclicDependency(newInformation.getCyclicDependency());
                }
                if (newInformation.getGodComponents() != 0) {
                    thresholdEntity.setGodComponents(newInformation.getGodComponents());
                }

                if (newInformation.getInsufficientModularization() != 0) {
                    thresholdEntity.setInsufficientModularization(newInformation.getInsufficientModularization());
                }
                if (newInformation.getUnnecessaryAbstraction() != 0) {
                    thresholdEntity.setUnnecessaryAbstraction(newInformation.getUnnecessaryAbstraction());
                }
                if (newInformation.getComplexConditional() != 0) {
                    thresholdEntity.setComplexConditional(newInformation.getComplexConditional());
                }
                if (newInformation.getEmptyCatchClause() != 0) {
                    thresholdEntity.setEmptyCatchClause(newInformation.getEmptyCatchClause());
                }
                if (newInformation.getMissingAssertion() != 0) {
                    thresholdEntity.setMissingAssertion(newInformation.getMissingAssertion());
                }
                if (newInformation.getEmptyTest() != 0) {
                    thresholdEntity.setEmptyTest(newInformation.getEmptyTest());
                }
                if(newInformation.getArchSmellDensity() != 0D) {
                    thresholdEntity.setArchSmellDensity(newInformation.getArchSmellDensity());
                }
                if(newInformation.getDesignSmellDensity() != 0D) {
                    thresholdEntity.setDesignSmellDensity(newInformation.getDesignSmellDensity());
                }
                if(newInformation.getImpSmellDensity() != 0D) {
                    thresholdEntity.setImpSmellDensity(newInformation.getImpSmellDensity());
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
            if (thresholdDTO.getBugs() != 0) {
                thresholdEntity.setBugs(thresholdDTO.getBugs());
            }
            if (thresholdDTO.getVulnerabilities() != 0) {
                thresholdEntity.setVulnerabilities(thresholdDTO.getVulnerabilities());
            }
            if (thresholdDTO.getCodeSmell() != 0D) {
                thresholdEntity.setCodeSmell(thresholdDTO.getCodeSmell());
            }
            if (thresholdDTO.getTestCoverage() != 0D) {
                thresholdEntity.setTestCoverage(thresholdDTO.getTestCoverage());
            }
            if (thresholdDTO.getDuplicatedLines() != 0D) {
                thresholdEntity.setDuplicatedLines(thresholdDTO.getDuplicatedLines());
            }
            if (thresholdDTO.getCyclomaticComplexity() != 0D) {
                thresholdEntity.setCyclomaticComplexity(thresholdDTO.getCyclomaticComplexity());
            }
            if (thresholdDTO.getDocumentedLines() != 0D) {
                thresholdEntity.setDocumentedLines(thresholdDTO.getDocumentedLines());
            }
            if(thresholdDTO.getCyclicDependency() != 0)
            {
                thresholdEntity.setCyclicDependency(thresholdDTO.getCyclicDependency());
            }
            if (thresholdDTO.getGodComponents() != 0) {
                thresholdEntity.setGodComponents(thresholdDTO.getGodComponents());
            }

            if (thresholdDTO.getInsufficientModularization() != 0) {
                thresholdEntity.setInsufficientModularization(thresholdDTO.getInsufficientModularization());
            }
            if (thresholdDTO.getUnnecessaryAbstraction() != 0) {
                thresholdEntity.setUnnecessaryAbstraction(thresholdDTO.getUnnecessaryAbstraction());
            }
            if (thresholdDTO.getComplexConditional() != 0) {
                thresholdEntity.setComplexConditional(thresholdDTO.getComplexConditional());
            }
            if (thresholdDTO.getEmptyCatchClause() != 0) {
                thresholdEntity.setEmptyCatchClause(thresholdDTO.getEmptyCatchClause());
            }
            if (thresholdDTO.getMissingAssertion() != 0) {
                thresholdEntity.setMissingAssertion(thresholdDTO.getMissingAssertion());
            }
            if (thresholdDTO.getEmptyTest() != 0) {
                thresholdEntity.setEmptyTest(thresholdDTO.getEmptyTest());
            }
            if(thresholdDTO.getArchSmellDensity() != 0D) {
                thresholdEntity.setArchSmellDensity(thresholdDTO.getArchSmellDensity());
            }
            if(thresholdDTO.getDesignSmellDensity() != 0D) {
                thresholdEntity.setDesignSmellDensity(thresholdDTO.getDesignSmellDensity());
            }
            if(thresholdDTO.getImpSmellDensity() != 0D) {
                thresholdEntity.setImpSmellDensity(thresholdDTO.getImpSmellDensity());
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
            LOGGER.info("convertThresholdDtoToEntity : Converted Threshold DTO to Entity {}", thresholdEntity);
        } else {
            LOGGER.warn("convertThresholdDtoToEntity : ThresholdDTO value is null");
        }
        return thresholdEntity;
    }

    public ThresholdDTO convertThresholdEntityToDTO(ThresholdEntity thresholdEntity) {
        ThresholdDTO thresholdDTO = null;
        if (thresholdEntity != null) {
            thresholdDTO = new ThresholdDTO();
            if (thresholdEntity.getBugs() != 0) {
                thresholdDTO.setBugs(thresholdEntity.getBugs());
            }
            if (thresholdEntity.getVulnerabilities() != 0) {
                thresholdDTO.setVulnerabilities(thresholdEntity.getVulnerabilities());
            }
            if (thresholdEntity.getCodeSmell() != 0D) {
                thresholdDTO.setCodeSmell(thresholdEntity.getCodeSmell());
            }
            if (thresholdEntity.getTestCoverage() != 0D) {
                thresholdDTO.setTestCoverage(thresholdEntity.getTestCoverage());
            }
            if (thresholdEntity.getDuplicatedLines() != 0D) {
                thresholdDTO.setDuplicatedLines(thresholdEntity.getDuplicatedLines());
            }
            if (thresholdEntity.getCyclomaticComplexity() != 0D) {
                thresholdDTO.setCyclomaticComplexity(thresholdEntity.getCyclomaticComplexity());
            }
            if (thresholdEntity.getDocumentedLines() != 0D) {
                thresholdDTO.setDocumentedLines(thresholdEntity.getDocumentedLines());
            }
            if(thresholdEntity.getCyclicDependency() != 0)
            {
                thresholdDTO.setCyclicDependency(thresholdEntity.getCyclicDependency());
            }
            if (thresholdEntity.getGodComponents() != 0) {
                thresholdDTO.setGodComponents(thresholdEntity.getGodComponents());
            }

            if (thresholdEntity.getInsufficientModularization() != 0) {
                thresholdDTO.setInsufficientModularization(thresholdEntity.getInsufficientModularization());
            }
            if (thresholdEntity.getUnnecessaryAbstraction() != 0) {
                thresholdDTO.setUnnecessaryAbstraction(thresholdEntity.getUnnecessaryAbstraction());
            }
            if (thresholdEntity.getComplexConditional() != 0) {
                thresholdDTO.setComplexConditional(thresholdEntity.getComplexConditional());
            }
            if (thresholdEntity.getEmptyCatchClause() != 0) {
                thresholdDTO.setEmptyCatchClause(thresholdEntity.getEmptyCatchClause());
            }
            if (thresholdEntity.getMissingAssertion() != 0) {
                thresholdDTO.setMissingAssertion(thresholdEntity.getMissingAssertion());
            }
            if (thresholdEntity.getEmptyTest() != 0) {
                thresholdDTO.setEmptyTest(thresholdEntity.getEmptyTest());
            }
            if(thresholdEntity.getArchSmellDensity() != 0D) {
                thresholdDTO.setArchSmellDensity(thresholdEntity.getArchSmellDensity());
            }
            if(thresholdEntity.getDesignSmellDensity() != 0D) {
                thresholdDTO.setDesignSmellDensity(thresholdEntity.getDesignSmellDensity());
            }
            if(thresholdEntity.getImpSmellDensity() != 0D) {
                thresholdDTO.setImpSmellDensity(thresholdEntity.getImpSmellDensity());
            }
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
            LOGGER.info("convertThresholdDtoToEntity : Converted Threshold Entity to DTO {}", thresholdDTO);
        } else {
            LOGGER.warn("convertThresholdDtoToEntity : Threshold Entity is null");
        }
        return thresholdDTO;
    }

}

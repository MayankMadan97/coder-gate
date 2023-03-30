package com.github.codergate.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.codergate.dto.analysis.AnalysisDTO;
import com.github.codergate.dto.analysis.Designate;
import com.github.codergate.dto.analysis.Project;
import com.github.codergate.dto.analysis.Solution;
import com.github.codergate.entities.AnalysisEntity;
import com.github.codergate.repositories.AnalysisRepository;
import com.github.codergate.utils.Mapper;

@Service
public class AnalysisService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalysisService.class);

    @Autowired
    AnalysisRepository analysisRepository;

    @Autowired
    BranchService branchService;

    @Autowired
    RepositoryService repoService;

    public AnalysisDTO addAnalysis(AnalysisDTO analysisToAdd, long eventID, int repositoryID) {
        AnalysisDTO analysisDTO = null;
        AnalysisEntity analysisEntity = convertAnalysisDtoToEntity(analysisToAdd, eventID, repositoryID);
        if (analysisEntity != null) {
            AnalysisEntity savedEntity = analysisRepository.save(analysisEntity);
            LOGGER.info("addAnalysis : The analysis information has been added {}", analysisEntity);
            analysisDTO = convertAnalysisEntityToDto(savedEntity);
        }
        return analysisDTO;
    }

    public AnalysisDTO getAnalysisByID(int repositoryID) {
        AnalysisDTO analysisDTO = null;
        AnalysisEntity analysisEntity = analysisRepository.findAnalysisByRepositoryId(repositoryID);
        if (analysisEntity != null) {
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
        if (analysisEntity != null) {
            if (newInformation != null) {
                if (newInformation.getAnalysisType() != null) {
                    analysisEntity.setType(newInformation.getAnalysisType());
                }
                if (newInformation.getBugs() != 0) {
                    analysisEntity.setBugs(newInformation.getBugs());
                }
                if (newInformation.getVulnerabilities() != 0) {
                    analysisEntity.setVulnerabilities(newInformation.getVulnerabilities());
                }
                if (newInformation.getCodeSmell() != 0) {
                    analysisEntity.setCodeSmell(newInformation.getCodeSmell());
                }
                if (newInformation.getTestCoverage() != 0) {
                    analysisEntity.setTestCoverage(newInformation.getTestCoverage());
                }
                if (newInformation.getDuplicatedLines() != 0) {
                    analysisEntity.setDuplicatedLines(newInformation.getDuplicatedLines());
                }
                if (newInformation.getCyclomaticComplexity() != 0) {
                    analysisEntity.setCyclomaticComplexity(newInformation.getCyclomaticComplexity());
                }
                if (newInformation.getDocumentedLines() != 0) {
                    analysisEntity.setDocumentedLines(newInformation.getDocumentedLines());
                }
                if (newInformation.getCyclicDependency() != 0) {
                    analysisEntity.setCyclicDependency(newInformation.getCyclicDependency());
                }
                if (newInformation.getGodComponents() != 0) {
                    analysisEntity.setGodComponents(newInformation.getGodComponents());
                }
                if (newInformation.getCyclicallyDependentModularization() != 0) {
                    analysisEntity.setCyclicallyDependentModularization(
                            newInformation.getCyclicallyDependentModularization());
                }
                if (newInformation.getInsufficientModularization() != 0) {
                    analysisEntity.setInsufficientModularization(newInformation.getInsufficientModularization());
                }
                if (newInformation.getUnnecessaryAbstraction() != 0) {
                    analysisEntity.setUnnecessaryAbstraction(newInformation.getUnnecessaryAbstraction());
                }
                if (newInformation.getComplexConditional() != 0) {
                    analysisEntity.setComplexConditional(newInformation.getComplexConditional());
                }
                if (newInformation.getEmptyCatchClause() != 0) {
                    analysisEntity.setEmptyCatchClause(newInformation.getEmptyCatchClause());
                }
                if (newInformation.getMissingAssertion() != 0) {
                    analysisEntity.setMissingAssertion(newInformation.getMissingAssertion());
                }
                if (newInformation.getEmptyTest() != 0) {
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

    // public boolean deleteAnalysisByID(int repositoryID) {
    // boolean isDeleted = false;
    // if(repositoryID != 0)
    // {
    // analysisRepository.deleteByRepositoryId(repositoryID);
    // isDeleted = true;
    // LOGGER.info("deleteAnalysisByID : Deleting analysis information with
    // repositoryID {}", repositoryID);
    // }
    // return isDeleted;
    // }

    public AnalysisEntity convertAnalysisDtoToEntity(AnalysisDTO analysisDTO, long eventID, int repositoryID) {
        AnalysisEntity analysisEntity = null;

        if (analysisDTO != null) {
            analysisEntity = new AnalysisEntity();
            if (analysisDTO.getAnalysisType() != null) {
                analysisEntity.setType(analysisDTO.getAnalysisType());
            }
            if (analysisDTO.getBugs() != 0) {
                analysisEntity.setBugs(analysisDTO.getBugs());
            }
            if (analysisDTO.getVulnerabilities() != 0) {
                analysisEntity.setVulnerabilities(analysisDTO.getVulnerabilities());
            }
            if (analysisDTO.getCodeSmell() != 0) {
                analysisEntity.setCodeSmell(analysisDTO.getCodeSmell());
            }
            if (analysisDTO.getTestCoverage() != 0) {
                analysisEntity.setTestCoverage(analysisDTO.getTestCoverage());
            }
            if (analysisDTO.getDuplicatedLines() != 0) {
                analysisEntity.setDuplicatedLines(analysisDTO.getDuplicatedLines());
            }
            if (analysisDTO.getCyclomaticComplexity() != 0) {
                analysisEntity.setCyclomaticComplexity(analysisDTO.getCyclomaticComplexity());
            }
            if (analysisDTO.getDocumentedLines() != 0) {
                analysisEntity.setDocumentedLines(analysisDTO.getDocumentedLines());
            }
            if (analysisDTO.getCyclicDependency() != 0) {
                analysisEntity.setCyclicDependency(analysisDTO.getCyclicDependency());
            }
            if (analysisDTO.getGodComponents() != 0) {
                analysisEntity.setGodComponents(analysisDTO.getGodComponents());
            }
            if (analysisDTO.getCyclicallyDependentModularization() != 0) {
                analysisEntity.setCyclicallyDependentModularization(analysisDTO.getCyclicallyDependentModularization());
            }
            if (analysisDTO.getInsufficientModularization() != 0) {
                analysisEntity.setInsufficientModularization(analysisDTO.getInsufficientModularization());
            }
            if (analysisDTO.getUnnecessaryAbstraction() != 0) {
                analysisEntity.setUnnecessaryAbstraction(analysisDTO.getUnnecessaryAbstraction());
            }
            if (analysisDTO.getComplexConditional() != 0) {
                analysisEntity.setComplexConditional(analysisDTO.getComplexConditional());
            }
            if (analysisDTO.getEmptyCatchClause() != 0) {
                analysisEntity.setEmptyCatchClause(analysisDTO.getEmptyCatchClause());
            }
            if (analysisDTO.getMissingAssertion() != 0) {
                analysisEntity.setMissingAssertion(analysisDTO.getMissingAssertion());
            }
            if (analysisDTO.getEmptyTest() != 0) {
                analysisEntity.setEmptyTest(analysisDTO.getEmptyTest());
            }
            Date date = new Date();
            analysisEntity.setTimestamp(date.getTime());
            LOGGER.info("convertAnalysisDtoToEntity : Converted AnalysisDTO to Entity {}", analysisEntity);
        } else {
            LOGGER.warn("convertAnalysisDtoToEntity : AnalysisDTO value is null");
        }
        return analysisEntity;
    }

    public AnalysisDTO convertAnalysisEntityToDto(AnalysisEntity analysisEntity) {
        AnalysisDTO analysisDTO = null;
        if (analysisEntity != null) {
            analysisDTO = new AnalysisDTO();
            if (analysisEntity.getType() != null) {
                analysisDTO.setAnalysisType(analysisEntity.getType());
            }
            if (analysisEntity.getBugs() != 0) {
                analysisDTO.setBugs(analysisEntity.getBugs());
            }
            if (analysisEntity.getVulnerabilities() != 0) {
                analysisDTO.setVulnerabilities(analysisEntity.getVulnerabilities());
            }
            if (analysisEntity.getCodeSmell() != 0) {
                analysisDTO.setCodeSmell(analysisEntity.getCodeSmell());
            }
            if (analysisEntity.getTestCoverage() != 0) {
                analysisDTO.setTestCoverage(analysisEntity.getTestCoverage());
            }
            if (analysisEntity.getDuplicatedLines() != 0) {
                analysisDTO.setDuplicatedLines(analysisEntity.getDuplicatedLines());
            }
            if (analysisEntity.getCyclomaticComplexity() != 0) {
                analysisDTO.setCyclomaticComplexity(analysisEntity.getCyclomaticComplexity());
            }
            if (analysisEntity.getDocumentedLines() != 0) {
                analysisDTO.setDocumentedLines(analysisEntity.getDocumentedLines());
            }
            if (analysisEntity.getCyclicDependency() != 0) {
                analysisDTO.setCyclicDependency(analysisEntity.getCyclicDependency());
            }
            if (analysisEntity.getGodComponents() != 0) {
                analysisDTO.setGodComponents(analysisEntity.getGodComponents());
            }
            if (analysisEntity.getCyclicallyDependentModularization() != 0) {
                analysisDTO.setCyclicallyDependentModularization(analysisEntity.getCyclicallyDependentModularization());
            }
            if (analysisEntity.getInsufficientModularization() != 0) {
                analysisDTO.setInsufficientModularization(analysisEntity.getInsufficientModularization());
            }
            if (analysisEntity.getUnnecessaryAbstraction() != 0) {
                analysisDTO.setUnnecessaryAbstraction(analysisEntity.getUnnecessaryAbstraction());
            }
            if (analysisEntity.getComplexConditional() != 0D) {
                analysisDTO.setComplexConditional(analysisEntity.getComplexConditional());
            }
            if (analysisEntity.getEmptyCatchClause() != 0D) {
                analysisDTO.setEmptyCatchClause(analysisEntity.getEmptyCatchClause());
            }
            if (analysisEntity.getMissingAssertion() != 0) {
                analysisDTO.setMissingAssertion(analysisEntity.getMissingAssertion());
            }
            if (analysisEntity.getEmptyTest() != 0) {
                analysisDTO.setEmptyTest(analysisEntity.getEmptyTest());
            }
            LOGGER.info("convertAnalysisDtoToEntity : Converted Analysis Entity to DTO {}", analysisDTO);
        } else {
            LOGGER.warn("convertAnalysisEntityToDto : Analysis Entity is null");
        }
        return analysisDTO;
    }

    public AnalysisEntity processAnalysis(MultipartFile file, int repoId, String branchName) throws IOException {
        AnalysisEntity processedAnalysis = null;
        try (InputStream inputStream = file.getInputStream()) {
            String xml = new String(inputStream.readAllBytes());
            JSONObject jsonifiedXML = XML.toJSONObject(xml);
            Designate jsonifiedAnalysis = Mapper.getInstance().readValue(jsonifiedXML.toString(), Designate.class);
            if (jsonifiedAnalysis != null && jsonifiedAnalysis.getAnalysis() != null
                    && jsonifiedAnalysis.getAnalysis().getSolution() != null) {
                Solution solution = jsonifiedAnalysis.getAnalysis().getSolution();
                if (solution.getProject() != null) {
                    Project project = solution.getProject();
                    branchService.addBranch(branchName, repoId);
                    double complexityDensity = getCyclomaticComplexity(solution.getProject(),
                            solution.getMethodCount());
                    int cyclicArchDependencies = getSmells(project.getArchSmells().getArchSmell(), "Cyclic Dependency")
                            .size();
                    int godComponentArchDependencies = getSmells(project.getArchSmells().getArchSmell(),
                            "God Component").size();
                    int complexConditionalImpSmells = getSmells(
                            project.getImplementationSmells().getImplementationSmell(),
                            "Complex Conditional").size();
                    int emptyCatchClauseImpSmells = getSmells(
                            project.getImplementationSmells().getImplementationSmell(), "Empty Catch Clause")
                            .size();
                    int cyclicallyDependentDsSmells = getSmells(
                            project.getDesignSmells().getDesignSmell(), "Cyclically-dependent Modularization")
                            .size();
                    int insufficientModularizationDsSmells = getSmells(
                            project.getDesignSmells().getDesignSmell(), "Insufficient Modularization")
                            .size();
                    int unnecessaryAbstractionDsSmells = getSmells(
                            project.getDesignSmells().getDesignSmell(), "Unnecessary Abstraction")
                            .size();
                    int archSmells = solution.getTotalArchSmellCount();
                    int designSmells = solution.getTotalDesignSmellCount();
                    int impSmells = solution.getTotalImplSmellCount();
                    AnalysisEntity analysisEntity = new AnalysisEntity(repoId, branchName, solution.getSmellDensity(),
                            solution.getCodeDuplication(), System.currentTimeMillis());
                    analysisEntity.setCyclomaticComplexity(complexityDensity);
                    analysisEntity.setCyclicDependency(cyclicArchDependencies);
                    analysisEntity.setGodComponents(godComponentArchDependencies);
                    analysisEntity.setComplexConditional(complexConditionalImpSmells);
                    analysisEntity.setEmptyCatchClause(emptyCatchClauseImpSmells);
                    analysisEntity.setCyclicallyDependentModularization(cyclicallyDependentDsSmells);
                    analysisEntity.setInsufficientModularization(insufficientModularizationDsSmells);
                    analysisEntity.setUnnecessaryAbstraction(unnecessaryAbstractionDsSmells);
                    analysisEntity.setArchSmellDensity(
                            archSmells > 0 ? (double) archSmells / solution.getLoc() : archSmells);
                    analysisEntity.setDesignSmellDensity(
                            designSmells > 0 ? (double) designSmells / solution.getLoc() : designSmells);
                    analysisEntity.setImpSmellDensity(
                            impSmells > 0 ? (double) impSmells / solution.getLoc() : impSmells);
                    processedAnalysis = analysisRepository.save(analysisEntity);
                }
            }
            return processedAnalysis;
        }
    }

    private double getCyclomaticComplexity(Project projectOutput, int methodCount) {
        LOGGER.debug("getCyclomaticComplexity :: Entering the method");
        double complexity = 0.0;
        if (projectOutput != null && projectOutput.getImplementationSmells() != null && methodCount != 0) {
            int complextMethodSmells = projectOutput.getImplementationSmells().getImplementationSmell().stream()
                    .filter(smell -> smell != null && smell.getName() != null)
                    .map(smell -> smell.getName().contains("Complex Method"))
                    .collect(Collectors.toList()).size();
            if (complextMethodSmells > 0) {
                complexity = ((double) complextMethodSmells / methodCount) * 100;
            }
        }
        LOGGER.debug("getCyclomaticComplexity :: Exiting the method with complexity {}", complexity);
        return complexity;
    }

    private <T> List<T> getSmells(List<T> smellList, String smellName) {
        LOGGER.debug("getCyclicDependencies :: Entering the method");
        if (smellList != null && !smellList.isEmpty()) {
            return smellList.stream()
                    .filter(smell -> {
                        Map<String, Object> map = Mapper.getInstance().convertValue(smell, new TypeReference<Map>() {

                        });
                        return map != null && map.get("Name") != null
                                && map.get("Name").toString().equalsIgnoreCase(smellName);
                    })
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

}

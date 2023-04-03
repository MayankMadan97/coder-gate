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
import com.github.codergate.dto.analysis.ArchSmell;
import com.github.codergate.dto.analysis.DesignSmell;
import com.github.codergate.dto.analysis.Designate;
import com.github.codergate.dto.analysis.ImplementationSmell;
import com.github.codergate.dto.analysis.Project;
import com.github.codergate.dto.analysis.Solution;
import com.github.codergate.entities.AnalysisEntity;
import com.github.codergate.entities.BranchEntity;
import com.github.codergate.repositories.AnalysisRepository;
import com.github.codergate.utils.Mapper;

@Service
public class AnalysisService {

    @Autowired
    AnalysisRepository analysisRepository;

    @Autowired
    BranchService branchService;

    private static final int HUNDRED = 100;
    private static final String NAME = "Name";
    private static final String GOD_COMPONENT = "God Component";
    private static final String COMPLEX_METHOD = "Complex Method";
    private static final String CYC_DEPENDENCY = "Cyclic Dependency";
    private static final String EMPTY_CATCH_CLAUSE = "Empty Catch Clause";
    private static final String COMPLEX_CONDITIONAL = "Complex Conditional";
    private static final String UNNECESSARY_ABST = "Unnecessary Abstraction";
    private static final String INSUFFICIENT_MOD = "Insufficient Modularization";
    private static final String CYC_DEPENDENT_MOD = "Cyclically-dependent Modularization";

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalysisService.class);

    /**
     * @param repositoryID
     * @return AnalysisDTO
     */
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

    public AnalysisDTO getLatestAnalysis(int repoId) {
        return convertAnalysisEntityToDto(analysisRepository.findLatestAnalysisByRepositoryId(repoId));
    }

    /**
     * @param repositoryID
     * @param newInformation
     * @return AnalysisDTO
     */
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
                if (newInformation.getCodeSmell() != 0D) {
                    analysisEntity.setCodeSmell(newInformation.getCodeSmell());
                }
                if (newInformation.getTestCoverage() != 0D) {
                    analysisEntity.setTestCoverage(newInformation.getTestCoverage());
                }
                if (newInformation.getDuplicatedLines() != 0D) {
                    analysisEntity.setDuplicatedLines(newInformation.getDuplicatedLines());
                }
                if (newInformation.getCyclomaticComplexity() != 0D) {
                    analysisEntity.setCyclomaticComplexity(newInformation.getCyclomaticComplexity());
                }
                if (newInformation.getDocumentedLines() != 0D) {
                    analysisEntity.setDocumentedLines(newInformation.getDocumentedLines());
                }
                if (newInformation.getCyclicDependency() != 0) {
                    analysisEntity.setCyclicDependency(newInformation.getCyclicDependency());
                }
                if (newInformation.getGodComponents() != 0) {
                    analysisEntity.setGodComponents(newInformation.getGodComponents());
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
                if (newInformation.getArchSmellDensity() != 0D) {
                    analysisEntity.setArchSmellDensity(newInformation.getArchSmellDensity());
                }
                if (newInformation.getDesignSmellDensity() != 0D) {
                    analysisEntity.setDesignSmellDensity(newInformation.getDesignSmellDensity());
                }
                if (newInformation.getImpSmellDensity() != 0D) {
                    analysisEntity.setImpSmellDensity(newInformation.getImpSmellDensity());
                }
//                Date date = new Date();
//                analysisEntity.setTimestamp(date.getTime());
                analysisEntity.setType(newInformation.getAnalysisType());
                analysisEntity.setBugs(newInformation.getBugs());
                analysisEntity.setVulnerabilities(newInformation.getVulnerabilities());
                analysisEntity.setCodeSmell(newInformation.getCodeSmell());
                analysisEntity.setTestCoverage(newInformation.getTestCoverage());
                analysisEntity.setDuplicatedLines(newInformation.getDuplicatedLines());
                analysisEntity.setCyclomaticComplexity(newInformation.getCyclomaticComplexity());
                analysisEntity.setDocumentedLines(newInformation.getDocumentedLines());
                analysisEntity.setCyclicDependency(newInformation.getCyclicDependency());
                analysisEntity.setGodComponents(newInformation.getGodComponents());
                analysisEntity.setCyclicallyDependentModularization(newInformation.getCycDependentMod());
                analysisEntity.setInsufficientModularization(newInformation.getInsufficientModularization());
                analysisEntity.setUnnecessaryAbstraction(newInformation.getUnnecessaryAbstraction());
                analysisEntity.setComplexConditional(newInformation.getComplexConditional());
                analysisEntity.setEmptyCatchClause(newInformation.getEmptyCatchClause());
                analysisEntity.setMissingAssertion(newInformation.getMissingAssertion());
                analysisEntity.setEmptyTest(newInformation.getEmptyTest());
                analysisEntity.setTimestamp(new Date().getTime());
            }
            AnalysisEntity savedEntity = analysisRepository.save(analysisEntity);
            LOGGER.info("updateAnalysisByID : Updating the analysis information with repositoryID {}", repositoryID);
            analysisDTO = convertAnalysisEntityToDto(savedEntity);
        }
        return analysisDTO;
    }

    public AnalysisEntity convertAnalysisDtoToEntity(AnalysisDTO analysisDTO) {
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
            if (analysisDTO.getCodeSmell() != 0D) {
                analysisEntity.setCodeSmell(analysisDTO.getCodeSmell());
            }
            if (analysisDTO.getTestCoverage() != 0D) {
                analysisEntity.setTestCoverage(analysisDTO.getTestCoverage());
            }
            if (analysisDTO.getDuplicatedLines() != 0D) {
                analysisEntity.setDuplicatedLines(analysisDTO.getDuplicatedLines());
            }
            if (analysisDTO.getCyclomaticComplexity() != 0D) {
                analysisEntity.setCyclomaticComplexity(analysisDTO.getCyclomaticComplexity());
            }
            if (analysisDTO.getDocumentedLines() != 0D) {
                analysisEntity.setDocumentedLines(analysisDTO.getDocumentedLines());
            }
            if (analysisDTO.getCyclicDependency() != 0) {
                analysisEntity.setCyclicDependency(analysisDTO.getCyclicDependency());
            }
            if (analysisDTO.getGodComponents() != 0) {
                analysisEntity.setGodComponents(analysisDTO.getGodComponents());
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
            if (analysisDTO.getArchSmellDensity() != 0D) {
                analysisEntity.setArchSmellDensity(analysisDTO.getArchSmellDensity());
            }
            if (analysisDTO.getDesignSmellDensity() != 0D) {
                analysisEntity.setDesignSmellDensity(analysisDTO.getDesignSmellDensity());
            }
            if (analysisDTO.getImpSmellDensity() != 0D) {
                analysisEntity.setImpSmellDensity(analysisDTO.getImpSmellDensity());
            }
//            Date date = new Date();
//            analysisEntity.setTimestamp(date.getTime());
            analysisEntity.setType(analysisDTO.getAnalysisType());
            analysisEntity.setBugs(analysisDTO.getBugs());
            analysisEntity.setVulnerabilities(analysisDTO.getVulnerabilities());
            analysisEntity.setCodeSmell(analysisDTO.getCodeSmell());
            analysisEntity.setTestCoverage(analysisDTO.getTestCoverage());
            analysisEntity.setDuplicatedLines(analysisDTO.getDuplicatedLines());
            analysisEntity.setCyclomaticComplexity(analysisDTO.getCyclomaticComplexity());
            analysisEntity.setDocumentedLines(analysisDTO.getDocumentedLines());
            analysisEntity.setCyclicDependency(analysisDTO.getCyclicDependency());
            analysisEntity.setGodComponents(analysisDTO.getGodComponents());
            analysisEntity.setCyclicallyDependentModularization(analysisDTO.getCycDependentMod());
            analysisEntity.setInsufficientModularization(analysisDTO.getInsufficientModularization());
            analysisEntity.setUnnecessaryAbstraction(analysisDTO.getUnnecessaryAbstraction());
            analysisEntity.setComplexConditional(analysisDTO.getComplexConditional());
            analysisEntity.setEmptyCatchClause(analysisDTO.getEmptyCatchClause());
            analysisEntity.setMissingAssertion(analysisDTO.getMissingAssertion());
            analysisEntity.setEmptyTest(analysisDTO.getEmptyTest());
            analysisEntity.setTimestamp(new Date().getTime());
            analysisEntity.setBranchId(new BranchEntity(null, null));
            LOGGER.info("convertAnalysisDtoToEntity : Converted AnalysisDTO to Entity {}", analysisEntity);
        } else {
            LOGGER.warn("convertAnalysisDtoToEntity : AnalysisDTO value is null");
        }
        return analysisEntity;
    }

    /**
     * @param analysisEntity
     * @return AnalysisDTO
     */
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
            if (analysisEntity.getCodeSmell() != 0D) {
                analysisDTO.setCodeSmell(analysisEntity.getCodeSmell());
            }
            if (analysisEntity.getTestCoverage() != 0D) {
                analysisDTO.setTestCoverage(analysisEntity.getTestCoverage());
            }
            if (analysisEntity.getDuplicatedLines() != 0D) {
                analysisDTO.setDuplicatedLines(analysisEntity.getDuplicatedLines());
            }
            if (analysisEntity.getCyclomaticComplexity() != 0D) {
                analysisDTO.setCyclomaticComplexity(analysisEntity.getCyclomaticComplexity());
            }
            if (analysisEntity.getDocumentedLines() != 0D) {
                analysisDTO.setDocumentedLines(analysisEntity.getDocumentedLines());
            }
            if (analysisEntity.getCyclicDependency() != 0) {
                analysisDTO.setCyclicDependency(analysisEntity.getCyclicDependency());
            }
            if (analysisEntity.getGodComponents() != 0) {
                analysisDTO.setGodComponents(analysisEntity.getGodComponents());
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
            if (analysisEntity.getArchSmellDensity() != 0D) {
                analysisDTO.setArchSmellDensity(analysisEntity.getArchSmellDensity());
            }
            if (analysisEntity.getDesignSmellDensity() != 0D) {
                analysisDTO.setDesignSmellDensity(analysisEntity.getDesignSmellDensity());
            }
            if (analysisEntity.getImpSmellDensity() != 0D) {
                analysisDTO.setImpSmellDensity(analysisEntity.getImpSmellDensity());
            }
            LOGGER.info("convertAnalysisDtoToEntity : Converted Analysis Entity to DTO {}", analysisDTO);
        } else {
            LOGGER.warn("convertAnalysisEntityToDto : Analysis Entity is null");
            analysisDTO.setAnalysisType(analysisEntity.getType());
            analysisDTO.setBugs(analysisEntity.getBugs());
            analysisDTO.setVulnerabilities(analysisEntity.getVulnerabilities());
            analysisDTO.setCodeSmell(analysisEntity.getCodeSmell());
            analysisDTO.setTestCoverage(analysisEntity.getTestCoverage());
            analysisDTO.setDuplicatedLines(analysisEntity.getDuplicatedLines());
            analysisDTO.setCyclomaticComplexity(analysisEntity.getCyclomaticComplexity());
            analysisDTO.setDocumentedLines(analysisEntity.getDocumentedLines());
            analysisDTO.setCyclicDependency(analysisEntity.getCyclicDependency());
            analysisDTO.setGodComponents(analysisEntity.getGodComponents());
            analysisDTO.setCycDependentMod(analysisEntity.getCyclicallyDependentModularization());
            analysisDTO.setInsufficientModularization(analysisEntity.getInsufficientModularization());
            analysisDTO.setUnnecessaryAbstraction(analysisEntity.getUnnecessaryAbstraction());
            analysisDTO.setComplexConditional(analysisEntity.getComplexConditional());
            analysisDTO.setEmptyCatchClause(analysisEntity.getEmptyCatchClause());
            analysisDTO.setMissingAssertion(analysisEntity.getMissingAssertion());
            analysisDTO.setEmptyTest(analysisEntity.getEmptyTest());
            analysisDTO.setTimestamp(analysisEntity.getTimestamp());
        }
        LOGGER.info("convertAnalysisDtoToEntity :: Exiting the method with {}", analysisDTO);
        return analysisDTO;
    }

    /**
     * @param file
     * @param repoId
     * @param branchName
     * @return AnalysisEntity
     * @throws IOException
     */
    public AnalysisEntity processAnalysis(MultipartFile file, int repoId, String branchName) throws IOException {
        AnalysisEntity processedAnalysis = null;
        try (InputStream inputStream = file.getInputStream()) {
            String xml = new String(inputStream.readAllBytes());
            JSONObject jsonifiedXML = XML.toJSONObject(xml);
            Designate jsonifiedAnalysis = Mapper.getInstance().readValue(jsonifiedXML.toString(), Designate.class);
            if (jsonifiedAnalysis.getAnalysis() != null && jsonifiedAnalysis.getAnalysis().getSolution() != null) {
                Solution solution = jsonifiedAnalysis.getAnalysis().getSolution();
                if (solution.getProject() != null) {
                    Project project = solution.getProject();
                    branchService.addBranch(branchName, repoId);
                    double complexityDensity = getCyclomaticComplexity(solution.getProject(),
                            solution.getMethodCount());
                    List<ArchSmell> archSmell = project.getArchSmells().getArchSmell();
                    int cycArchDependencies = getSmells(archSmell, CYC_DEPENDENCY).size();
                    int godCompArchDependencies = getSmells(archSmell, GOD_COMPONENT).size();
                    List<ImplementationSmell> implementationSmell = project.getImplementationSmells()
                            .getImplementationSmell();
                    int complexCondImpSmells = getSmells(implementationSmell, COMPLEX_CONDITIONAL).size();
                    int emptyCatchClauseImpSmells = getSmells(implementationSmell, EMPTY_CATCH_CLAUSE).size();
                    List<DesignSmell> designSmell = project.getDesignSmells().getDesignSmell();
                    int cycDependentDsSmells = getSmells(designSmell, CYC_DEPENDENT_MOD).size();
                    int insufficientModDsSmells = getSmells(designSmell, INSUFFICIENT_MOD).size();
                    int unnecessaryAbsDsSmells = getSmells(designSmell, UNNECESSARY_ABST).size();
                    int archSmells = solution.getTotalArchSmellCount();
                    int designSmells = solution.getTotalDesignSmellCount();
                    int impSmells = solution.getTotalImplSmellCount();
                    AnalysisEntity analysisEntity = new AnalysisEntity(repoId, branchName, System.currentTimeMillis());
                    analysisEntity.setCodeSmell(solution.getSmellDensity());
                    analysisEntity.setDuplicatedLines(solution.getCodeDuplication());
                    analysisEntity.setCyclomaticComplexity(complexityDensity);
                    analysisEntity.setCyclicDependency(cycArchDependencies);
                    analysisEntity.setGodComponents(godCompArchDependencies);
                    analysisEntity.setComplexConditional(complexCondImpSmells);
                    analysisEntity.setEmptyCatchClause(emptyCatchClauseImpSmells);
                    analysisEntity.setCyclicallyDependentModularization(cycDependentDsSmells);
                    analysisEntity.setInsufficientModularization(insufficientModDsSmells);
                    analysisEntity.setUnnecessaryAbstraction(unnecessaryAbsDsSmells);
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

    /**
     * method calculates project wide cyclomatic complexity
     * 
     * @param projectOutput
     * @param methodCount
     * @return double
     */
    private double getCyclomaticComplexity(Project projectOutput, int methodCount) {
        LOGGER.debug("getCyclomaticComplexity :: Entering the method");
        double complexity = 0.0;
        if (projectOutput.getImplementationSmells() != null && methodCount != 0) {
            int complextMethodSmells = projectOutput.getImplementationSmells().getImplementationSmell().stream()
                    .filter(smell -> smell != null && smell.getName() != null)
                    .map(smell -> smell.getName().contains(COMPLEX_METHOD))
                    .collect(Collectors.toList()).size();
            if (complextMethodSmells > 0) {
                complexity = ((double) complextMethodSmells / methodCount) * HUNDRED;
            }
        }
        LOGGER.debug("getCyclomaticComplexity :: Exiting the method with complexity {}", complexity);
        return complexity;
    }

    /**
     * get smells of type T from analysis
     * 
     * @param smellList
     * @param smellName
     * @return List<T>
     */
    private <T> List<T> getSmells(List<T> smellList, String smellName) {
        LOGGER.debug("getCyclicDependencies :: Entering the method");
        if (smellList != null && !smellList.isEmpty()) {
            return smellList.stream()
                    .filter(smell -> {
                        Map<String, Object> map = Mapper.getInstance().convertValue(smell,
                                new TypeReference<Map<String, Object>>() {
                                });
                        return map != null && map.get(NAME) != null
                                && map.get(NAME).toString().equalsIgnoreCase(smellName);
                    })
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

}

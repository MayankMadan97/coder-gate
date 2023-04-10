package com.github.codergate.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
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

    public AnalysisDTO getLatestAnalysis(int repoId) {
        return convertAnalysisEntityToDto(analysisRepository.findLatestAnalysisByRepositoryId(repoId));
    }

    /**
     * @param analysisEntity
     * @return AnalysisDTO
     */
    public AnalysisDTO convertAnalysisEntityToDto(AnalysisEntity analysisEntity) {
        AnalysisDTO analysisDTO = new AnalysisDTO();
        if (analysisEntity != null) {
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
            analysisDTO.setArchSmellDensity(analysisEntity.getArchSmellDensity());
            analysisDTO.setDesignSmellDensity(analysisEntity.getDesignSmellDensity());
            analysisDTO.setImpSmellDensity(analysisEntity.getImpSmellDensity());

            analysisDTO.setTimestamp(analysisEntity.getTimestamp());
            LOGGER.info("convertAnalysisDtoToEntity : Converted Analysis Entity to DTO {}", analysisDTO);
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
                Integer loc = solution.getLoc();
                if (solution.getProject() != null) {
                    Project project = solution.getProject();
                    branchService.addBranch(branchName, repoId);
                    double complexityDensity = getCyclomaticComplexity(solution.getProject(),
                            solution.getMethodCount());
                    AnalysisEntity analysisEntity = new AnalysisEntity(repoId, branchName, System.currentTimeMillis());
                    if (project.getArchSmells() != null) {
                        List<ArchSmell> archSmell = project.getArchSmells().getArchSmell();
                        int cycArchDependencies = getSmells(archSmell, CYC_DEPENDENCY).size();
                        int godCompArchDependencies = getSmells(archSmell, GOD_COMPONENT).size();
                        analysisEntity.setCyclicDependency(cycArchDependencies);
                        analysisEntity.setGodComponents(godCompArchDependencies);
                    }
                    if (project.getImplementationSmells() != null) {
                        List<ImplementationSmell> implementationSmell = project.getImplementationSmells()
                                .getImplementationSmell();
                        int complexCondImpSmells = getSmells(implementationSmell, COMPLEX_CONDITIONAL).size();
                        int emptyCatchClauseImpSmells = getSmells(implementationSmell, EMPTY_CATCH_CLAUSE).size();
                        analysisEntity.setComplexConditional(complexCondImpSmells);
                        analysisEntity.setEmptyCatchClause(emptyCatchClauseImpSmells);
                    }
                    if (project.getDesignSmells() != null) {
                        List<DesignSmell> designSmell = project.getDesignSmells().getDesignSmell();
                        int cycDependentDsSmells = getSmells(designSmell, CYC_DEPENDENT_MOD).size();
                        int insufficientModDsSmells = getSmells(designSmell, INSUFFICIENT_MOD).size();
                        int unnecessaryAbsDsSmells = getSmells(designSmell, UNNECESSARY_ABST).size();
                        analysisEntity.setCyclicallyDependentModularization(cycDependentDsSmells);
                        analysisEntity.setInsufficientModularization(insufficientModDsSmells);
                        analysisEntity.setUnnecessaryAbstraction(unnecessaryAbsDsSmells);
                    }
                    Integer methodCount = solution.getMethodCount();
                    Integer totalArchSmellCount = solution.getTotalArchSmellCount();
                    Integer totalDesignSmellCount = solution.getTotalDesignSmellCount();
                    Integer totalImplSmellCount = solution.getTotalImplSmellCount();
                    Integer componentCount = solution.getComponentCount();
                    Integer metricVoilations = solution.getMetricVoilations();
                    Integer typeCount = solution.getTypeCount();

                    analysisEntity.setLoc(loc);
                    analysisEntity.setMethodCount(methodCount);
                    analysisEntity.setTotalArchSmellCount(totalArchSmellCount);
                    analysisEntity.setTotalDesignSmellCount(totalDesignSmellCount);
                    analysisEntity.setTotalImplSmellCount(totalImplSmellCount);
                    analysisEntity.setComponentCount(solution.getComponentCount());
                    analysisEntity.setComponentCount(componentCount);
                    analysisEntity.setMetricVoilations(metricVoilations);
                    analysisEntity.setTypeCount(typeCount);
                    analysisEntity.setCodeSmell(solution.getSmellDensity());
                    analysisEntity.setDuplicatedLines(solution.getCodeDuplication());
                    analysisEntity.setCyclomaticComplexity(complexityDensity);
                    int archSmells = solution.getTotalArchSmellCount();
                    int designSmells = solution.getTotalDesignSmellCount();
                    int impSmells = solution.getTotalImplSmellCount();
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

package com.github.codergate.services;

import com.github.codergate.dto.analysis.AnalysisDTO;
import com.github.codergate.entities.AnalysisEntity;
import com.github.codergate.repositories.AnalysisRepository;
import com.google.protobuf.Message;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AnalysisServiceTest {

    private final double codeSmell = 4.30;
    private final double duplicatedLines5 = 0;
    private final double cyclomaticComplexity = 0.198938992;
    private final int cyclicDependency = 31;
    private final int godComponents = 1;
    private final int cycDependentMod = 80;
    private final int insufficientModularization = 70;
    private final int unnecessaryAbstraction = 60;
    private final int complexConditional = 50;
    private final int emptyCatchClause = 40;
    private final double archSmellDensity = 10.1;
    private final double designSmellDensity = 20.2;
    private final double impSmellDensity = 30.3;
    private final int repositoryID = 1;

    @Mock
    AnalysisRepository analysisRepository;

    @InjectMocks
    AnalysisService analysisService;

    private AnalysisEntity setValuesToAnalysisEntity() {
        AnalysisEntity analysisEntity = new AnalysisEntity();

        analysisEntity.setCodeSmell(codeSmell);
        analysisEntity.setDuplicatedLines(duplicatedLines5);
        analysisEntity.setCyclomaticComplexity(cyclomaticComplexity);

        analysisEntity.setCyclicDependency(cyclicDependency);
        analysisEntity.setGodComponents(godComponents);

        analysisEntity.setCyclicallyDependentModularization(cycDependentMod);
        analysisEntity.setInsufficientModularization(insufficientModularization);
        analysisEntity.setUnnecessaryAbstraction(unnecessaryAbstraction);

        analysisEntity.setComplexConditional(complexConditional);
        analysisEntity.setEmptyCatchClause(emptyCatchClause);

        analysisEntity.setArchSmellDensity(archSmellDensity);
        analysisEntity.setDesignSmellDensity(designSmellDensity);
        analysisEntity.setImpSmellDensity(impSmellDensity);

        return analysisEntity;
    }

    private AnalysisDTO setValuesToAnalysisDTO() {
        AnalysisDTO analysisDTO = new AnalysisDTO();

        analysisDTO.setCodeSmell(codeSmell);
        analysisDTO.setDuplicatedLines(duplicatedLines5);
        analysisDTO.setCyclomaticComplexity(cyclomaticComplexity);

        analysisDTO.setCyclicDependency(cyclicDependency);
        analysisDTO.setGodComponents(godComponents);

        analysisDTO.setCycDependentMod(cycDependentMod);
        analysisDTO.setInsufficientModularization(insufficientModularization);
        analysisDTO.setUnnecessaryAbstraction(unnecessaryAbstraction);

        analysisDTO.setComplexConditional(complexConditional);
        analysisDTO.setEmptyCatchClause(emptyCatchClause);

        analysisDTO.setArchSmellDensity(archSmellDensity);
        analysisDTO.setDesignSmellDensity(designSmellDensity);
        analysisDTO.setImpSmellDensity(impSmellDensity);

        return analysisDTO;
    }

    @Test
    public void testGetLatestAnalysis() {
        AnalysisDTO expectedDTO = setValuesToAnalysisDTO();
        int repoID = 101;
        AnalysisEntity expectedEntity = setValuesToAnalysisEntity();

        when(analysisRepository.findLatestAnalysisByRepositoryId(repoID)).thenReturn(expectedEntity);

        AnalysisDTO actual = analysisService.getLatestAnalysis(repoID);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedDTO);
    }

//    @Test
//    public void testProcessAnalysis() {
//        Path path = Paths.get("DesigniteAnalysis.xml");
//        String multipartFileName = "analysis.xml";
//        String originalFileName = "DesigniteAnalysis.xml";
//        byte[] content = null;
//        try {
//            content = Files.readAllBytes(path);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        MultipartFile file = new MockMultipartFile(multipartFileName, originalFileName, )
//    }
}
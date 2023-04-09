package com.github.codergate.services;

import com.github.codergate.dto.threshold.ThresholdDTO;
import com.github.codergate.entities.RepositoryEntity;
import com.github.codergate.entities.ThresholdEntity;
import com.github.codergate.repositories.ThresholdRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


//import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ThresholdServiceTest {

    private final int bugs = 10;
    private final int vulnerabilities = 20;
    private final double codeSmell = 30.5;
    private final double testCoverage = 40.6;
    private final double duplicatedLines = 0.7;
    private final double cyclomaticComplexity = 60.8;
    private final double documentedLines= 70.9;
    private final int cyclicDependency = 80;
    private final int godComponents = 90;
    private final int cycDependentMod = 80;
    private final int insufficientModularization = 70;
    private final int unnecessaryAbstraction = 60;
    private final int complexConditional = 50;
    private final int emptyCatchClause = 40;
    private final int missingAssertion = 30;
    private final int emptyTest = 20;
    private final double archSmellDensity = 10.1;
    private final double designSmellDensity = 20.2;
    private final double impSmellDensity = 30.3;
    private final int repositoryID = 1;

    @Mock
    ThresholdRepository thresholdRepositoryMock;

    @InjectMocks
    ThresholdService thresholdServiceMock;

    private ThresholdDTO setValuesToThresholdDTO() {
        ThresholdDTO thresholdDTO = new ThresholdDTO();
        thresholdDTO.setBugs(bugs);
        thresholdDTO.setVulnerabilities(vulnerabilities);
        thresholdDTO.setCodeSmell(codeSmell);
        thresholdDTO.setTestCoverage(testCoverage);
        thresholdDTO.setDuplicatedLines(duplicatedLines);
        thresholdDTO.setCyclomaticComplexity(cyclomaticComplexity);
        thresholdDTO.setDocumentedLines(documentedLines);
        thresholdDTO.setCyclicDependency(cyclicDependency);
        thresholdDTO.setGodComponents(godComponents);
        thresholdDTO.setCycDependentMod(cycDependentMod);
        thresholdDTO.setInsufficientModularization(insufficientModularization);
        thresholdDTO.setUnnecessaryAbstraction(unnecessaryAbstraction);
        thresholdDTO.setComplexConditional(complexConditional);
        thresholdDTO.setEmptyCatchClause(emptyCatchClause);
        thresholdDTO.setMissingAssertion(missingAssertion);
        thresholdDTO.setEmptyTest(emptyTest);
        thresholdDTO.setArchSmellDensity(archSmellDensity);
        thresholdDTO.setDesignSmellDensity(designSmellDensity);
        thresholdDTO.setImpSmellDensity(impSmellDensity);
        return thresholdDTO;
    }

    private ThresholdEntity setValuesToThresholdEntity() {
        ThresholdEntity thresholdEntity = new ThresholdEntity();
        thresholdEntity.setBugs(bugs);
        thresholdEntity.setVulnerabilities(vulnerabilities);
        thresholdEntity.setCodeSmell(codeSmell);
        thresholdEntity.setTestCoverage(testCoverage);
        thresholdEntity.setDuplicatedLines(duplicatedLines);
        thresholdEntity.setCyclomaticComplexity(cyclomaticComplexity);
        thresholdEntity.setDocumentedLines(documentedLines);
        thresholdEntity.setCyclicDependency(cyclicDependency);
        thresholdEntity.setGodComponents(godComponents);
        thresholdEntity.setCycDependentMod(cycDependentMod);
        thresholdEntity.setInsufficientModularization(insufficientModularization);
        thresholdEntity.setUnnecessaryAbstraction(unnecessaryAbstraction);
        thresholdEntity.setComplexConditional(complexConditional);
        thresholdEntity.setEmptyCatchClause(emptyCatchClause);
        thresholdEntity.setMissingAssertion(missingAssertion);
        thresholdEntity.setEmptyTest(emptyTest);
        thresholdEntity.setArchSmellDensity(archSmellDensity);
        thresholdEntity.setDesignSmellDensity(designSmellDensity);
        thresholdEntity.setImpSmellDensity(impSmellDensity);
        return thresholdEntity;
    }

    @Test
    void addThresholdWithAllData() {
        ThresholdDTO thresholdDTO = setValuesToThresholdDTO();
        ThresholdEntity expected = setValuesToThresholdEntity();
        RepositoryEntity repositoryEntity = new RepositoryEntity();
        repositoryEntity.setRepositoryId(repositoryID);
        expected.setRepositoryIdInThreshold(repositoryEntity);

        ArgumentCaptor<ThresholdEntity> entityCaptor = ArgumentCaptor.forClass(ThresholdEntity.class);
        when(thresholdRepositoryMock.save((entityCaptor.capture()))).thenReturn(expected);

        thresholdServiceMock.addThreshold(thresholdDTO, repositoryID);

        verify(thresholdRepositoryMock).save(entityCaptor.getValue());
        ThresholdEntity actual = entityCaptor.getValue();

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getThresholdByRepositoryIDWhenThresholdExistsForThatRepositoryID() {
        ThresholdEntity expected = setValuesToThresholdEntity();
        RepositoryEntity repositoryEntity = new RepositoryEntity();
        repositoryEntity.setRepositoryId(repositoryID);
        expected.setRepositoryIdInThreshold(repositoryEntity);

        when(thresholdRepositoryMock.findByRepositoryId(repositoryID)).thenReturn(expected);
        ThresholdDTO actual = thresholdServiceMock.getThresholdByID(repositoryID);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getThresholdByRepositoryIDWhenThresholdValuesDoNotExistForhatRepositoryID() {
        when(thresholdRepositoryMock.findByRepositoryId(repositoryID)).thenReturn(null);
        ThresholdDTO thresholdDTO = thresholdServiceMock.getThresholdByID(repositoryID);
        assertNull(thresholdDTO);
    }

    @Test
    void updateThresholdByID() {
        ThresholdEntity expected = setValuesToThresholdEntity();
        RepositoryEntity repositoryEntity = new RepositoryEntity();
        repositoryEntity.setRepositoryId(repositoryID);
        expected.setRepositoryIdInThreshold(repositoryEntity);
        ThresholdDTO updatedInformation = setValuesToThresholdDTO();
        when(thresholdRepositoryMock.findByRepositoryId(repositoryID)).thenReturn(expected);

        ArgumentCaptor<ThresholdEntity> entityCaptor = ArgumentCaptor.forClass(ThresholdEntity.class);
        when(thresholdRepositoryMock.save((entityCaptor.capture()))).thenReturn(expected);

        ThresholdDTO actual = thresholdServiceMock.updateThresholdByID(updatedInformation, repositoryID);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void addThresholdForExistingRepositoryID() {
        ThresholdEntity expected = setValuesToThresholdEntity();
        RepositoryEntity repositoryEntity = new RepositoryEntity();
        repositoryEntity.setRepositoryId(repositoryID);
        expected.setRepositoryIdInThreshold(repositoryEntity);

        ThresholdDTO thresholdDTO = setValuesToThresholdDTO();

        when(thresholdRepositoryMock.findByRepositoryId(repositoryID)).thenReturn(expected);

        ArgumentCaptor<ThresholdEntity> entityCaptor = ArgumentCaptor.forClass(ThresholdEntity.class);
        when(thresholdRepositoryMock.save((entityCaptor.capture()))).thenReturn(expected);

        ThresholdDTO actual = thresholdServiceMock.addThreshold(thresholdDTO, repositoryID);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
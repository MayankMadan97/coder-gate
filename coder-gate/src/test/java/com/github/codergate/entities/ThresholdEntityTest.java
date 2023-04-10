package com.github.codergate.entities;

import com.github.codergate.repositories.ThresholdRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ThresholdEntityTest {

    @Autowired
    ThresholdRepository thresholdRepository;
    @Autowired
    private TestEntityManager entityManager;

    int thresholdId =1;
    int repositoryId =123;
    int bugs = 100;
    int vulnerabilities = 80;
    double codeSmell =99;
    double testCoverage = 89;
    double duplicatedLines =12;
    double cyclomaticComplexity =12;
    double documentedLines =11;

    // Architecture Smells
    int cyclicDependency = 2;
    int godComponents = 0;

    // Design Smells
    int cycDependentMod = 1;
    int insufficientModularization= 5;
    int unnecessaryAbstraction = 8;

    // Implementation Smells
    int complexConditional = 9;
    int emptyCatchClause = 88;

    // Test Smells
    int missingAssertion =90;
    int emptyTest =1;

    // Densities
    double archSmellDensity =12;
    double designSmellDensity =76;
    double impSmellDensity =111;
    boolean action = true;


    @Test
    void testAllArgsConstructor() {
        RepositoryEntity repositoryIdInThreshold = new RepositoryEntity();


        repositoryIdInThreshold.setRepositoryId(repositoryId);

        ThresholdEntity thresholdEntity = new ThresholdEntity(thresholdId,repositoryIdInThreshold,bugs,vulnerabilities,codeSmell,
                testCoverage,duplicatedLines,cyclomaticComplexity,documentedLines,cyclicDependency,
                godComponents,cycDependentMod,insufficientModularization,unnecessaryAbstraction,complexConditional,
                emptyCatchClause,missingAssertion,emptyTest,archSmellDensity,designSmellDensity,impSmellDensity,action);

        assertEquals(thresholdId,thresholdEntity.getThresholdId());
        assertEquals(repositoryIdInThreshold,thresholdEntity.getRepositoryIdInThreshold());
        assertEquals(bugs,thresholdEntity.getBugs());
        assertEquals(vulnerabilities,thresholdEntity.getVulnerabilities());
        assertEquals(codeSmell,thresholdEntity.getCodeSmell());
        assertEquals(testCoverage,thresholdEntity.getTestCoverage());
        assertEquals(duplicatedLines,thresholdEntity.getDuplicatedLines());
        assertEquals(cyclomaticComplexity,thresholdEntity.getCyclomaticComplexity());
        assertEquals(documentedLines,thresholdEntity.getDocumentedLines());
        assertEquals(cyclicDependency,thresholdEntity.getCyclicDependency());
        assertEquals(godComponents,thresholdEntity.getGodComponents());
        assertEquals(cycDependentMod,thresholdEntity.getCycDependentMod());
        assertEquals(insufficientModularization,thresholdEntity.getInsufficientModularization());
        assertEquals(unnecessaryAbstraction,thresholdEntity.getUnnecessaryAbstraction());
        assertEquals(complexConditional,thresholdEntity.getComplexConditional());
        assertEquals(emptyCatchClause,thresholdEntity.getEmptyCatchClause());
        assertEquals(missingAssertion,thresholdEntity.getMissingAssertion());
        assertEquals(emptyTest,thresholdEntity.getEmptyTest());
        assertEquals(archSmellDensity,thresholdEntity.getArchSmellDensity());
        assertEquals(designSmellDensity,thresholdEntity.getDesignSmellDensity());
        assertEquals(impSmellDensity,thresholdEntity.getImpSmellDensity());

    }





}
package com.github.codergate.services.utility;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.codergate.entities.AnalysisEntity;
import com.github.codergate.entities.ThresholdEntity;
import com.github.codergate.utils.PullRequestUtil;

@ExtendWith(MockitoExtension.class)
public class PullRequestUtilTest {
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

    private AnalysisEntity setValuesToAnalysisEntity() {

        AnalysisEntity analysisEntity = new AnalysisEntity();

        analysisEntity.setBugs(bugs);
        analysisEntity.setVulnerabilities(vulnerabilities);
        analysisEntity.setCodeSmell(codeSmell);
        analysisEntity.setDuplicatedLines(duplicatedLines);
        analysisEntity.setTestCoverage(testCoverage);
        analysisEntity.setCyclomaticComplexity(cyclomaticComplexity);
        analysisEntity.setDocumentedLines(documentedLines);

        analysisEntity.setCyclicDependency(cyclicDependency);
        analysisEntity.setGodComponents(godComponents);

        analysisEntity.setCyclicallyDependentModularization(cycDependentMod);
        analysisEntity.setInsufficientModularization(insufficientModularization);
        analysisEntity.setUnnecessaryAbstraction(unnecessaryAbstraction);

        analysisEntity.setComplexConditional(complexConditional);
        analysisEntity.setEmptyCatchClause(emptyCatchClause);

        analysisEntity.setMissingAssertion(missingAssertion);
        analysisEntity.setEmptyTest(emptyTest);

        analysisEntity.setArchSmellDensity(archSmellDensity);
        analysisEntity.setDesignSmellDensity(designSmellDensity);
        analysisEntity.setImpSmellDensity(impSmellDensity);

        return analysisEntity;
    }

    @Test
    public void testGetFalseThresholdKeysWhenAnalysisCrossesIt() {
        AnalysisEntity analysisEntity = setValuesToAnalysisEntity();
        ThresholdEntity thresholdEntity = setValuesToThresholdEntity();
        PullRequestUtil pullRequestUtil = new PullRequestUtil();

        List<String> expected = new ArrayList<>();

        String codeSmell = "Code smell density : ";
        String values = "Expected (" + thresholdEntity.getCodeSmell() + ") | Actual (" + analysisEntity.getCodeSmell() + ")";
        expected.add(codeSmell+values);

        codeSmell = "Cyclomatic complexity: ";
        values = "Expected (" + thresholdEntity.getCyclomaticComplexity() + ") | Actual (" + analysisEntity.getCyclomaticComplexity() + ")";
        expected.add(codeSmell+values);

        codeSmell = "Line duplication: ";
        values = "Expected (" + thresholdEntity.getDuplicatedLines() + ") | Actual (" + analysisEntity.getDuplicatedLines() + ")";
        expected.add(codeSmell+values);

        codeSmell = "Architecture smell density : ";
        values = "Expected (" + thresholdEntity.getArchSmellDensity() + ") | Actual (" + analysisEntity.getArchSmellDensity() + ")";
        expected.add(codeSmell+values);

        codeSmell = "Design smell density: ";
        values = "Expected (" + thresholdEntity.getDesignSmellDensity() + ") | Actual (" + analysisEntity.getDesignSmellDensity() + ")";
        expected.add(codeSmell+values);

        codeSmell = "Implementation smell density : ";
        values = "Expected (" + thresholdEntity.getImpSmellDensity() + ") | Actual (" + analysisEntity.getImpSmellDensity() + ")";
        expected.add(codeSmell+values);

        codeSmell = "Complex conditions: ";
        values = "Expected (" + thresholdEntity.getComplexConditional() + ") | Actual (" + analysisEntity.getComplexConditional() + ")";
        expected.add(codeSmell+values);

        codeSmell = "Cyclically dependent modularization : ";
        values = "Expected (" + thresholdEntity.getCycDependentMod() + ") | Actual (" + analysisEntity.getCyclicallyDependentModularization() + ")";
        expected.add(codeSmell+values);

        codeSmell = "Cyclic dependencie : ";
        values = "Expected (" + thresholdEntity.getCyclicDependency() + ") | Actual (" + analysisEntity.getCyclicDependency() + ")";
        expected.add(codeSmell+values);

        codeSmell = "Empty catch clause : ";
        values = "Expected (" + thresholdEntity.getEmptyCatchClause() + ") | Actual (" + analysisEntity.getEmptyCatchClause() + ")";
        expected.add(codeSmell+values);

        codeSmell = "Empty test method : ";
        values = "Expected (" + thresholdEntity.getEmptyTest() + ") | Actual (" + analysisEntity.getEmptyTest() + ")";
        expected.add(codeSmell+values);

        codeSmell = "God component : ";
        values = "Expected (" + thresholdEntity.getGodComponents() + ") | Actual (" + analysisEntity.getGodComponents() + ")";
        expected.add(codeSmell+values);

        codeSmell = "Insufficient modularization : ";
        values = "Expected (" + thresholdEntity.getInsufficientModularization() + ") | Actual (" + analysisEntity.getInsufficientModularization() + ")";
        expected.add(codeSmell+values);

        codeSmell = "Missing assertion : ";
        values = "Expected (" + thresholdEntity.getMissingAssertion() + ") | Actual (" + analysisEntity.getMissingAssertion() + ")";
        expected.add(codeSmell+values);

        codeSmell = "Unnecessary abstraction : ";
        values = "Expected (" + thresholdEntity.getUnnecessaryAbstraction() + ") | Actual (" + analysisEntity.getUnnecessaryAbstraction() + ")";
        expected.add(codeSmell+values);

        List<String> actual = pullRequestUtil.getFalseThresholdKeys(analysisEntity, thresholdEntity);
        Assertions.assertIterableEquals(expected, actual);
    }

    @Test
    public void testGetFalseThresholdKeysWhenAnalysisDoesNotCrossIt() {
        AnalysisEntity analysisEntity = setValuesToAnalysisEntity();
        ThresholdEntity thresholdEntity = setValuesToThresholdEntity();
        PullRequestUtil pullRequestUtil = new PullRequestUtil();

        analysisEntity.setBugs(bugs-1);
        analysisEntity.setVulnerabilities(vulnerabilities-1);
        analysisEntity.setCodeSmell(codeSmell-1);
        analysisEntity.setDuplicatedLines(duplicatedLines-1);
        analysisEntity.setTestCoverage(testCoverage-1);
        analysisEntity.setCyclomaticComplexity(cyclomaticComplexity-1);
        analysisEntity.setDocumentedLines(documentedLines-1);

        analysisEntity.setCyclicDependency(cyclicDependency-1);
        analysisEntity.setGodComponents(godComponents-1);

        analysisEntity.setCyclicallyDependentModularization(cycDependentMod-1);
        analysisEntity.setInsufficientModularization(insufficientModularization-1);
        analysisEntity.setUnnecessaryAbstraction(unnecessaryAbstraction-1);

        analysisEntity.setComplexConditional(complexConditional-1);
        analysisEntity.setEmptyCatchClause(emptyCatchClause-1);

        analysisEntity.setMissingAssertion(missingAssertion-1);
        analysisEntity.setEmptyTest(emptyTest-1);

        analysisEntity.setArchSmellDensity(archSmellDensity-1);
        analysisEntity.setDesignSmellDensity(designSmellDensity-1);
        analysisEntity.setImpSmellDensity(impSmellDensity-1);

        List<String> expected = new ArrayList<>();

        List<String> actual = pullRequestUtil.getFalseThresholdKeys(analysisEntity, thresholdEntity);
        Assertions.assertIterableEquals(expected, actual);
    }
}
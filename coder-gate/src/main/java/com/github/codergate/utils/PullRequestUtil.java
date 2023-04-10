package com.github.codergate.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.github.codergate.entities.AnalysisEntity;
import com.github.codergate.entities.ThresholdEntity;

@Component
public class PullRequestUtil {

    private static final String EXPECTED = "Expected (";
    private static final String ACTUAL = ") | Actual (";

    /**
     * method compares latest analysis with threshold values for a repo
     * 
     * @param analysisEntity
     * @param thresholdEntity
     * @return Boolean
     */
    public List<String> getFalseThresholdKeys(AnalysisEntity analysisEntity, ThresholdEntity thresholdEntity) {
        List<String> falseKeys = new ArrayList<>();
        String moreInfo;
        if (analysisEntity.getCodeSmell() >= thresholdEntity.getCodeSmell()) {
            moreInfo = thresholdEntity.getCodeSmell() + ACTUAL + analysisEntity.getCodeSmell();
            falseKeys.add("Code smell density :" + EXPECTED + moreInfo + ")");
        }
        if (analysisEntity.getCyclomaticComplexity() >= thresholdEntity.getCyclomaticComplexity()) {
            moreInfo = thresholdEntity.getCyclomaticComplexity() + ACTUAL
                    + analysisEntity.getCyclomaticComplexity();
            falseKeys.add(EXPECTED +
                    "Cyclomatic complexity:" + EXPECTED + moreInfo + ")");
        }
        if (analysisEntity.getDuplicatedLines() >= thresholdEntity.getDuplicatedLines()) {
            moreInfo = thresholdEntity.getDuplicatedLines() + ACTUAL
                    + analysisEntity.getDuplicatedLines();
            falseKeys.add("Line duplication: " + EXPECTED + moreInfo + ")");
        }
        if (analysisEntity.getArchSmellDensity() >= thresholdEntity.getArchSmellDensity()) {
            moreInfo = thresholdEntity.getArchSmellDensity() + ACTUAL
                    + analysisEntity.getArchSmellDensity();
            falseKeys.add("Architecture smell density : " + EXPECTED + moreInfo + ")");
        }
        if (analysisEntity.getDesignSmellDensity() >= thresholdEntity.getDesignSmellDensity()) {
            moreInfo = thresholdEntity.getDesignSmellDensity() + ACTUAL
                    + analysisEntity.getDesignSmellDensity();
            falseKeys.add("Design smell density: " + EXPECTED + moreInfo + ")");
        }
        if (analysisEntity.getImpSmellDensity() >= thresholdEntity.getImpSmellDensity()) {
            moreInfo = thresholdEntity.getImpSmellDensity() + ACTUAL
                    + analysisEntity.getImpSmellDensity();
            falseKeys.add("Implementation smell density : " + EXPECTED + moreInfo + ")");
        }
        if (analysisEntity.getComplexConditional() >= thresholdEntity.getComplexConditional()) {
            moreInfo = thresholdEntity.getComplexConditional() + ACTUAL
                    + analysisEntity.getComplexConditional();
            falseKeys.add("Complex conditions:" + EXPECTED + EXPECTED + moreInfo + ")");
        }
        if (analysisEntity.getCyclicallyDependentModularization() >= thresholdEntity.getCycDependentMod()) {
            moreInfo = thresholdEntity.getCycDependentMod() + ACTUAL
                    + analysisEntity.getCyclicallyDependentModularization();
            falseKeys.add("Cyclically dependent modularization : " + EXPECTED + moreInfo);
        }
        if (analysisEntity.getCyclicDependency() >= thresholdEntity.getCyclicDependency()) {
            moreInfo = thresholdEntity.getCyclicDependency() + ACTUAL
                    + analysisEntity.getCyclicDependency();
            falseKeys.add("Cyclic dependencie : " + EXPECTED + moreInfo);
        }
        if (analysisEntity.getEmptyCatchClause() >= thresholdEntity.getEmptyCatchClause()) {
            moreInfo = thresholdEntity.getEmptyCatchClause() + ACTUAL
                    + analysisEntity.getEmptyCatchClause();
            falseKeys.add("Empty catch clause : " + EXPECTED + moreInfo + ")");
        }
        if (analysisEntity.getEmptyTest() >= thresholdEntity.getEmptyTest()) {
            moreInfo = thresholdEntity.getEmptyTest() + ACTUAL
                    + analysisEntity.getEmptyTest();
            falseKeys.add("Empty test method : " + EXPECTED + moreInfo + ")");
        }
        if (analysisEntity.getGodComponents() >= thresholdEntity.getGodComponents()) {
            moreInfo = thresholdEntity.getGodComponents() + ACTUAL
                    + analysisEntity.getGodComponents();
            falseKeys.add("God component : " + EXPECTED + moreInfo + ")");
        }
        if (analysisEntity.getInsufficientModularization() >= thresholdEntity.getInsufficientModularization()) {
            moreInfo = thresholdEntity.getInsufficientModularization() + ACTUAL
                    + analysisEntity.getInsufficientModularization();
            falseKeys.add("Insufficient modularization : " + EXPECTED + moreInfo + ")");
        }
        if (analysisEntity.getMissingAssertion() >= thresholdEntity.getMissingAssertion()) {
            moreInfo = thresholdEntity.getMissingAssertion() + ACTUAL
                    + analysisEntity.getMissingAssertion();
            falseKeys.add("Missing assertion : " + EXPECTED + EXPECTED + moreInfo + ")");
        }
        if (analysisEntity.getUnnecessaryAbstraction() >= thresholdEntity.getUnnecessaryAbstraction()) {
            moreInfo = thresholdEntity.getUnnecessaryAbstraction() + ACTUAL
                    + analysisEntity.getUnnecessaryAbstraction();
            falseKeys.add("Unnecessary abstraction : " + EXPECTED + EXPECTED + moreInfo + ")");
        }
        return falseKeys;
    }
}

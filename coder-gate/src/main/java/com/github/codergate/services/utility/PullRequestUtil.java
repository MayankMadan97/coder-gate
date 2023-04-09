package com.github.codergate.services.utility;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.github.codergate.entities.AnalysisEntity;
import com.github.codergate.entities.ThresholdEntity;

@Component
public class PullRequestUtil {

    /**
     * method compares latest analysis with threshold values for a repo
     * 
     * @param analysisEntity
     * @param thresholdEntity
     * @return Boolean
     */
    public List<String> getFalseThresholdKeys(AnalysisEntity analysisEntity, ThresholdEntity thresholdEntity) {
        List<String> falseKeys = new ArrayList<>();
        if (analysisEntity.getCodeSmell() >= thresholdEntity.getCodeSmell()) {
            falseKeys.add("Code smell density : Expected (" + thresholdEntity.getCodeSmell() + ") | Actual ("
                    + analysisEntity.getCodeSmell() + ")");
        }
        if (analysisEntity.getCyclomaticComplexity() >= thresholdEntity.getCyclomaticComplexity()) {
            falseKeys.add(
                    "Cyclomatic complexity: Expected (" + thresholdEntity.getCyclomaticComplexity() + ") | Actual ("
                            + analysisEntity.getCyclomaticComplexity() + ")");
        }
        if (analysisEntity.getDuplicatedLines() >= thresholdEntity.getDuplicatedLines()) {
            falseKeys.add("Line duplication: Expected (" + thresholdEntity.getDuplicatedLines() + ") | Actual ("
                    + analysisEntity.getDuplicatedLines() + ")");
        }
        if (analysisEntity.getArchSmellDensity() >= thresholdEntity.getArchSmellDensity()) {
            falseKeys.add(
                    "Architecture smell density : Expected (" + thresholdEntity.getArchSmellDensity() + ") | Actual ("
                            + analysisEntity.getArchSmellDensity() + ")");
        }
        if (analysisEntity.getDesignSmellDensity() >= thresholdEntity.getDesignSmellDensity()) {
            falseKeys.add("Design smell density: Expected (" + thresholdEntity.getDesignSmellDensity() + ") | Actual ("
                    + analysisEntity.getDesignSmellDensity() + ")");
        }
        if (analysisEntity.getImpSmellDensity() >= thresholdEntity.getImpSmellDensity()) {
            falseKeys.add(
                    "Implementation smell density : Expected (" + thresholdEntity.getImpSmellDensity() + ") | Actual ("
                            + analysisEntity.getImpSmellDensity() + ")");
        }
        if (analysisEntity.getComplexConditional() >= thresholdEntity.getComplexConditional()) {
            falseKeys.add("Complex conditions: Expected (" + thresholdEntity.getComplexConditional() + ") | Actual ("
                    + analysisEntity.getComplexConditional() + ")");
        }
        if (analysisEntity.getCyclicallyDependentModularization() >= thresholdEntity.getCycDependentMod()) {
            falseKeys.add("Cyclically dependent modularization : Expected (" + thresholdEntity.getCycDependentMod()
                    + ") | Actual ("
                    + analysisEntity.getCyclicallyDependentModularization() + ")");
        }
        if (analysisEntity.getCyclicDependency() >= thresholdEntity.getCyclicDependency()) {
            falseKeys.add("Cyclic dependencie : Expected (" + thresholdEntity.getCyclicDependency() + ") | Actual ("
                    + analysisEntity.getCyclicDependency() + ")");
        }
        if (analysisEntity.getEmptyCatchClause() >= thresholdEntity.getEmptyCatchClause()) {
            falseKeys.add("Empty catch clause : Expected (" + thresholdEntity.getEmptyCatchClause() + ") | Actual ("
                    + analysisEntity.getEmptyCatchClause() + ")");
        }
        if (analysisEntity.getEmptyTest() >= thresholdEntity.getEmptyTest()) {
            falseKeys.add("Empty test method : Expected (" + thresholdEntity.getEmptyTest() + ") | Actual ("
                    + analysisEntity.getEmptyTest() + ")");
        }
        if (analysisEntity.getGodComponents() >= thresholdEntity.getGodComponents()) {
            falseKeys.add("God component : Expected (" + thresholdEntity.getGodComponents() + ") | Actual ("
                    + analysisEntity.getGodComponents() + ")");
        }
        if (analysisEntity.getInsufficientModularization() >= thresholdEntity.getInsufficientModularization()) {
            falseKeys.add("Insufficient modularization : Expected (" + thresholdEntity.getInsufficientModularization()
                    + ") | Actual ("
                    + analysisEntity.getInsufficientModularization() + ")");
        }
        if (analysisEntity.getMissingAssertion() >= thresholdEntity.getMissingAssertion()) {
            falseKeys.add("Missing assertion : Expected (" + thresholdEntity.getMissingAssertion() + ") | Actual ("
                    + analysisEntity.getMissingAssertion() + ")");
        }
        if (analysisEntity.getUnnecessaryAbstraction() >= thresholdEntity.getUnnecessaryAbstraction()) {
            falseKeys.add("Unnecessary abstraction : Expected (" + thresholdEntity.getUnnecessaryAbstraction()
                    + ") | Actual ("
                    + analysisEntity.getUnnecessaryAbstraction() + ")");
        }
        return falseKeys;
    }
}

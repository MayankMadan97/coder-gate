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
            falseKeys.add("Code smell density");
        }
        if (analysisEntity.getCyclomaticComplexity() >= thresholdEntity.getCyclomaticComplexity()) {
            falseKeys.add("Cyclomatic complexity");
        }
        if (analysisEntity.getDuplicatedLines() >= thresholdEntity.getDuplicatedLines()) {
            falseKeys.add("Line duplication");
        }
        if (analysisEntity.getArchSmellDensity() >= thresholdEntity.getArchSmellDensity()) {
            falseKeys.add("Architecture smell density");
        }
        if (analysisEntity.getDesignSmellDensity() >= thresholdEntity.getDesignSmellDensity()) {
            falseKeys.add("Design smell density");
        }
        if (analysisEntity.getImpSmellDensity() >= thresholdEntity.getImpSmellDensity()) {
            falseKeys.add("Implementation smell density");
        }
        if (analysisEntity.getComplexConditional() >= thresholdEntity.getComplexConditional()) {
            falseKeys.add("Complex conditions");
        }
        if (analysisEntity.getCyclicallyDependentModularization() >= thresholdEntity.getCycDependentMod()) {
            falseKeys.add("Cyclically dependent modularization");
        }
        if (analysisEntity.getCyclicDependency() >= thresholdEntity.getCyclicDependency()) {
            falseKeys.add("Cyclic dependencie");
        }
        if (analysisEntity.getEmptyCatchClause() >= thresholdEntity.getEmptyCatchClause()) {
            falseKeys.add("Empty catch clause");
        }
        if (analysisEntity.getEmptyTest() >= thresholdEntity.getEmptyTest()) {
            falseKeys.add("Empty test method");
        }
        if (analysisEntity.getGodComponents() >= thresholdEntity.getGodComponents()) {
            falseKeys.add("God component");
        }
        if (analysisEntity.getInsufficientModularization() >= thresholdEntity.getInsufficientModularization()) {
            falseKeys.add("Insufficient modularization");
        }
        if (analysisEntity.getMissingAssertion() >= thresholdEntity.getMissingAssertion()) {
            falseKeys.add("Missing assertion");
        }
        if (analysisEntity.getUnnecessaryAbstraction() >= thresholdEntity.getUnnecessaryAbstraction()) {
            falseKeys.add("Unnecessary abstraction");
        }
        return falseKeys;
    }
}

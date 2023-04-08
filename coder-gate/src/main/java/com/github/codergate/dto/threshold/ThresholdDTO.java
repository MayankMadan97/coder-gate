package com.github.codergate.dto.threshold;

import java.io.Serializable;

public class ThresholdDTO implements Serializable {

    private int bugs;
    private int vulnerabilities;
    private double codeSmell;
    private double testCoverage;
    private double duplicatedLines;
    private double cyclomaticComplexity;
    private double documentedLines;

    // Architecture Smells
    private int cyclicDependency;
    private int godComponents;

    // Design Smells
    private int cycDependentMod;
    private int insufficientModularization;
    private int unnecessaryAbstraction;

    // Implementation Smells
    private int complexConditional;
    private int emptyCatchClause;

    // Test Smells
    private int missingAssertion;
    private int emptyTest;

    // Desnsities
    private double archSmellDensity;
    private double designSmellDensity;
    private double impSmellDensity;
    private boolean allowAction;
    

    public int getBugs() {
        return bugs;
    }

    public void setBugs(int bugs) {
        this.bugs = bugs;
    }

    public int getVulnerabilities() {
        return vulnerabilities;
    }

    public void setVulnerabilities(int vulnerabilities) {
        this.vulnerabilities = vulnerabilities;
    }

    public double getCodeSmell() {
        return codeSmell;
    }

    public void setCodeSmell(double codeSmell) {
        this.codeSmell = codeSmell;
    }

    public double getTestCoverage() {
        return testCoverage;
    }

    public void setTestCoverage(double testCoverage) {
        this.testCoverage = testCoverage;
    }

    public double getDuplicatedLines() {
        return duplicatedLines;
    }

    public void setDuplicatedLines(double duplicatedLines) {
        this.duplicatedLines = duplicatedLines;
    }

    public double getCyclomaticComplexity() {
        return cyclomaticComplexity;
    }

    public void setCyclomaticComplexity(double cyclomaticComplexity) {
        this.cyclomaticComplexity = cyclomaticComplexity;
    }

    public double getDocumentedLines() {
        return documentedLines;
    }

    public void setDocumentedLines(double documentedLines) {
        this.documentedLines = documentedLines;
    }

    public int getCyclicDependency() {
        return cyclicDependency;
    }

    public void setCyclicDependency(int cyclicDependency) {
        this.cyclicDependency = cyclicDependency;
    }

    public int getGodComponents() {
        return godComponents;
    }

    public void setGodComponents(int godComponents) {
        this.godComponents = godComponents;
    }

    public int getCycDependentMod() {
        return cycDependentMod;
    }

    public void setCycDependentMod(int cycDependentMod) {
        this.cycDependentMod = cycDependentMod;
    }

    public int getInsufficientModularization() {
        return insufficientModularization;
    }

    public void setInsufficientModularization(int insufficientModularization) {
        this.insufficientModularization = insufficientModularization;
    }

    public int getUnnecessaryAbstraction() {
        return unnecessaryAbstraction;
    }

    public void setUnnecessaryAbstraction(int unnecessaryAbstraction) {
        this.unnecessaryAbstraction = unnecessaryAbstraction;
    }

    public int getComplexConditional() {
        return complexConditional;
    }

    public void setComplexConditional(int complexConditional) {
        this.complexConditional = complexConditional;
    }

    public int getEmptyCatchClause() {
        return emptyCatchClause;
    }

    public void setEmptyCatchClause(int emptyCatchClause) {
        this.emptyCatchClause = emptyCatchClause;
    }

    public int getMissingAssertion() {
        return missingAssertion;
    }

    public void setMissingAssertion(int missingAssertion) {
        this.missingAssertion = missingAssertion;
    }

    public int getEmptyTest() {
        return emptyTest;
    }

    public void setEmptyTest(int emptyTest) {
        this.emptyTest = emptyTest;
    }

    public double getArchSmellDensity() {
        return archSmellDensity;
    }

    public void setArchSmellDensity(double archSmellDensity) {
        this.archSmellDensity = archSmellDensity;
    }

    public double getDesignSmellDensity() {
        return designSmellDensity;
    }

    public void setDesignSmellDensity(double designSmellDensity) {
        this.designSmellDensity = designSmellDensity;
    }

    public double getImpSmellDensity() {
        return impSmellDensity;
    }

    public void setImpSmellDensity(double impSmellDensity) {
        this.impSmellDensity = impSmellDensity;
    }

    public boolean isAllowAction() {
        return allowAction;
    }

    public void setAllowAction(boolean allowAction) {
        this.allowAction = allowAction;
    }
}

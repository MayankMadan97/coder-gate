package com.github.codergate.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "Analysis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisEntity {
    @Id
    @Column(name = "analysisid")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "analysis_type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "repositoryId")
    private RepositoryEntity repositoryIdInAnalysis;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "eventId")
    private EventEntity eventIdInAnalysis;

    private int bugs;
    private int vulnerabilities;
    private int codeSmell;
    private int testCoverage;
    private int duplicatedLines;
    private int cyclomaticComplexity;
    private int documentedLines;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public EventEntity getEventIdInAnalysis() {
        return eventIdInAnalysis;
    }

    public void setEventIdInAnalysis(EventEntity eventIdInAnalysis) {
        this.eventIdInAnalysis = eventIdInAnalysis;
    }

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

    public int getCodeSmell() {
        return codeSmell;
    }

    public void setCodeSmell(int codeSmell) {
        this.codeSmell = codeSmell;
    }

    public int getTestCoverage() {
        return testCoverage;
    }

    public void setTestCoverage(int testCoverage) {
        this.testCoverage = testCoverage;
    }

    public int getDuplicatedLines() {
        return duplicatedLines;
    }

    public void setDuplicatedLines(int duplicatedLines) {
        this.duplicatedLines = duplicatedLines;
    }

    public int getCyclomaticComplexity() {
        return cyclomaticComplexity;
    }

    public void setCyclomaticComplexity(int cyclomaticComplexity) {
        this.cyclomaticComplexity = cyclomaticComplexity;
    }

    public int getDocumentedLines() {
        return documentedLines;
    }

    public void setDocumentedLines(int documentedLines) {
        this.documentedLines = documentedLines;
    }
    //Architecture Smells
    private int cyclicDependency;
    private int godComponents;

    //Design Smells
    private int cyclicallyDependentModularization;
    private int insufficientModularization;
    private int unnecessaryAbstraction;

    //Implementation Smells
    private int complexMethod;
    private int complexConditional;
    private int emptyCatchClause;

    //Test Smells
    private int missingAssertion;
    private int emptyTest;

    //Timestamp
    private long timestamp;

    public RepositoryEntity getRepositoryIdInAnalysis() {
        return repositoryIdInAnalysis;
    }

    public void setRepositoryIdInAnalysis(RepositoryEntity repositoryIdInAnalysis) {
        this.repositoryIdInAnalysis = repositoryIdInAnalysis;
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

    public int getCyclicallyDependentModularization() {
        return cyclicallyDependentModularization;
    }

    public void setCyclicallyDependentModularization(int cyclicallyDependentModularization) {
        this.cyclicallyDependentModularization = cyclicallyDependentModularization;
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

    public int getComplexMethod() {
        return complexMethod;
    }

    public void setComplexMethod(int complexMethod) {
        this.complexMethod = complexMethod;
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

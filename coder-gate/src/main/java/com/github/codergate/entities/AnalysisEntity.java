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

<<<<<<< HEAD
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

    public int getRepositoryID() {
        return repositoryID;
    }

    public void setRepositoryID(int repositoryID) {
        this.repositoryID = repositoryID;
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
=======
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

>>>>>>> f79a1113bffc2b1a2a0c59dae712fe48151abdd0
}

package com.github.codergate.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Threshold")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThresholdEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int thresholdId;
    @OneToOne
    @JoinColumn(name = "repositoryId")
    private RepositoryEntity repositoryIdInThreshold;
    private int bugs;
    private int vulnerabilities;
    private int codeSmell;
    private int testCoverage;
    private int duplicatedLines;
    private int cyclomaticComplexity;
    private int documentedLines;

<<<<<<< HEAD
    public int getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(int repositoryId) {
        this.repositoryId = repositoryId;
    }

    public RepositoryEntity getRepositoryIdInThreshold() {
        return repositoryIdInThreshold;
    }

    public void setRepositoryIdInThreshold(RepositoryEntity repositoryIdInThreshold) {
        this.repositoryIdInThreshold = repositoryIdInThreshold;
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

>>>>>>> f79a1113bffc2b1a2a0c59dae712fe48151abdd0
}

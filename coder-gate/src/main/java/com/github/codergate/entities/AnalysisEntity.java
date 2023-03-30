package com.github.codergate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Analysis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisEntity {

    @Id
    @Column(name = "analysisid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "analysis_type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "branchId")
    @JoinColumn(name = "repositoryId")
    private BranchEntity branchId;

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
    private int cyclicallyDependentModularization;
    private int insufficientModularization;
    private int unnecessaryAbstraction;

    // Implementation Smells
    private int complexConditional;
    private int emptyCatchClause;

    // Test Smells
    private int missingAssertion;
    private int emptyTest;

    // Densities
    private double archSmellDensity;
    private double designSmellDensity;
    private double impSmellDensity;

    // Timestamp
    private long timestamp;

    public AnalysisEntity(int repoId, String branchName,
            long timestamp) {
        BranchEntity branch = new BranchEntity();
        branch.setBranchId(new BranchId(repoId, branchName));
        this.branchId = branch;
        this.timestamp = timestamp;
    }

}

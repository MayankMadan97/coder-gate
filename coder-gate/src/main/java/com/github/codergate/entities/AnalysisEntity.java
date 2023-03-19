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
    private double codeSmell;
    private int testCoverage;
    private int duplicatedLines;
    private int cyclomaticComplexity;
    private int documentedLines;

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

    public AnalysisEntity(double codeSmell, int duplicatedLines) {
        this.codeSmell = codeSmell;
        this.duplicatedLines = duplicatedLines;
    }

}

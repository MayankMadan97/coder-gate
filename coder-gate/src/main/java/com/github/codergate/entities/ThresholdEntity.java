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
    private int repositoryId;
    @OneToOne
    @MapsId
    @JoinColumn(name = "repositoryId")
    private RepositoryEntity repositoryIdInThreshold;
    private int bugs;
    private int vulnerabilities;
    private int codeSmell;
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

}

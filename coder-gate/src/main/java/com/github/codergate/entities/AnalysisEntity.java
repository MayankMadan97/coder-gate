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
    private int id;

    @Column(name = "analysis_type")
    private String type;

    private int repositoryID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "eventId")
    private EventEntity eventIdInAnalysis;
}

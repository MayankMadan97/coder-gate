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
    private int analysisID;
    private String analysisType;
    private int repositoryID;

    @OneToOne
    @JoinColumn(name = "eventID")
    private EventEntity e3;
}

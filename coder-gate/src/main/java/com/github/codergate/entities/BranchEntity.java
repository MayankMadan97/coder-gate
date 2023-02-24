package com.github.codergate.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "branch")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BranchEntity {

    @Id
    private String branchUrl;

    @ManyToOne
    @JoinColumn(name = "repositoryId")
    private RepositoryEntity b;
}

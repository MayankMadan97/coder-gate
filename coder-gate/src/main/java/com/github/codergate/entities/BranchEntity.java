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
    @EmbeddedId
    @Column(name = "branchId")
    private BranchId branchId;

    @ManyToOne
    @JoinColumn(name = "repositoryId", insertable = false, updatable = false)
    private RepositoryEntity repositoryIdInBranch;
}

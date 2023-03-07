package com.github.codergate.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "repository")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryEntity {
    @Id
    private int repositoryId;
    private String repositoryName;
    private boolean fork;

    @OneToMany(mappedBy = "repositoryIdInUser", cascade = CascadeType.ALL)
    private Set<UserEntity> userAndRepository;

    @OneToMany(mappedBy = "repositoryIdInBranch", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BranchEntity> branchEntities = new ArrayList<>();
    @OneToMany(mappedBy = "repositoryIdInTag", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TagEntity> tagEntities = new ArrayList<>();

    @OneToMany(mappedBy = "repositoryIdInEvent", cascade = CascadeType.ALL)
    private Set<EventEntity> eventAndRepository;

    @OneToOne(mappedBy = "repositoryIdInThreshold")
    private ThresholdEntity thresholdAndRepository;

}
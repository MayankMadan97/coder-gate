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

    @OneToMany(mappedBy = "r1", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserEntity> u1;

    @OneToMany(mappedBy = "b", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BranchEntity> branchEntities = new ArrayList<>();
    @OneToMany(mappedBy = "t", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TagEntity> tagEntities = new ArrayList<>();

    @OneToMany(mappedBy = "r2", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EventEntity> e2;

}
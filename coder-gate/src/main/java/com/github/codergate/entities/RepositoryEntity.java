package com.github.codergate.entities;

import com.github.codergate.dto.installation.Installation;
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


    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity userEntity;

    @OneToMany(mappedBy = "repositoryIdInBranch", cascade = CascadeType.ALL)
    private List<BranchEntity> branchEntities = new ArrayList<>();
    @OneToMany(mappedBy = "repositoryIdInTag", cascade = CascadeType.ALL)
    private List<TagEntity> tagEntities = new ArrayList<>();

    @OneToMany(mappedBy = "repositoryIdInEvent", cascade = CascadeType.ALL)
    private Set<EventEntity> eventAndRepository;

    @OneToOne(mappedBy = "repositoryIdInThreshold")
    private ThresholdEntity thresholdAndRepository;

    private String installationId;

}
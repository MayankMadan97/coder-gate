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

    public int getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(int repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public boolean isFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<BranchEntity> getBranchEntities() {
        return branchEntities;
    }

    public void setBranchEntities(List<BranchEntity> branchEntities) {
        this.branchEntities = branchEntities;
    }

    public List<TagEntity> getTagEntities() {
        return tagEntities;
    }

    public void setTagEntities(List<TagEntity> tagEntities) {
        this.tagEntities = tagEntities;
    }

    public Set<EventEntity> getEventAndRepository() {
        return eventAndRepository;
    }

    public void setEventAndRepository(Set<EventEntity> eventAndRepository) {
        this.eventAndRepository = eventAndRepository;
    }

    public ThresholdEntity getThresholdAndRepository() {
        return thresholdAndRepository;
    }

    public void setThresholdAndRepository(ThresholdEntity thresholdAndRepository) {
        this.thresholdAndRepository = thresholdAndRepository;
    }

    public String getInstallationId() {
        return installationId;
    }

    public void setInstallationId(String installationId) {
        this.installationId = installationId;
    }
}
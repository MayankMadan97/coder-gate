package com.github.codergate.dto.controller;

import com.github.codergate.dto.installation.RepositoriesAddedDTO;
import com.github.codergate.dto.pullRequest.CommitsInformation;

import java.util.LinkedHashMap;
import java.util.List;

public class RepositoryResponse {
    RepositoriesAddedDTO repositoriesAddedDTO;
    List<LinkedHashMap> commitsInformation;

    public RepositoriesAddedDTO getRepositoriesAddedDTO() {
        return repositoriesAddedDTO;
    }

    public void setRepositoriesAddedDTO(RepositoriesAddedDTO repositoriesAddedDTO) {
        this.repositoriesAddedDTO = repositoriesAddedDTO;
    }

    public List<LinkedHashMap> getCommitsInformation() {
        return commitsInformation;
    }

    public void setCommitsInformation(List<LinkedHashMap> commitsInformation) {
        this.commitsInformation = commitsInformation;
    }
}

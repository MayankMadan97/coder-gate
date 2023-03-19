package com.github.codergate.dto.controller;

import com.github.codergate.dto.installation.RepositoriesAddedDTO;
import com.github.codergate.dto.pullRequest.CommitsInformation;

import java.util.List;

public class RepositoryResponse {
    List<RepositoriesAddedDTO> repositoriesAddedDTOList;
    CommitsInformation commitsInformation;

    public List<RepositoriesAddedDTO> getRepositoriesAddedDTOList() {
        return repositoriesAddedDTOList;
    }

    public void setRepositoriesAddedDTOList(List<RepositoriesAddedDTO> repositoriesAddedDTOList) {
        this.repositoriesAddedDTOList = repositoriesAddedDTOList;
    }

    public CommitsInformation getCommitsInformation() {
        return commitsInformation;
    }

    public void setCommitsInformation(CommitsInformation commitsInformation) {
        this.commitsInformation = commitsInformation;
    }
}

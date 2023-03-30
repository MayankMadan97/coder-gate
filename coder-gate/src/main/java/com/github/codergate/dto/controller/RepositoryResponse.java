package com.github.codergate.dto.controller;

import java.util.List;

import com.github.codergate.dto.RepositoryMinimal;

public class RepositoryResponse {

    List<RepositoryMinimal> repositoriesAddedDTO;

    public List<RepositoryMinimal> getRepositoriesAddedDTO() {
        return repositoriesAddedDTO;
    }

    public void setRepositoriesAddedDTO(List<RepositoryMinimal> repositoriesAddedDTO) {
        this.repositoriesAddedDTO = repositoriesAddedDTO;
    }

}

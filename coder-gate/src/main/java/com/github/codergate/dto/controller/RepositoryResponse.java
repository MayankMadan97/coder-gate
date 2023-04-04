package com.github.codergate.dto.controller;

import java.util.List;

import com.github.codergate.dto.RepositoryMinimal;

public class RepositoryResponse {

    List<RepositoryMinimal> repositories;

    public List<RepositoryMinimal> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<RepositoryMinimal> repositories) {
        this.repositories = repositories;
    }

}

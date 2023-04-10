package com.github.codergate.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.codergate.entities.BranchEntity;
import com.github.codergate.entities.BranchId;
import com.github.codergate.entities.RepositoryEntity;
import com.github.codergate.repositories.BranchRepository;

@Service
public class BranchService {

    @Autowired
    BranchRepository branchRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(BranchService.class);

    /***
     * adds branch information during push event
     * 
     * @return RepositoryDTO object
     */
    public void addBranch(String branchesUrl, Integer repositoryId) {
        BranchEntity branchEntity = convertDTOToEntity(branchesUrl, repositoryId);
        BranchEntity savedEntity = branchRepository.save(branchEntity);
        LOGGER.info("addBranch : The branch information is added {}", savedEntity);
    }

    /***
     * converts RepositoryDTO to Branch entity
     * 
     * @return Branch entity
     */
    private BranchEntity convertDTOToEntity(String branchesUrl, Integer repositoryId) {
        BranchEntity branchEntity = null;
        branchEntity = new BranchEntity();
        BranchId branchId = new BranchId(repositoryId, branchesUrl);
        RepositoryEntity repositoryEntity = new RepositoryEntity();
        repositoryEntity.setRepositoryId(repositoryId);
        branchEntity.setBranchId(branchId);
        branchEntity.setRepositoryIdInBranch(repositoryEntity);
        LOGGER.info("convertDTOToEntity : Repository DTO has been converted to Branch Entity {}", branchEntity);
        return branchEntity;
    }

    public List<String> getBranchesByRepoId(String repoId) {
        return branchRepository.findByRepoId(Integer.parseInt(repoId));
    }
}

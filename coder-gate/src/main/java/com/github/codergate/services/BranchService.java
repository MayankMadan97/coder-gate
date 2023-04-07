package com.github.codergate.services;

import com.github.codergate.dto.push.RepositoryDTO;
import com.github.codergate.entities.BranchEntity;
import com.github.codergate.entities.BranchId;
import com.github.codergate.repositories.BranchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    @Autowired
    BranchRepository branchRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(BranchService.class);

    /***
     * adds branch information during push event
     * @return RepositoryDTO object
     */
    public void addBranch(String branchesUrl, Integer repositoryId) {
        BranchEntity branchEntity = convertDTOToEntity(branchesUrl,repositoryId);
            BranchEntity savedEntity = branchRepository.save(branchEntity);
            LOGGER.info("addBranch : The branch information is added {}", savedEntity);
    }

    /***
     * converts RepositoryDTO to Branch entity
     * @return Branch entity
     */
    private BranchEntity convertDTOToEntity(String branchesUrl, Integer reposirotyId) {
        BranchEntity branchEntity = null;
            branchEntity = new BranchEntity();
            BranchId branchId = new BranchId(reposirotyId, branchesUrl);
            branchEntity.setBranchId(branchId);
            LOGGER.info("convertDTOToEntity : Repository DTO has been converted to Branch Entity {}", branchEntity);
        return branchEntity;
    }

    /***
     * converts Branch entity to RepositoryDTO
     * 
     * @param branch Branch entity
     * @return RepositoryDTO object
     */
    private RepositoryDTO convertEntityToDto(BranchEntity branch) {
        RepositoryDTO repositoryDTO = null;
        if (branch != null) {
            repositoryDTO = new RepositoryDTO();
            if (branch.getBranchId() != null) {
                BranchId branchIdObject = branch.getBranchId();
                repositoryDTO.setId(branchIdObject.getRepositoryId());
                repositoryDTO.setBranchesUrl(branchIdObject.getBranchUrl());
            }
            LOGGER.info("convertEntityToDto : Branch Entity has been converted to RepositoryDTO {}", repositoryDTO);
        } else {
            LOGGER.warn("convertEntityToDto : Branch Entity value is null");
        }
        return repositoryDTO;
    }

    public List<String> getBranchesByRepoId(String repoId){
        return branchRepository.findByRepoId(Integer.parseInt(repoId) );
    }
}

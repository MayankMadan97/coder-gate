package com.github.codergate.services;

import com.github.codergate.dto.push.RepositoryDTO;
import com.github.codergate.entities.BranchEntity;
import com.github.codergate.entities.BranchId;
import com.github.codergate.repositories.BranchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchService {

    @Autowired
    BranchRepository branchRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(WebHookListenerService.class);

    /***
     * adds branch information during push event
     * @param repository RepositoryDTO object
     * @return RepositoryDTO object
     */
    public RepositoryDTO addBranch(RepositoryDTO repository) {
        RepositoryDTO repositoryDTO;
        BranchEntity branchEntity = dtoToEntity(repository);
        BranchEntity saveEntity = branchRepository.save(branchEntity);
        LOGGER.info("BranchService : The branch information is added");
        repositoryDTO = entityToDto(saveEntity);
        return repositoryDTO;
    }

    /***
     * converts RepositoryDTO to Branch entity
     * @param repositoryDTO RepositoryDTO object
     * @return Branch entity
     */
    private BranchEntity dtoToEntity(RepositoryDTO repositoryDTO) {
        BranchEntity branchEntity = null;
        if(repositoryDTO != null)
        {
            branchEntity = new BranchEntity();
            if(repositoryDTO.getTagsUrl() != null && repositoryDTO.getId() != null)
            {
                BranchId branchId = new BranchId(repositoryDTO.getId(), repositoryDTO.getBranchesUrl());
                branchEntity.setBranchId(branchId);
            }
            LOGGER.info("BranchService : Repository DTO has been converted to Branch Entity");
        } else {
            LOGGER.warn("BranchService : Repository value is null");
        }
        return branchEntity;
    }

    /***
     * converts Branch entity to RepositoryDTO
     * @param branch Branch entity
     * @return RepositoryDTO object
     */
    private RepositoryDTO entityToDto(BranchEntity branch) {
        RepositoryDTO repository = null;
        if(branch != null)
        {
            repository = new RepositoryDTO();
            if(branch.getBranchId() != null)
            {
                BranchId branchIdObject = branch.getBranchId();
                repository.setId(branchIdObject.getRepositoryId());
                repository.setBranchesUrl(branchIdObject.getBranchUrl());
            }
            LOGGER.info("BranchService : Branch Entity has been converted to RepositoryDTO");
        } else {
            LOGGER.warn("BranchService : Tag value is null");
        }
        return repository;
    }
}

package com.github.codergate.services;

import com.github.codergate.dto.installation.RepositoriesAdded;
import com.github.codergate.dto.push.RepositoryDTO;
import com.github.codergate.entities.RepositoryEntity;
import com.github.codergate.entities.UserEntity;
import com.github.codergate.repositories.RepositoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class RepositoryService {

    @Autowired
    RepositoryRepository repositoryRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(WebHookListenerService.class);

    /***
     *  adds the repository to the table during installation event
     * @param repositories repository information
     * @param userId user id
     * @return RepositoriesAdded dto class
     */
    public List<RepositoriesAdded> addRepository(List<RepositoriesAdded> repositories, int userId)
    {
        List<RepositoriesAdded> repositoriesAdded;
        List<RepositoryEntity> repositoryEntityList= dtoToEntityForInstallationEvent(repositories,userId);
        List<RepositoryEntity>saveRepository= repositoryEntityList.stream().map(items -> repositoryRepository.save(items)).collect(Collectors.toList());
        repositoriesAdded = entityToDto(saveRepository);
        return repositoriesAdded;
    }

    /***
     * adds the repository to the table during push event
     * @param repositoryDTO repository DTO
     * @param userId user id
     * @return
     */
    public RepositoryDTO addRepository(RepositoryDTO repositoryDTO, int userId) {
        RepositoryDTO repositoryDTO2;
        RepositoryEntity repositoryEntity = dtoToEntityForPushEvent(repositoryDTO, userId);

        RepositoryEntity saveEntity = repositoryRepository.save(repositoryEntity);
        LOGGER.info("RepositoryService : The repositoryRepository information is added");
        repositoryDTO2 = entityToDtoForPushEvent(saveEntity);
        return repositoryDTO2;
    }

    /***
     * gets the repository information using id
     * @param repositoryId repositoryId
     * @return RepositoriesAdded dto class
     */
    public List<RepositoriesAdded> getRepository(List<Integer> repositoryId)
    {
        List<RepositoriesAdded> repositoriesAdded=null;
        for(int id : repositoryId) {
            Optional<RepositoryEntity> repositoryEntity = repositoryRepository.findById(id);
            if (repositoryEntity.isPresent()) {
                List<RepositoryEntity> entity = new ArrayList<>();
                entity.add(repositoryEntity.get());
                repositoriesAdded = entityToDto(entity);
                LOGGER.info("RepositoryService : Getting the user information");
            }
        }
        return repositoriesAdded;
    }


    /***
     * updates the repository table
     * @param repositoryId repositoryId
     * @return RepositoriesAdded dto class
     */
    public List<RepositoriesAdded> updateRepository(int repositoryId)
    {
        List<RepositoriesAdded> repositoriesAdded=null;
        Optional<RepositoryEntity> repositoryEntities= repositoryRepository.findById(repositoryId);
        if(repositoryEntities.isPresent())
        {
            List<RepositoryEntity> repositoryEntityList =repositoryEntities.stream().map(i->{
                i.setRepositoryId(repositoryId);
                return i;
            }).collect(Collectors.toList());
            List<RepositoryEntity> saveEntity =repositoryEntityList.stream().map(items -> repositoryRepository.save(items)).collect(Collectors.toList());
            repositoriesAdded=entityToDto(saveEntity);
        }

        return repositoriesAdded;
    }

    /***
     * deletes the repository information
     * @param repositoryId repository id
     * @return true or false
     */
    public boolean deleteRepository(int repositoryId)
    {
        boolean isDeleted =false;
        if(repositoryId!=0)
        {
            repositoryRepository.deleteById(repositoryId);
            isDeleted=true;
            LOGGER.info("RepositoryService : Deleting the user information");
        }

        return isDeleted;
    }

    /***
     * converts dto to entity during installation event
     * @param repository dto class
     * @param userId user id
     * @return  RepositoryEntity
     */
    private List<RepositoryEntity> dtoToEntityForInstallationEvent(List<RepositoriesAdded> repository, int userId) {

        List<RepositoryEntity> listOfRepositoryValues = null;

        if (repository != null && userId != 0) {

            listOfRepositoryValues = repository.stream().map(i->{RepositoryEntity repositoryEntity =new RepositoryEntity();
                repositoryEntity.setRepositoryId(i.getId());
                repositoryEntity.setRepositoryName(i.getName());
                UserEntity userEntity=new UserEntity();
                userEntity.setUserId(userId);
                repositoryEntity.setUserEntity(userEntity);
                return repositoryEntity;
            }).collect(Collectors.toList());

            LOGGER.info("RepositoryService : DTO has been converted to Entity");
        } else {
            LOGGER.warn("RepositoryService : User value is null ");
        }
        return listOfRepositoryValues;
    }

    /***
     * converts dto to entity during push event
     * @param repository dto class
     * @param userID user id
     * @return RepositoryEntity
     */
    private RepositoryEntity dtoToEntityForPushEvent(RepositoryDTO repository, int userID) {
        RepositoryEntity repositoryEntity = null;
        if (repository != null)
        {
            repositoryEntity = new RepositoryEntity();
            if(repository.getId() != 0)
            {
                repositoryEntity.setRepositoryId(repository.getId());
            }
            if(repository.getName() != null)
            {
                repositoryEntity.setRepositoryName(repository.getName());
            }
            if(repository.getFork() != null)
            {
                repositoryEntity.setFork(repository.getFork());
            }
            if(userID != 0)
            {
                UserEntity userEntity = new UserEntity();
                userEntity.setUserId(userID);
                repositoryEntity.setUserEntity(userEntity);
            }
            LOGGER.info("RepositoryService : RepositoryRepository DTO has been converted to Entity");
        } else {
            LOGGER.warn("RepositoryService : RepositoryRepository value is null ");
        }
        return repositoryEntity;
    }

    /***
     * converts entity to dto during installation event
     * @param repositoryEntity RepositoryEntity class
     * @return dto class
     */
    private List<RepositoriesAdded> entityToDto(List<RepositoryEntity> repositoryEntity)
    {
        List<RepositoriesAdded> listOfRepositoryAddedDTOValues=null;

        if(repositoryEntity!=null) {

            listOfRepositoryAddedDTOValues= repositoryEntity.stream().map(i->{
                RepositoriesAdded repositories=new RepositoriesAdded();
                repositories.setId(i.getRepositoryId());
                repositories.setName(i.getRepositoryName());
                return repositories;
            }).collect(Collectors.toList());

            LOGGER.info("UserService : Entity has been converted to DTO");
        }
        else {
            LOGGER.warn("UserService : User value is null ");
        }

        return listOfRepositoryAddedDTOValues;
    }
    /***
     * gets the repository information using userId
     * @param userId userId
     * @return RepositoriesAdded dto class
     */
    public List<RepositoriesAdded> getRepositoryFromUserId(Long userId)
    {
        List<RepositoryEntity> repositoriesByUserId = repositoryRepository.findByUserId(userId);
        LOGGER.info("RepositoryService : Getting the repositories from user Id ");
        List<RepositoriesAdded> repositoriesAdded = entityToDto(repositoriesByUserId);
        return repositoriesAdded;
    }

    /***
     * converts entity to dto during push event
     * @param repository RepositoryEntity class
     * @return dto class
     */
    private RepositoryDTO entityToDtoForPushEvent(RepositoryEntity repository) {
        RepositoryDTO repositoryDTO = null;
        if(repository != null)
        {
            repositoryDTO = new RepositoryDTO();
            if(repository.getRepositoryId() != 0)
            {
                repositoryDTO.setId(repository.getRepositoryId());
            }
            if(repository.getRepositoryName() != null)
            {
                repositoryDTO.setName(repository.getRepositoryName());
            }
            if(repository.isFork())
                repositoryDTO.setFork(repository.isFork());
            else
                repositoryDTO.setFork(false);
            LOGGER.info("RepositoryService : Entity has been converted to RepositoryRepository DTO");
        } else {
            LOGGER.warn("RepositoryService : RepositoryRepository value is null ");
        }
        return repositoryDTO;
    }

}

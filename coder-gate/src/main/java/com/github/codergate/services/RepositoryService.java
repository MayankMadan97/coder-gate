package com.github.codergate.services;

import com.github.codergate.dto.installation.RepositoriesAdded;
import com.github.codergate.entities.RepositoryEntity;
import com.github.codergate.entities.UserEntity;
import com.github.codergate.repositories.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class RepositoryService {

    @Autowired
    Repository repository;
    private static final Logger LOGGER = LoggerFactory.getLogger(WebHookListenerService.class);

    /***
     *  adds the repository to the table
     * @param repositories repository information
     * @param userId user id
     * @return RepositoriesAdded dto class
     */
    public List<RepositoriesAdded> addRepository(List<RepositoriesAdded> repositories, int userId)
    {
        List<RepositoriesAdded> repositoriesAdded;
        List<RepositoryEntity> repositoryEntityList=dtoToEntity(repositories,userId);
        List<RepositoryEntity>saveRepository= repositoryEntityList.stream().map(items -> repository.save(items)).collect(Collectors.toList());
        repositoriesAdded = entityToDto(saveRepository);
        return repositoriesAdded;
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
            Optional<RepositoryEntity> repositoryEntity = repository.findById(id);
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
        Optional<RepositoryEntity> repositoryEntities=repository.findById(repositoryId);
        if(repositoryEntities.isPresent())
        {
            List<RepositoryEntity> repositoryEntityList =repositoryEntities.stream().map(i->{
                i.setRepositoryId(repositoryId);
                return i;
            }).collect(Collectors.toList());
            List<RepositoryEntity> saveEntity =repositoryEntityList.stream().map(items -> repository.save(items)).collect(Collectors.toList());
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
            repository.deleteById(repositoryId);
            isDeleted=true;
            LOGGER.info("RepositoryService : Deleting the user information");
        }

        return isDeleted;
    }

    /***
     * converts dto to entity
     * @param repository dto class
     * @param userId user id
     * @return  RepositoryEntity
     */
    private List<RepositoryEntity> dtoToEntity(List<RepositoriesAdded> repository,int userId) {

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
     * converts entity to dto
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
}

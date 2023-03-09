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


    public List<RepositoriesAdded> addRepository(List<RepositoriesAdded> repositories, int userId)
    {
        List<RepositoriesAdded> repositoriesAdded;
        List<RepositoryEntity> repositoryEntityList=dtoToEntity(repositories,userId);
        List<RepositoryEntity>saveRepository= repositoryEntityList.stream().map(items -> repository.save(items)).collect(Collectors.toList());
        repositoriesAdded = entityToDto(saveRepository);
        return repositoriesAdded;
    }



    public List<RepositoriesAdded> getRepository(int userId)
    {
        List<RepositoriesAdded> repositoriesAdded=null;
        Optional<RepositoryEntity> repositoryEntity=repository.findById(userId);
        if(repositoryEntity.isPresent())
        {
            List<RepositoryEntity> entity=new ArrayList<>();
            entity.add(repositoryEntity.get());
            repositoriesAdded=entityToDto(entity);
            LOGGER.info("RepositoryService : Getting the user information");
        }

        return repositoriesAdded;
    }
//
//    public List<RepositoriesAdded> updateRepository(int userId)
//    {
//
//        return null;
//    }


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

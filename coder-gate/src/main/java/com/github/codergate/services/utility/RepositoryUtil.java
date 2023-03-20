package com.github.codergate.services.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.codergate.dto.pullRequest.CommitsInformation;
import com.github.codergate.entities.RepositoryEntity;
import com.github.codergate.entities.UserEntity;
import com.github.codergate.repositories.RepositoryRepository;
import com.github.codergate.repositories.UserRepository;
import com.github.codergate.utils.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;


@Component
public class RepositoryUtil {

    @Autowired
    RestClient restClient;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RepositoryRepository repositoryRepository;
    public List<LinkedHashMap> getCommitsInformation(String installationId, Integer repositoryId) throws JsonProcessingException {
        RepositoryEntity repositoryEntity = repositoryRepository.findById(repositoryId).get();
        UserEntity userEntity = userRepository.findById(repositoryEntity.getUserEntity().getUserId()).get();
        String apiUrl = String.format("https://api.github.com/repos/%s/%s/commits", userEntity.getUserName(), repositoryEntity.getRepositoryName());
        List<LinkedHashMap> json = (List<LinkedHashMap>) restClient.invokeForGet(apiUrl, null, installationId);

        return json;
    }
}

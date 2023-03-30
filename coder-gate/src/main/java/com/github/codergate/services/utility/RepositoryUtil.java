package com.github.codergate.services.utility;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.codergate.entities.RepositoryEntity;
import com.github.codergate.entities.UserEntity;
import com.github.codergate.repositories.RepositoryRepository;
import com.github.codergate.repositories.UserRepository;
import com.github.codergate.utils.Mapper;
import com.github.codergate.utils.RestClient;

@Component
public class RepositoryUtil {

    @Autowired
    RestClient restClient;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RepositoryRepository repositoryRepository;

    /**
     * method fetches commits on a particular repo from github
     * 
     * @param installationId
     * @param repositoryId
     * @return List<LinkedHashMap<String, Object>>
     */
    public List<LinkedHashMap<String, Object>> getCommitsInformation(String installationId, Integer repositoryId) {
        List<LinkedHashMap<String, Object>> commitInfoList = null;
        String apiPrefix = "https://api.github.com/repos/%s/%s/commits";
        Optional<RepositoryEntity> optionalRepo = repositoryRepository.findById(repositoryId);
        if (optionalRepo.isPresent() && installationId != null) {
            RepositoryEntity repositoryEntity = optionalRepo.get();
            Optional<UserEntity> optionalUser = userRepository.findById(repositoryEntity.getUserEntity().getUserId());
            if (optionalUser.isPresent()) {
                UserEntity userEntity = optionalUser.get();
                String apiUrl = String.format(apiPrefix, userEntity.getUserName(),
                        repositoryEntity.getRepositoryName());
                Object apiRespObject = restClient.invokeForGet(apiUrl, null, installationId);
                if (apiRespObject != null) {
                    commitInfoList = Mapper.getInstance().convertValue(apiRespObject,
                            new TypeReference<List<LinkedHashMap<String, Object>>>() {
                            });
                }
            }
        }
        return commitInfoList;
    }
}

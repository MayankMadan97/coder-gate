package com.github.codergate.services;

import com.github.codergate.controllers.WebhookListenerController;
import com.github.codergate.dto.push.PusherPayloadDTO;
import com.github.codergate.entities.BranchEntity;
import com.github.codergate.entities.RepositoryEntity;
import com.github.codergate.entities.TagEntity;
import com.github.codergate.entities.UserEntity;
import com.github.codergate.repositories.BranchRepository;
import com.github.codergate.repositories.Repository;
import com.github.codergate.repositories.TagRepository;
import com.github.codergate.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PushPayloadService {
    @Autowired
    private Repository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private TagRepository tagRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(WebhookListenerController.class);

    public void pushPayload(PusherPayloadDTO payload){
        if (payload != null) {

            UserEntity userEntity = new UserEntity();
            RepositoryEntity repositoryEntity = new RepositoryEntity();
            BranchEntity branchEntity = new BranchEntity();
            TagEntity tagEntity = new TagEntity();

            userEntity.setUserName(payload.getPusher().getName());
            userEntity.setEmail(payload.getPusher().getEmail());
            userEntity.setR1(repositoryEntity);

            repositoryEntity.setRepositoryId(payload.getRepository().getId());
            repositoryEntity.setRepositoryName(payload.getRepository().getName());
            repositoryEntity.setFork(payload.getRepository().getFork());

            branchEntity.setBranchUrl(payload.getRepository().getBranchesUrl());
            branchEntity.setB(repositoryEntity);

            tagEntity.setTagUrl(payload.getRepository().getTagsUrl());
            tagEntity.setT(repositoryEntity);

            this.repository.save(repositoryEntity);
            this.userRepository.save(userEntity);
            this.branchRepository.save(branchEntity);
            this.tagRepository.save(tagEntity);

            LOGGER.debug("payload : Pusher payload saved");
        }
        else {
            LOGGER.warn("payload : Following payload is null");
        }
    }
}

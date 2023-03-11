package com.github.codergate.services;

import com.github.codergate.controllers.WebhookListenerController;
import com.github.codergate.dto.push.PusherPayloadDTO;
import com.github.codergate.entities.*;
import com.github.codergate.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PushPayloadService {
    @Autowired
    private RepositoryRepository repositoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private EventRepository eventRepository;


    private static final Logger LOGGER = LoggerFactory.getLogger(WebhookListenerController.class);

    public void pushPayload(PusherPayloadDTO payload){
        if (payload != null) {


            UserEntity userEntity = new UserEntity();
            RepositoryEntity repositoryEntity = new RepositoryEntity();
            BranchEntity branchEntity = new BranchEntity();
            TagEntity tagEntity = new TagEntity();
            EventEntity eventEntity = new EventEntity();
            ThresholdEntity thresholdEntity = new ThresholdEntity();

            userEntity.setUserId(payload.getSender().getId());
            userEntity.setUserName(payload.getPusher().getName());
            userEntity.setEmail(payload.getPusher().getEmail());

            repositoryEntity.setRepositoryId(payload.getRepository().getId());
            repositoryEntity.setRepositoryName(payload.getRepository().getName());
//            repositoryEntity.setUserEntity(payload.getSender().getId());
            repositoryEntity.setFork(payload.getRepository().getFork());

//            branchEntity.setBranchUrl(payload.getRepository().getBranchesUrl());
//            branchEntity.setRepositoryIdInBranch(repositoryEntity);
//
//            tagEntity.setTagId(payload.getRepository().getTagsUrl());
//            tagEntity.setRepositoryIdInTag(repositoryEntity);

            eventEntity.setRepositoryIdInEvent(repositoryEntity);
            eventEntity.setUserIdInEvent(userEntity);
            eventEntity.setEventName("push");
            eventEntity.setCommitId(payload.getHeadCommit().getId());
            eventEntity.setCommitMessage(payload.getHeadCommit().getMessage());

            this.repositoryRepository.save(repositoryEntity);
            this.userRepository.save(userEntity);
            this.branchRepository.save(branchEntity);
            this.tagRepository.save(tagEntity);
            this.eventRepository.save(eventEntity);


            LOGGER.debug("payload : Pusher payload saved");
        }
        else {
            LOGGER.warn("payload : Following payload is null");
        }
    }
}

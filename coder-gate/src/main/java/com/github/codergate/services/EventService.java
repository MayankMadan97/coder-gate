package com.github.codergate.services;

import com.github.codergate.dto.push.HeadCommitDTO;
import com.github.codergate.entities.EventEntity;
import com.github.codergate.entities.RepositoryEntity;
import com.github.codergate.entities.UserEntity;
import com.github.codergate.repositories.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(WebHookListenerService.class);

    public HeadCommitDTO addEvent(HeadCommitDTO headCommitDTO, int userID, int repositoryID)
    {
        HeadCommitDTO headCommit;
        EventEntity eventEntity = headCommitDtoToEntity(headCommitDTO, userID, repositoryID);
        EventEntity saveEntity = eventRepository.save(eventEntity);
        LOGGER.info("EventService : The event information is added");
        headCommit = entityToHeadCommitDto(saveEntity);
        return headCommit;
    }

    private EventEntity headCommitDtoToEntity(HeadCommitDTO headCommitDTO, int userID, int repositoryID)
    {
        EventEntity eventEntity = null;
        if(headCommitDTO != null && userID != 0 && repositoryID != 0)
        {
            eventEntity = new EventEntity();
            if(headCommitDTO.getId() != null)
            {
                eventEntity.setCommitId(headCommitDTO.getId());
            }
            if(headCommitDTO.getMessage() != null)
            {
                eventEntity.setCommitMessage(headCommitDTO.getMessage());
            }

            eventEntity.setEventName("push");

            UserEntity userEntity = new UserEntity();
            userEntity.setUserId(userID);
            eventEntity.setUserIdInEvent(userEntity);

            RepositoryEntity repositoryEntity = new RepositoryEntity();
            repositoryEntity.setRepositoryId(repositoryID);
            eventEntity.setRepositoryIdInEvent(repositoryEntity);

            LOGGER.info("EventService : HeadCommit DTO has been converted to Entity");
        } else {
            LOGGER.warn("EventService : User value is null ");
        }
        return eventEntity;
    }

    private HeadCommitDTO entityToHeadCommitDto(EventEntity event)
    {
        HeadCommitDTO headcommit = null;
        if(event != null)
        {
            headcommit = new HeadCommitDTO();
            if(event.getEventId() != 0L)
            {
                headcommit.setId(event.getCommitId());
            }
            if(event.getCommitMessage() != null)
            {
                headcommit.setMessage(event.getCommitMessage());
            }
            LOGGER.info("EventService : Entity has been converted to HeadCommit DTO");
        } else {
            LOGGER.warn("EventService : User value is null ");
        }
        return headcommit;
    }
}

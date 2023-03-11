package com.github.codergate.services;

import com.github.codergate.dto.push.HeadCommitDTO;
import com.github.codergate.dto.installation.InstallationPayload;
import com.github.codergate.entities.EventEntity;
import com.github.codergate.entities.RepositoryEntity;
import com.github.codergate.entities.UserEntity;
import com.github.codergate.repositories.EventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    private EventEntity headCommitDtoToEntity(HeadCommitDTO headCommitDTO, int userID, int repositoryID) {
        EventEntity eventEntity = null;
        if (headCommitDTO != null && userID != 0 && repositoryID != 0) {
            eventEntity = new EventEntity();
            if (headCommitDTO.getId() != null) {
                eventEntity.setCommitId(headCommitDTO.getId());
            }
            if (headCommitDTO.getMessage() != null) {
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

    }
    /***
     *  adds the required event done by user into the database
     * @param event event name
     * @param userId user id
     * @param repositoryIdList repository ids
     * @return dto class
     */
    public InstallationPayload addEvent(String event,int userId,List<Integer>repositoryIdList)
    {
        InstallationPayload installationPayload;
        List<EventEntity> eventEntity=dtoToEntity(event,userId,repositoryIdList);
        List<EventEntity> saveEvent=eventRepository.saveAll(eventEntity);
        LOGGER.info("EventService : adding the event information");
        installationPayload=entityToDto(saveEvent);
        return installationPayload;
    }

    /****
     * Gets the event information
     * @param eventId event id
     * @return entity class
     */
    public EventEntity getEvent(Long eventId){
        EventEntity eventEntity = null;
        Optional<EventEntity> optionalEventEntity=eventRepository.findById(eventId.intValue());
        if(optionalEventEntity.isPresent())
        {
            eventEntity = optionalEventEntity.get();
            LOGGER.info("EventService : Getting the event information");
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

    /***
     *  Updates the even information
     * @param eventId event id
     * @return entity class
     */
    public EventEntity updateEntity(Long eventId)
    {
        EventEntity eventEntity=eventRepository.findById(eventId.intValue()).orElse(null);
        if(eventEntity!=null)
        {
            EventEntity saveEvent = eventRepository.save(eventEntity);
            LOGGER.info("EventService : Updating the event information");
        }
        return eventEntity;
    }

    /***
     * Delete the events
     * @param eventId event id
     * @return entity class
     */
    public boolean deleteEvent(int eventId)
    {
        boolean isDeleted =false;
        if(eventId!=0)
        {
            eventRepository.deleteById(eventId);
            isDeleted=true;
            LOGGER.info("EventService : Deleting the repository information");
        }
        return isDeleted;
    }


    /***
     *  converts dto class to entity
     * @param event event name
     * @param userId user id
     * @param repositoryIdList repository ids
     * @return entity
     */
    private List<EventEntity> dtoToEntity(String event,int userId, List<Integer>repositoryIdList)
    {
        List<EventEntity> eventEntityList=new ArrayList<>();
        EventEntity eventEntity;
        if(event!=null && userId!=0 && repositoryIdList!=null)
        {
            UserEntity userEntity=new UserEntity();
            userEntity.setUserId(userId);
            for(int i:repositoryIdList)
            {
                eventEntity = new EventEntity();
                eventEntity.setEventName(event);
                RepositoryEntity repositoryEntity=new RepositoryEntity();
                repositoryEntity.setRepositoryId(i);
                eventEntity.setRepositoryIdInEvent(repositoryEntity);
                eventEntity.setUserIdInEvent(userEntity);
                eventEntityList.add(eventEntity);
            }
            LOGGER.info("EventService : DTO has been converted to Entity");
        }else {
            LOGGER.warn("EventService : User value is null ");
        }
        return eventEntityList;
    }

    /***
     *  converts entity class to dto class
     * @param event event information
     * @return dto class
     */
    private InstallationPayload entityToDto(List<EventEntity> event)
    {
        InstallationPayload installationPayload = null;
        if(event!=null)
        {
            installationPayload=new InstallationPayload();
            if(event.get(0).getEventName()!=null)
            {
                installationPayload.setAction(event.get(0).getEventName());
            }

            LOGGER.info("EventService : Entity has been converted to DTO");
        }else {
            LOGGER.warn("EventService : event value is null ");
        }
        return installationPayload;
    }

}

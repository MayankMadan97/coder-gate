package com.github.codergate.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.codergate.dto.installation.InstallationPayloadDTO;
import com.github.codergate.dto.push.HeadCommitDTO;
import com.github.codergate.entities.EventEntity;
import com.github.codergate.entities.RepositoryEntity;
import com.github.codergate.entities.UserEntity;
import com.github.codergate.repositories.EventRepository;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(EventService.class);

    /***
     * adds the required event done by user into the database
     * 
     * @param eventType        event name
     * @param userId           user id
     * @param repositoryIdList repositoryRepository ids
     * @return dto class
     */
    public InstallationPayloadDTO addEvent(String eventType, int userId, List<Integer> repositoryIdList) {
        InstallationPayloadDTO installationPayloadDTO;
        List<EventEntity> eventEntity = convertDTOToEntity(eventType, userId, repositoryIdList);
        List<EventEntity> savedEvent = eventRepository.saveAll(eventEntity);
        LOGGER.info("addEvent : adding the event information {}", savedEvent);
        installationPayloadDTO = convertEntityToDTO(savedEvent);
        return installationPayloadDTO;
    }

    /***
     * Adds push event details to table
     * 
     * @param headCommitDTO HeadCommitDTO object
     * @param userID        ID of user in integer format
     * @param repositoryID  ID of repository in integer format
     * @return HeadCommitDTO object
     */
    public EventEntity addEvent(HeadCommitDTO headCommitDTO, int userID, int repositoryID) {
        HeadCommitDTO headCommit = null;
        EventEntity eventEntity = headCommitDtoToEntity(headCommitDTO, userID, repositoryID);
        if (eventEntity != null) {
            EventEntity savedEntity = eventRepository.save(eventEntity);
            LOGGER.info("addEvent : The event information for commit is added {}", savedEntity);
            headCommit = entityToHeadCommitDto(savedEntity);
        }
        return eventEntity;
    }

    /****
     * Gets the event information
     * 
     * @param eventId event id
     * @return entity class
     */
    public EventEntity getEventById(Long eventId) {
        EventEntity eventEntity = null;
        Optional<EventEntity> optionalEventEntity = eventRepository.findById(eventId.intValue());
        if (optionalEventEntity.isPresent()) {
            eventEntity = optionalEventEntity.get();
            LOGGER.info("getEventById : Getting the event information {}", eventId);
        } else {
            LOGGER.warn("getEventById: Event with ID: {} not found", eventId);
        }
        return eventEntity;
    }

    /***
     * Updates the even information
     * 
     * @param eventId event id
     * @return entity class
     */
    public EventEntity updateEntity(Long eventId) {
        EventEntity eventEntity = eventRepository.findById(eventId.intValue()).orElse(null);
        if (eventEntity != null) {
            // perform further implementation when required
            EventEntity saveEvent = eventRepository.save(eventEntity);
            LOGGER.info("updateEntity : Updating the event information");
        }
        return eventEntity;
    }

    /***
     * Delete the events
     * 
     * @param eventId event id
     * @return entity class
     */
    public boolean deleteEventById(int eventId) {
        boolean isDeleted = false;
        if (eventId != 0) {
            eventRepository.deleteById(eventId);
            isDeleted = true;
            LOGGER.info("deleteEventById : Deleting the event information {}", eventId);
        }
        return isDeleted;
    }

    /***
     * converts dto class to entity
     * 
     * @param eventTypeName    event name
     * @param userId           user id
     * @param repositoryIdList repositoryRepository ids
     * @return entity
     */
    private List<EventEntity> convertDTOToEntity(String eventTypeName, int userId, List<Integer> repositoryIdList) {
        List<EventEntity> eventEntityList = new ArrayList<>();
        EventEntity eventEntity;
        if (eventTypeName != null) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserId(userId);
            for (int id : repositoryIdList) {
                eventEntity = new EventEntity();
                eventEntity.setEventName(eventTypeName);
                RepositoryEntity repositoryEntity = new RepositoryEntity();
                repositoryEntity.setRepositoryId(id);
                eventEntity.setRepositoryIdInEvent(repositoryEntity);
                eventEntity.setUserIdInEvent(userEntity);
                eventEntityList.add(eventEntity);
            }
            LOGGER.info("convertDTOToEntity : DTO has been converted to Entity {}", eventEntityList);
        } else {
            LOGGER.warn("convertDTOToEntity : Event Entity value is null");
        }
        return eventEntityList;
    }

    /***
     * Converts HeadCommitDTO to Event Entity
     * 
     * @param headCommitDTO HeadCommitDTO
     * @param userID        ID of user in integer format
     * @param repositoryID  ID of repository in integer format
     * @return Event Entity
     */
    private EventEntity headCommitDtoToEntity(HeadCommitDTO headCommitDTO, int userID, int repositoryID) {
        EventEntity eventEntity = null;
        if (headCommitDTO != null) {
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
            LOGGER.info("headCommitDtoToEntity : HeadCommit DTO has been converted to Entity {}", eventEntity);
        } else {
            LOGGER.warn("headCommitDtoToEntity : Event Entity for HeadCommit is null ");
        }
        return eventEntity;
    }

    /***
     * converts entity class to dto class
     * 
     * @param eventType event information
     * @return dto class
     */
    private InstallationPayloadDTO convertEntityToDTO(List<EventEntity> eventType) {
        InstallationPayloadDTO installationPayloadDTO = null;
        if (eventType != null) {
            installationPayloadDTO = new InstallationPayloadDTO();
            if (eventType.get(0).getEventName() != null) {
                installationPayloadDTO.setAction(eventType.get(0).getEventName());
            }
            LOGGER.info("InstallationPayloadDTO : Entity has been converted to DTO {}", installationPayloadDTO);
        } else {
            LOGGER.warn("InstallationPayloadDTO : converting entity to installationPayloadDTO is null ");
        }
        return installationPayloadDTO;
    }

    /***
     * Converts Event Entity to HeadCommitDTO
     * 
     * @param eventType Event Entity
     * @return HeadCommitDTO object
     */
    private HeadCommitDTO entityToHeadCommitDto(EventEntity eventType) {
        HeadCommitDTO headCommitDTO = null;
        if (eventType != null) {
            headCommitDTO = new HeadCommitDTO();
            if (eventType.getEventId() != 0L) {
                headCommitDTO.setId(eventType.getCommitId());
            }
            if (eventType.getCommitMessage() != null) {
                headCommitDTO.setMessage(eventType.getCommitMessage());
            }
            LOGGER.info("entityToHeadCommitDto : Entity has been converted to HeadCommit DTO {}", headCommitDTO);
        } else {
            LOGGER.warn("entityToHeadCommitDto : converting entity to headCommitDTO is null");
        }
        return headCommitDTO;
    }

}

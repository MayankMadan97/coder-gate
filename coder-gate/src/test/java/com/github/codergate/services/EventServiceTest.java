package com.github.codergate.services;

import com.github.codergate.dto.installation.InstallationPayloadDTO;
import com.github.codergate.dto.push.HeadCommitDTO;
import com.github.codergate.entities.EventEntity;
import com.github.codergate.entities.RepositoryEntity;
import com.github.codergate.entities.UserEntity;
import com.github.codergate.repositories.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {
    @Mock
    EventRepository eventRepositoryMock;
    @InjectMocks
    EventService eventServiceMock;

    private final List<EventEntity> eventEntityList = new ArrayList<>();
    private final EventEntity eventEntity = new EventEntity();
    private final RepositoryEntity repositoryEntity = new RepositoryEntity();
    private final  UserEntity userEntity = new UserEntity();


    String eventType = "create";
    int repositoryId=1;
    int userId = 32;
    int eventId = 1;
    Long eventIds = 111L;
    long updatedId =12;





    @Test
    void testAddEventWhenAllDataIsPresent() {
        int repositoryIdTwo=1;
        int repositoryIdThree=1;
        List<Integer> repositoryIdList = Arrays.asList(repositoryId, repositoryIdTwo, repositoryIdThree);
        eventEntity.setEventId(eventId);
        eventEntity.setEventName(eventType);
        eventEntityList.add(eventEntity);
        when(eventRepositoryMock.saveAll(Mockito.anyList())).thenReturn(eventEntityList);
        InstallationPayloadDTO actual = eventServiceMock.addEvent(eventType, userId, repositoryIdList);
        assertEquals(eventEntityList.get(0).getEventName(),actual.getAction());

    }

    @Test
    void testAddEventNullInputs() {
        String eventType = null;
        List<Integer> repositoryIdList = new ArrayList<>();
        repositoryIdList.add(repositoryId);
        when(eventRepositoryMock.saveAll(Mockito.anyList())).thenReturn(null);
        InstallationPayloadDTO result = eventServiceMock.addEvent(eventType, userId, repositoryIdList);
        assertNull(result);
    }

    @Test
    void testAddEventCheckForAllValues() {
        repositoryEntity.setRepositoryId(repositoryId);
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        List<Integer> repositoryIdList = new ArrayList<>();
        repositoryIdList.add(repositoryEntity.getRepositoryId());
        eventEntity.setEventName(eventType);
        eventEntity.setRepositoryIdInEvent(repositoryEntity);
        eventEntity.setUserIdInEvent(userEntity);
        eventEntityList.add(eventEntity);
        when(eventRepositoryMock.saveAll(Mockito.anyList())).thenReturn(eventEntityList);
        InstallationPayloadDTO result = eventServiceMock.addEvent(eventType, userId, repositoryIdList);
        assertNotNull(result);
    }

    @Test
    void testAddEventCheckForEventTypes() {
        String eventType = null;
        repositoryEntity.setRepositoryId(repositoryId);
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userId);
        List<Integer> repositoryIdList = new ArrayList<>();
        repositoryIdList.add(repositoryEntity.getRepositoryId());
        List<EventEntity> eventEntityList=null;
        when(eventRepositoryMock.saveAll(Mockito.anyList())).thenReturn(eventEntityList);
        InstallationPayloadDTO result = eventServiceMock.addEvent(eventType, userId, repositoryIdList);
        assertNull(result);
    }

    @Test
    void testAddEventCheckForEventNameIsNull() {
        repositoryEntity.setRepositoryId(repositoryId);
        userEntity.setUserId(userId);
        List<Integer> repositoryIdList = new ArrayList<>();
        repositoryIdList.add(repositoryEntity.getRepositoryId());
        List<EventEntity> eventEntityList = new ArrayList<>();
        EventEntity eventEntityOne = new EventEntity();
        eventEntityOne.setEventName(null);
        eventEntityOne.setRepositoryIdInEvent(repositoryEntity);
        eventEntityOne.setUserIdInEvent(userEntity);
        eventEntityList.add(eventEntityOne);
        when(eventRepositoryMock.saveAll(Mockito.anyList())).thenReturn(eventEntityList);
        InstallationPayloadDTO result = eventServiceMock.addEvent(eventType, userId, repositoryIdList);
        assertNotNull(result);
    }


    @Test
    void testDeleteEvent()
    {
        int wantedNumberOfInvocation =1;
        Mockito.doNothing().when(eventRepositoryMock).deleteById(eventId);
        boolean actual = eventServiceMock.deleteEventById(eventId);
        assertTrue(actual);
        verify(eventRepositoryMock, Mockito.times(wantedNumberOfInvocation)).deleteById(eventId);
    }

    @Test
    void deleteEventByIdShouldNotDeleteEventWhenIdIsZero() {
        int eventId = 0;
        int wantedNumberOfInvocation =0;
        boolean actual = eventServiceMock.deleteEventById(eventId);
        assertFalse(actual);
        verify(eventRepositoryMock, Mockito.times(wantedNumberOfInvocation)).deleteById(eventId);
    }

    @Test
    void testEventByIdWithValidId() {
        EventEntity expected = new EventEntity();
        when(eventRepositoryMock.findById(eventIds.intValue())).thenReturn(Optional.of(expected));
        EventEntity actual = eventServiceMock.getEventById(eventIds);
        assertEquals(expected, actual);
        verify(eventRepositoryMock).findById(eventIds.intValue());
        verifyNoMoreInteractions(eventRepositoryMock);

    }

    @Test
    void testEventByIdWithInvalidId() {
        when(eventRepositoryMock.findById(eventIds.intValue())).thenReturn(Optional.empty());
        EventEntity actual = eventServiceMock.getEventById(eventIds);
        assertNull(actual);
        verify(eventRepositoryMock).findById(eventIds.intValue());
        verifyNoMoreInteractions(eventRepositoryMock);
    }

    @Test
    void testUpdateEntity() {
        int wantedNumberOfInvocation =1;
        eventEntity.setEventId(eventId);
        eventEntity.setEventName("Testing");
        Mockito.when(eventRepositoryMock.findById(Mockito.anyInt())).thenReturn(Optional.of(eventEntity));
        Mockito.when(eventRepositoryMock.save(Mockito.any(EventEntity.class))).thenReturn(eventEntity);
        EventEntity actual = eventServiceMock.updateEntity(updatedId);
        assertEquals(actual.getEventId(), eventEntity.getEventId());
        Mockito.verify(eventRepositoryMock, Mockito.times(wantedNumberOfInvocation)).save(Mockito.any(EventEntity.class));
    }

    @Test
    void testUpdateEntityWhenEventNotFound() {
        int wantedNumberOfInvocation =1;
        when(eventRepositoryMock.findById(anyInt())).thenReturn(Optional.empty());
        EventEntity actual = eventServiceMock.updateEntity((long) eventId);
        assertNull(actual);
        verify(eventRepositoryMock, times(wantedNumberOfInvocation)).findById(eventId);
        verifyNoMoreInteractions(eventRepositoryMock);
    }



    @Test
    void testAddEventFromPushEvent()
    {
        EventEntity savedEventEntity = new EventEntity();
        savedEventEntity.setEventId(eventId);
        when(eventRepositoryMock.save(any(EventEntity.class))).thenReturn(savedEventEntity);
        HeadCommitDTO headCommitDTO = new HeadCommitDTO();
        headCommitDTO.setId("commit_id");
        headCommitDTO.setMessage("commit_message");
        EventEntity actual = eventServiceMock.addEvent(headCommitDTO, userId, repositoryId);
        assertNotNull(actual);
    }




    @Test
    void testAddPushEventWithUserRepositoryIdIsNull(){
        HeadCommitDTO headCommitDTO = new HeadCommitDTO();
        int repositoryID = 0;
        EventEntity result = eventServiceMock.addEvent(headCommitDTO, userId, repositoryID);
        assertNotNull(result);
    }

    @Test
    void testAddPushEventWithUserRepositoryIdIsNull1(){
        HeadCommitDTO headCommitDTO = null;
        EventEntity result = eventServiceMock.addEvent(headCommitDTO, userId, repositoryId);
        assertNull(result);
    }


    @Test
    void testAddEventFromPushEventWhenIdCommitIsNull()
    {
        HeadCommitDTO headCommitDTO = new HeadCommitDTO();
        headCommitDTO.setId(null);
        headCommitDTO.setMessage(null);
        int eventId =0;
        EventEntity savedEventEntity = new EventEntity();
        savedEventEntity.setEventId(eventId);
        savedEventEntity.setCommitMessage(null);
        when(eventRepositoryMock.save(any(EventEntity.class))).thenReturn(savedEventEntity);
        EventEntity actual = eventServiceMock.addEvent(headCommitDTO, userId, repositoryId);
        assertNotNull(actual);
    }

    @Test
    void testAddEventFromPushEventWhenIdCommitMessageIsNull()
    {
        HeadCommitDTO headCommitDTO = new HeadCommitDTO();
        headCommitDTO.setId(null);
        headCommitDTO.setMessage(null);
        EventEntity savedEventEntity = new EventEntity();
        savedEventEntity.setCommitMessage("created repository");
        when(eventRepositoryMock.save(any(EventEntity.class))).thenReturn(savedEventEntity);
        EventEntity actual = eventServiceMock.addEvent(headCommitDTO, userId, repositoryId);
        assertNotNull(actual);
    }



}
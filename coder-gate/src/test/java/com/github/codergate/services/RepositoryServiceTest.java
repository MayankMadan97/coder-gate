package com.github.codergate.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.codergate.dto.installation.RepositoriesAddedDTO;
import com.github.codergate.entities.RepositoryEntity;
import com.github.codergate.entities.UserEntity;
import com.github.codergate.repositories.RepositoryRepository;

@ExtendWith(MockitoExtension.class)
class RepositoryServiceTest {

    @Mock
    RepositoryRepository repositoryMock;
    @InjectMocks
    RepositoryService repositoryServiceMock;

    RepositoryService repositoryService = new RepositoryService();

    private final List<RepositoryEntity> repositoryEntities = new ArrayList<>();
    private final RepositoryEntity repositoryOne = new RepositoryEntity();
    private final List<RepositoriesAddedDTO> repositoriesAddedDTOS = new ArrayList<>();
    private final UserEntity userEntity = new UserEntity();

    long userId = 32;
    int repositoryId = 1234;
    int repositoryIdTwo = 4567;
    int id = 10;

    @Test
    void testGetRepositoryFromUserId() {

        repositoryOne.setRepositoryId(repositoryId);
        repositoryOne.setRepositoryName("TestRepository");
        repositoryEntities.add(repositoryOne);
        RepositoryEntity repositoryTwo = new RepositoryEntity();
        repositoryTwo.setRepositoryId(repositoryIdTwo);
        repositoryTwo.setRepositoryName("DevRepository");
        repositoryEntities.add(repositoryTwo);
        when(repositoryMock.findByUserId(userId)).thenReturn(repositoryEntities);
        List<RepositoriesAddedDTO> actual = repositoryServiceMock.getRepositoryFromUserId(userId);
        RepositoriesAddedDTO repositoriesAddedDTOOne = new RepositoriesAddedDTO();
        List<RepositoriesAddedDTO> expected = new ArrayList<>();
        repositoriesAddedDTOOne.setId(repositoryId);
        repositoriesAddedDTOOne.setName("TestRepository");
        expected.add(repositoriesAddedDTOOne);
        RepositoriesAddedDTO repositoriesAddedDTOTwo = new RepositoriesAddedDTO();
        repositoriesAddedDTOTwo.setId(repositoryId);
        repositoriesAddedDTOTwo.setName("DevRepository");
        expected.add(repositoriesAddedDTOTwo);
        assertNotNull(actual);

    }

    @Test
    void testGetRepositoryFromUserIdWhenRepositoryIsEmpty() {
        when(repositoryMock.findByUserId(userId)).thenReturn(repositoryEntities);
        List<RepositoriesAddedDTO> actual = repositoryServiceMock.getRepositoryFromUserId(userId);
        assertNotNull(actual);
    }

    @Test
    void testGetRepositoryFromUserIdWhenUserIdIsInvalid() {
        when(repositoryMock.findByUserId(userId)).thenReturn(null);
        List<RepositoriesAddedDTO> actual = repositoryServiceMock.getRepositoryFromUserId(userId);
        assertNull(actual);
    }

    @Test
    void testDeleteRepositoryById() {
        int wantedNumberOfInvocation = 1;
        Mockito.doNothing().when(repositoryMock).deleteById(repositoryId);
        boolean isDeleted = repositoryServiceMock.deleteRepositoryById(repositoryId);
        assertTrue(isDeleted);
        verify(repositoryMock, Mockito.times(wantedNumberOfInvocation)).deleteById(repositoryId);
    }

    @Test
    void testDeleteRepositoryByIDWhenNullOrZero() {
        int repositoryId = 0;
        int wantedNumberOfInvocation = 0;
        boolean isDeleted = repositoryServiceMock.deleteRepositoryById(repositoryId);
        assertFalse(isDeleted);
        verify(repositoryMock, Mockito.times(wantedNumberOfInvocation)).deleteById(repositoryId);
    }

    @Test
    void testGetRepositoryByIdWhenIdsAreValidAndInValid() {
        List<Integer> repositoryIds = Arrays.asList(repositoryId, repositoryIdTwo);
        repositoryOne.setRepositoryId(repositoryId);
        repositoryOne.setRepositoryName("TestRepository");
        List<RepositoryEntity> expected = List.of(repositoryOne);
        when(repositoryMock.findById(repositoryId)).thenReturn(Optional.of(repositoryOne));
        when(repositoryMock.findById(repositoryIdTwo)).thenReturn(Optional.empty());
        List<RepositoriesAddedDTO> actual = repositoryServiceMock.getRepositoryById(repositoryIds);
        assertEquals(expected.get(0).getRepositoryName(), actual.get(0).getName());
    }

    @Test
    void testAddRepositoryWithEmptyList() {
        List<RepositoriesAddedDTO> actual = repositoryServiceMock.addRepository(repositoriesAddedDTOS, (int) userId);
        assertTrue(actual.isEmpty());

    }

    @Test
    void testAddRepositoryWithNullValues() {
        List<RepositoriesAddedDTO> repositoriesAddedDTOS = null;
        int userId = 0;
        List<RepositoriesAddedDTO> actual = repositoryService.addRepository(repositoriesAddedDTOS, userId);
        assertNull(actual);

    }

    @Test
    void testAddRepositoryWithCorrectValues() {

        RepositoriesAddedDTO repositoryOne = new RepositoriesAddedDTO();
        repositoryOne.setId(repositoryId);
        repositoryOne.setName("TestRepository");
        repositoriesAddedDTOS.add(repositoryOne);
        RepositoriesAddedDTO repositoryTwo = new RepositoriesAddedDTO();
        repositoryTwo.setId(repositoryIdTwo);
        repositoryTwo.setName("DevRepository");
        repositoriesAddedDTOS.add(repositoryTwo);
        List<RepositoryEntity> expected = new ArrayList<>();
        RepositoryEntity repositoryEntityOne = new RepositoryEntity();
        repositoryEntityOne.setRepositoryId(repositoryId);
        repositoryEntityOne.setRepositoryName("TestRepository");
        expected.add(repositoryEntityOne);
        RepositoryEntity repositoryEntityTwo = new RepositoryEntity();
        repositoryEntityTwo.setRepositoryId(repositoryIdTwo);
        repositoryEntityTwo.setRepositoryName("DevRepository");
        expected.add(repositoryEntityTwo);
        when(repositoryMock.saveAll(anyList())).thenReturn(expected);
        List<RepositoriesAddedDTO> actual = repositoryServiceMock.addRepository(repositoriesAddedDTOS, (int) userId);
        assertEquals(expected.get(0).getRepositoryId(), actual.get(0).getId());

    }

    @Test
    void testAddRepositoryWithRepositoryDTOIsNUllOrUserIdIsNull() {
        int userId = 0;
        RepositoriesAddedDTO repositoryOne = new RepositoriesAddedDTO();
        repositoryOne.setId(userId);
        repositoryOne.setName(null);
        repositoriesAddedDTOS.add(repositoryOne);
        List<RepositoriesAddedDTO> actual = repositoryServiceMock.addRepository(repositoriesAddedDTOS, userId);
        assertNull(actual);
        verify(repositoryMock, never()).saveAll(anyList());

    }

    @Test
    void testUpdateRepository() {
        repositoryOne.setRepositoryId((int) userId);
        repositoryOne.setRepositoryName("TestRepository");
        List<RepositoryEntity> savedEntities = new ArrayList<>();
        savedEntities.add(repositoryOne);
        List<RepositoriesAddedDTO> expectedDto = new ArrayList<>();
        RepositoriesAddedDTO repositoryDto = new RepositoriesAddedDTO();
        repositoryDto.setId((int) userId);
        repositoryDto.setName("TestRepository");
        expectedDto.add(repositoryDto);
        Mockito.when(repositoryMock.findById(Mockito.anyInt())).thenReturn(Optional.of(repositoryOne));
        Mockito.when(repositoryMock.save(Mockito.any(RepositoryEntity.class))).thenReturn(repositoryOne);
        List<RepositoriesAddedDTO> actualDto = repositoryServiceMock.updateRepository((int) userId);
        assertNotNull(expectedDto.toString(), actualDto.toString());
    }

    @Test
    void testUpdateRepositoryWhenNull() {

        when(repositoryMock.findById(repositoryId)).thenReturn(Optional.empty());
        List<RepositoriesAddedDTO> actual = repositoryServiceMock.updateRepository(repositoryId);
        assertNull(actual);
    }

    @Test
    void testAddRepositoryWhenIdIsNUll() {

        int repositoryId = 0;
        String name = "abc";
        String installationId = "12344";
        RepositoryEntity result = repositoryServiceMock.addRepository(repositoryId, name, (int) userId, installationId);
        assertNull(result);

    }

    @Test
    void testAddRepositoryReturnsNullWhenNameIsNull() {

        String name = null;
        String installationId = "12344";
        RepositoryEntity result = repositoryServiceMock.addRepository(id, name, (int) userId, installationId);
        assertNull(result);

    }

    @Test
    void testAddRepositoryReturnsNullUserIdIsNull() {

        String name = "abc";
        int userId = 0;
        String installationId = "12344";
        RepositoryEntity result = repositoryServiceMock.addRepository(id, name, userId, installationId);
        assertNull(result);

    }

    @Test
    void testAddRepositoryReturnsNullInstallationIdIsNull() {

        String name = "abc";
        String installationId = null;
        RepositoryEntity result = repositoryServiceMock.addRepository(id, name, (int) userId, installationId);
        assertNull(result);

    }

    @Test
    void testAddRepositoryWithCorrectValuesForPush() {

        String name = "Test Repository";
        String installationId = "123";
        repositoryOne.setRepositoryId(id);
        repositoryOne.setRepositoryName(name);
        userEntity.setUserId(userId);
        repositoryOne.setUserEntity(userEntity);
        repositoryOne.setInstallationId(installationId);
        Mockito.when(repositoryMock.save(Mockito.any(RepositoryEntity.class))).thenReturn(repositoryOne);
        RepositoryEntity actualEntity = repositoryServiceMock.addRepository(id, name, (int) userId, installationId);
        assertEquals(id, actualEntity.getRepositoryId());
    }

}
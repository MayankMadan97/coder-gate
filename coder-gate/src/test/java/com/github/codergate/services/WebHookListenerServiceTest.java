package com.github.codergate.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.codergate.dto.installation.AccountDTO;
import com.github.codergate.dto.installation.Installation;
import com.github.codergate.dto.installation.InstallationPayloadDTO;
import com.github.codergate.dto.installation.RepositoriesAddedDTO;
import com.github.codergate.dto.pullRequest.Payload;
import com.github.codergate.dto.push.HeadCommitDTO;
import com.github.codergate.dto.push.OwnerDTO;
import com.github.codergate.dto.push.PusherDTO;
import com.github.codergate.dto.push.RepositoryDTO;
import com.github.codergate.dto.push.SenderDTO;
import com.github.codergate.dto.threshold.ThresholdDTO;
import com.github.codergate.entities.RepositoryEntity;
import com.github.codergate.entities.TagEntity;
import com.github.codergate.entities.TagId;
import com.github.codergate.entities.UserEntity;
import com.github.codergate.utils.WebHookListenerUtil;

@ExtendWith(MockitoExtension.class)
class WebHookListenerServiceTest {

    @InjectMocks
    WebHookListenerService webHookListenerServiceMock;

    @Mock
    Logger logger;
    @Mock
    RepositoryService repositoryServiceMock = new RepositoryService();

    @Mock
    EventService eventServiceMock = new EventService();

    @Mock
    UserService userServiceMock = new UserService();

    @Mock
    TagService tagServiceMock = new TagService();

    @Mock
    BranchService branchServiceMock = new BranchService();

    @Mock
    PullRequestService pullRequestService = new PullRequestService();

    @Mock
    WebHookListenerUtil webHookListenerUtil = new WebHookListenerUtil();
    @Mock
    ThresholdService thresholdService = new ThresholdService();

    private final Map<String, Object> mockPayload = new HashMap<>();
    private final Map<String, Object> installationMock = new HashMap<>();
    private final Map<String, Object> accountMock = new HashMap<>();
    private final Map<String, Object> repositoriesMock = new HashMap<>();
    private final List<Map<String, Object>> repositoriesList = new ArrayList<>();
    InstallationPayloadDTO installationPayloadDTO = new InstallationPayloadDTO();
    private final AccountDTO accountDTO = new AccountDTO();
    List<RepositoriesAddedDTO> repositoriesAddedDTOList = new ArrayList<>();
    RepositoriesAddedDTO repositoriesAddedDTO = new RepositoriesAddedDTO();
    List<Integer> repositoryIdList = new ArrayList<>();
    OwnerDTO ownerDTO = new OwnerDTO();
    RepositoryDTO repositoryDTO = new RepositoryDTO();
    SenderDTO senderDTO = new SenderDTO();
    Installation installation = new Installation();
    HeadCommitDTO headCommitDTO = new HeadCommitDTO();
    UserEntity userEntity = new UserEntity();
    RepositoryEntity repositoryEntity = new RepositoryEntity();
    TagEntity tagEntity = new TagEntity();
    Payload payload = new Payload();

    int userId = 1;
    int installationUserId = 123;
    int repositoryId = 12;
    int setId = 111;

    @Test
    void testHandleDeletionWhenAllValuesArePresent() {

        long userId = 1;
        mockPayload.put("action", "deleted");
        repositoriesMock.put("name", "alex");
        repositoriesList.add(repositoriesMock);
        accountMock.put("id", userId);
        installationMock.put("id", installationUserId);
        mockPayload.put("repositories", repositoriesList);
        mockPayload.put("installation", installationMock);
        installationMock.put("account", accountMock);
        accountDTO.setId((int) userId);
        when(userServiceMock.getUserById(userId)).thenReturn(accountDTO);
        webHookListenerServiceMock.listen(mockPayload);
        verify(userServiceMock).getUserById(userId);
        assertNotNull(mockPayload);
        verify(logger, never()).info(eq("The following payload is null"));

    }

    @Test
    void testHandleDeletionWhenALLValueInPayloadIsNull() {

        mockPayload.put("action", "deleted");
        repositoriesList.add(null);
        mockPayload.put("repositories", null);
        mockPayload.put("installation", null);
        installationMock.put("account", null);
        webHookListenerServiceMock.listen(mockPayload);
        assertNotNull(mockPayload);
        verify(logger, never()).info(eq("The following payload is null"));

    }

    @Test
    void testHandleDeletionWhenInstallationValueInPayloadIsNull() {

        mockPayload.put("installation", null);
        repositoriesMock.put("name", "alex");
        repositoriesList.add(repositoriesMock);
        mockPayload.put("repositories", repositoriesList);
        mockPayload.put("action", "deleted");
        webHookListenerServiceMock.listen(mockPayload);
        assertNotNull(mockPayload);
        verify(logger, never()).info(eq("The following payload is null"));

    }

    @Test
    void testHandleDeletionWhenAccountValueInPayloadIsNull() {

        installationMock.put("account", null);
        installationMock.put("id", installationUserId);
        mockPayload.put("installation", installationMock);
        repositoriesMock.put("name", "alex");
        repositoriesList.add(repositoriesMock);
        mockPayload.put("repositories", repositoriesList);
        mockPayload.put("action", "deleted");
        webHookListenerServiceMock.listen(mockPayload);
        assertNotNull(mockPayload);

    }

    @Test
    void testHandleDeletionWhenRepositoriesValueInPayloadIsNull() {

        accountMock.put("id", userId);
        installationMock.put("account", accountMock);
        mockPayload.put("installation", installationMock);
        mockPayload.put("repositories", null);
        mockPayload.put("action", "deleted");
        webHookListenerServiceMock.listen(mockPayload);
        assertNotNull(mockPayload);
    }

    @Test
    void testHandleDeletionWhenPayloadIdIsNull() {

        long userId = 1;
        mockPayload.put("action", "deleted");
        repositoriesMock.put("name", "alex");
        repositoriesList.add(repositoriesMock);
        accountMock.put("id", userId);
        installationMock.put("id", installationUserId);
        mockPayload.put("repositories", repositoriesList);
        mockPayload.put("installation", installationMock);
        installationMock.put("account", accountMock);
        when(userServiceMock.getUserById(userId)).thenReturn(null);
        webHookListenerServiceMock.listen(mockPayload);
        verify(userServiceMock).getUserById(userId);
        assertNull(accountDTO.getId());

    }

    @Test
    void testHandleInstallationCreateInstallationRepository() {

        String eventType = "created";
        int userID = 1;
        accountDTO.setId(userId);
        accountDTO.setLogin("alex");
        repositoriesAddedDTO.setId(setId);
        repositoriesAddedDTO.setName("abcRepo");
        repositoriesAddedDTOList.add(repositoriesAddedDTO);
        repositoryIdList.add(repositoriesAddedDTO.getId());
        installationPayloadDTO.setAction("created");
        mockPayload.put("action", "created");
        accountMock.put("id", accountDTO.getId());
        accountMock.put("login", accountDTO.getLogin());
        installationMock.put("account", accountMock);
        installationMock.put("id", installationUserId);
        mockPayload.put("installation", installationMock);
        repositoriesMock.put("id", repositoriesAddedDTO.getId());
        repositoriesMock.put("name", repositoriesAddedDTO.getName());
        repositoriesList.add(repositoriesMock);
        mockPayload.put("repositories", repositoriesList);
        mockPayload.put("installation", installationMock);
        when(userServiceMock.addUser(Mockito.any(AccountDTO.class))).thenReturn(accountDTO);
        when(repositoryServiceMock.addRepository(any(List.class), eq(userID)))
                .thenReturn(repositoriesAddedDTOList);
        when(eventServiceMock.addEvent(eventType, userID, repositoryIdList))
                .thenReturn(installationPayloadDTO);
        webHookListenerServiceMock.listen(mockPayload);
        verify(userServiceMock).addUser(Mockito.any(AccountDTO.class));
        verify(repositoryServiceMock).addRepository(Mockito.any(List.class), Mockito.eq(userID));
        verify(eventServiceMock).addEvent(eventType, userID, repositoryIdList);
        assertNotNull(mockPayload);

    }

    @Test
    void testHandleInstallationAddedRepository() {
        int userID = 1;
        Long userId = 1L;
        accountDTO.setId(userID);
        accountDTO.setLogin("alex");
        repositoriesAddedDTO.setId(setId);
        repositoriesAddedDTO.setName("abcRepo");
        repositoriesAddedDTOList.add(repositoriesAddedDTO);
        repositoriesMock.put("id", repositoriesAddedDTO.getId());
        repositoriesMock.put("name", repositoriesAddedDTO.getName());
        repositoriesList.add(repositoriesMock);
        accountMock.put("id", accountDTO.getId());
        accountMock.put("login", accountDTO.getLogin());
        installationMock.put("id", installationUserId);
        installationMock.put("account", accountMock);
        mockPayload.put("installation", installationMock);
        mockPayload.put("action", "added");
        mockPayload.put("repositories_added", repositoriesList);
        String eventType = "added";
        repositoryIdList.add(repositoriesAddedDTO.getId());
        installationPayloadDTO.setAction(eventType);
        when(userServiceMock.getUserById(userId)).thenReturn(accountDTO);
        when(repositoryServiceMock.addRepository(any(List.class), eq(userID)))
                .thenReturn(repositoriesAddedDTOList);
        when(eventServiceMock.addEvent(eventType, userID, repositoryIdList))
                .thenReturn(installationPayloadDTO);
        webHookListenerServiceMock.listen(mockPayload);
        verify(userServiceMock).getUserById(userId);
        verify(repositoryServiceMock).addRepository(Mockito.any(List.class), Mockito.eq(userID));
        verify(eventServiceMock).addEvent(eventType, userID, repositoryIdList);
        assertNotNull(accountDTO.getId());
    }

    @Test
    void testHandleInstallationWhenInstallationAccountIsNull() {

        mockPayload.put("action", "added");
        installationMock.put("id", installationUserId);
        mockPayload.put("installation", installationMock);
        installationMock.put("account", null);
        repositoriesMock.put("id", repositoryId);
        repositoriesMock.put("name", "alex");
        repositoriesList.add(repositoriesMock);
        mockPayload.put("repositories", repositoriesList);
        webHookListenerServiceMock.listen(mockPayload);
        verify(logger, never()).info(eq("The following payload is null"));
        assertNull(mockPayload.get("account"));

    }

    @Test
    void testHandleInstallationWhenRepositoriesAddedIsNull() {
        mockPayload.put("action", "added");
        installationMock.put("id", installationUserId);
        mockPayload.put("installation", installationMock);
        installationMock.put("account", null);
        mockPayload.put("repositories_added", null);
        webHookListenerServiceMock.listen(mockPayload);
        verify(logger, never()).info(eq("The following payload is null"));
        assertNull(mockPayload.get("repositories_added"));

    }

    @Test
    void testHandleInstallationWhenRepositoriesAddedWithAccountIsNull() {

        mockPayload.put("action", "added");
        installationMock.put("id", installationUserId);
        mockPayload.put("installation", installationMock);
        installationMock.put("account", null);
        mockPayload.put("repositories_added", repositoriesList);
        webHookListenerServiceMock.listen(mockPayload);
        verify(logger, never()).info(eq("The following payload is null"));
        assertNull(mockPayload.get("account"));

    }

    @Test
    void testHandleInstallationRepositoryAddedWhenInstallationIsNull() {

        mockPayload.put("action", "added");
        mockPayload.put("installation", null);
        mockPayload.put("repositories_added", repositoriesList);
        webHookListenerServiceMock.listen(mockPayload);
        verify(logger, never()).info(eq("The following payload is null"));
        assertNull(mockPayload.get("installation"));

    }

    @Test
    void testHandleInstallationWhenRepositoriesAddedWithUserIdIsNull() {
        int userId = 0;
        mockPayload.put("action", "added");
        installationMock.put("id", installationUserId);
        mockPayload.put("installation", installationMock);
        accountMock.put("id", userId);
        accountMock.put("login", "alex");
        installationMock.put("account", accountMock);
        mockPayload.put("repositories_added", repositoriesList);
        webHookListenerServiceMock.listen(mockPayload);
        verify(logger, never()).info(eq("The following payload is null"));
        assertNull(mockPayload.get("userId"));
    }

    @Test
    void testHandleInstallationWhenAccountIsNull() {
        Long userId = 1L;
        mockPayload.put("action", "added");
        installationMock.put("id", installationUserId);
        mockPayload.put("installation", installationMock);
        accountMock.put("id", userId);
        accountMock.put("login", "alex");
        installationMock.put("account", accountMock);
        mockPayload.put("repositories_added", repositoriesList);
        when(userServiceMock.getUserById(userId)).thenReturn(null);
        webHookListenerServiceMock.listen(mockPayload);
        verify(logger, never()).info(eq("The following payload is null"));
        assertNotNull(mockPayload);
    }

    @Test
    void testHandleInstallationRemoveRepository() {
        repositoriesAddedDTO.setId(setId);
        repositoriesAddedDTO.setName("abcRepo");
        repositoriesAddedDTOList.add(repositoriesAddedDTO);
        repositoriesMock.put("id", repositoriesAddedDTO.getId());
        repositoriesMock.put("name", repositoriesAddedDTO.getName());
        repositoriesList.add(repositoriesMock);
        repositoryIdList.add(repositoriesAddedDTO.getId());
        accountMock.put("id", userId);
        accountMock.put("login", "alex");
        installationMock.put("id", installationUserId);
        installationMock.put("account", accountMock);
        mockPayload.put("installation", installationMock);
        mockPayload.put("action", "removed");
        mockPayload.put("repositories_removed", repositoriesList);
        when(repositoryServiceMock.getRepositoryById(repositoryIdList)).thenReturn(repositoriesAddedDTOList);
        when(repositoryServiceMock.deleteRepositoryById(setId)).thenReturn(true);
        webHookListenerServiceMock.listen(mockPayload);
        verify(repositoryServiceMock).getRepositoryById(repositoryIdList);
        verify(repositoryServiceMock).deleteRepositoryById(setId);
        assertNotNull(mockPayload);

    }

    @Test
    void testHandleInstallationRemoveRepositoryWhenInstallationIsNull() {
        mockPayload.put("action", "removed");
        mockPayload.put("installation", null);
        mockPayload.put("repositories_added", repositoriesList);
        webHookListenerServiceMock.listen(mockPayload);
        verify(logger, never()).info(eq("The following payload is null"));
        assertNull(mockPayload.get("installation"));

    }

    @Test
    void testHandleInstallationRemoveRepositoryWhenInstallationAccountIsNull() {
        mockPayload.put("action", "removed");
        installationMock.put("id", installationUserId);
        mockPayload.put("installation", installationMock);
        installationMock.put("account", null);
        mockPayload.put("repositories_added", repositoriesList);
        webHookListenerServiceMock.listen(mockPayload);
        verify(logger, never()).info(eq("The following payload is null"));
        assertNull(mockPayload.get("account"));
    }

    @Test
    void testHandleInstallationRemoveRepositoryWhenNull() {

        mockPayload.put("action", "removed");
        installationMock.put("id", installationUserId);
        mockPayload.put("installation", installationMock);
        accountMock.put("id", userId);
        accountMock.put("login", "alex");
        installationMock.put("account", accountMock);
        mockPayload.put("repositories_added", null);
        webHookListenerServiceMock.listen(mockPayload);
        verify(logger, never()).info(eq("The following payload is null"));
        assertNull(mockPayload.get("repositories_added"));

    }

    @Test
    void testHandleInstallationRemoveRepositoryListIsEmpty() {
        accountMock.put("id", userId);
        accountMock.put("login", "alex");
        installationMock.put("id", installationUserId);
        installationMock.put("account", accountMock);
        mockPayload.put("installation", installationMock);
        mockPayload.put("action", "removed");
        mockPayload.put("repositories_removed", repositoriesList);
        webHookListenerServiceMock.listen(mockPayload);
        verify(logger, never()).info(eq("The following payload is null"));
        assertNotNull(mockPayload);
    }

    @Test
    void testHandleInstallationRemoveRepositoryWhenRepositoryToRemoveIsNull() {
        repositoriesMock.put("id", repositoryId);
        repositoriesMock.put("name", "alexRepo");
        repositoriesList.add(repositoriesMock);
        List<Integer> repositoryIdList = new ArrayList<>();
        repositoryIdList.add(repositoryId);
        accountMock.put("id", userId);
        accountMock.put("login", "alex");
        installationMock.put("id", installationUserId);
        installationMock.put("account", accountMock);
        mockPayload.put("installation", installationMock);
        mockPayload.put("action", "removed");
        mockPayload.put("repositories_removed", repositoriesList);
        when(repositoryServiceMock.getRepositoryById(repositoryIdList)).thenReturn(null);
        webHookListenerServiceMock.listen(mockPayload);
        verify(logger, never()).info(eq("The following payload is null"));
        assertNotNull(mockPayload);
    }

    @Test
    void testHandlePushEvent() {

        String repoName = "abcRepo";
        String login = "alex";
        String email = "alex@gmail.com";
        String commitId = "122345";
        String installationId = "12344";
        String tagUrl = "http://tags";
        boolean fork = false;
        ownerDTO.setId(setId);
        repositoryDTO.setId(repositoryId);
        repositoryDTO.setName(repoName);
        repositoryDTO.setFork(fork);
        repositoryDTO.setOwner(ownerDTO);
        repositoryDTO.setTagsUrl(tagUrl);
        PusherDTO pusherDTO = new PusherDTO(login, email);
        senderDTO.setId(setId);
        senderDTO.setLogin(login);
        installation.setId(Integer.valueOf(installationId));
        headCommitDTO.setId(commitId);
        mockPayload.put("pusher", pusherDTO);
        mockPayload.put("sender", senderDTO);
        mockPayload.put("installation", installation);
        mockPayload.put("repository", repositoryDTO);
        mockPayload.put("head_commit", headCommitDTO);
        userEntity.setUserId(setId);
        userEntity.setUserName(login);
        userEntity.setEmail(email);
        repositoryEntity.setRepositoryId(repositoryId);
        repositoryEntity.setRepositoryName(repoName);
        repositoryEntity.setUserEntity(userEntity);
        repositoryEntity.setFork(fork);
        repositoryEntity.setInstallationId(installationId);
        TagId tag = new TagId(repositoryId, tagUrl);
        tagEntity.setTagId(tag);
        tagEntity.setRepositoryIdInTag(repositoryEntity);
        when(userServiceMock.addUser(setId, login, email)).thenReturn(userEntity);
        when(repositoryServiceMock.addRepository(repositoryId, repoName, setId, installationId))
                .thenReturn(repositoryEntity);
        doNothing().when(tagServiceMock).addTag(tagUrl, repositoryId);
        webHookListenerServiceMock.listen(mockPayload);
        verify(userServiceMock).addUser(setId, login, email);
        verify(repositoryServiceMock).addRepository(repositoryId, repoName, setId, installationId);
        assertNotNull(mockPayload.get("pusher"));
    }

    @Test
    void testHandlePushEventWhenPusherIsNull() {
        mockPayload.put("pusher", null);
        webHookListenerServiceMock.listen(mockPayload);
        assertNull(mockPayload.get("pusher"));

    }

    @Test
    void testHandlePushEventWhenSenderIsNull() {
        String login = "alex";
        String email = "alex@gmail.com";
        PusherDTO pusherDTO = new PusherDTO(login, email);
        mockPayload.put("pusher", pusherDTO);
        mockPayload.put("sender", null);
        webHookListenerServiceMock.listen(mockPayload);
        assertNull(mockPayload.get("sender"));

    }

    @Test
    void testHandlePushEventWhenCommitIsNull() {
        String login = "alex";
        String email = "alex@gmail.com";
        PusherDTO pusherDTO = new PusherDTO(login, email);
        mockPayload.put("pusher", pusherDTO);
        mockPayload.put("head_commit", null);
        webHookListenerServiceMock.listen(mockPayload);
        assertNull(mockPayload.get("head_commit"));

    }

    @Test
    void testHandlePushEventWhenRepositoryIsNull() {
        String login = "alex";
        String email = "alex@gmail.com";
        PusherDTO pusherDTO = new PusherDTO(login, email);
        mockPayload.put("pusher", pusherDTO);
        mockPayload.put("repository", null);
        webHookListenerServiceMock.listen(mockPayload);
        assertNull(mockPayload.get("repository"));

    }

    @Test
    void testHandlePullEvent() {

        ThresholdDTO thresholdDTO = new ThresholdDTO();
        String repoName = "abcRepo";
        String login = "alex";
        String email = "alex@gmail.com";
        String commitId = "122345";
        String installationId = "12344";
        String tagUrl = "http://tags";
        boolean fork = false;
        ownerDTO.setId(setId);
        repositoryDTO.setId(repositoryId);
        repositoryDTO.setName(repoName);
        repositoryDTO.setFork(fork);
        repositoryDTO.setOwner(ownerDTO);
        repositoryDTO.setTagsUrl(tagUrl);
        senderDTO.setId(setId);
        senderDTO.setLogin(login);
        senderDTO.setUrl("11i3iie//aoso");
        Installation installation = new Installation();
        installation.setId(Integer.valueOf(installationId));
        headCommitDTO.setId(commitId);
        mockPayload.put("pull_request", payload);
        mockPayload.put("sender", senderDTO);
        mockPayload.put("installation", installation);
        mockPayload.put("repository", repositoryDTO);
        mockPayload.put("head_commit", headCommitDTO);
        userEntity.setUserId(setId);
        userEntity.setUserName(login);
        userEntity.setEmail(email);
        repositoryEntity.setRepositoryId(repositoryId);
        repositoryEntity.setRepositoryName(repoName);
        repositoryEntity.setUserEntity(userEntity);
        repositoryEntity.setFork(fork);
        repositoryEntity.setInstallationId(installationId);
        TagId tag = new TagId(repositoryId, tagUrl);
        tagEntity.setTagId(tag);
        tagEntity.setRepositoryIdInTag(repositoryEntity);
        when(userServiceMock.addUser(setId, login, senderDTO.getUrl())).thenReturn(userEntity);
        when(repositoryServiceMock.addRepository(repositoryId, repoName, setId, installationId))
                .thenReturn(repositoryEntity);
        when(thresholdService.getThresholdByID(repositoryId)).thenReturn(thresholdDTO);
        doNothing().when(tagServiceMock).addTag(tagUrl, repositoryId);
        webHookListenerServiceMock.listen(mockPayload);
        verify(userServiceMock).addUser(setId, login, senderDTO.getUrl());
        assertNotNull(mockPayload);
    }

    @Test
    void testHandlePullEventWhenSenderIsNull() {
        String repoName = "abcRepo";
        String tagUrl = "http://tags";
        boolean fork = false;
        repositoryDTO.setId(repositoryId);
        repositoryDTO.setName(repoName);
        repositoryDTO.setFork(fork);
        repositoryDTO.setTagsUrl(tagUrl);
        mockPayload.put("pull_request", payload);
        mockPayload.put("repository", repositoryDTO);
        webHookListenerServiceMock.listen(mockPayload);
        assertNull(mockPayload.get("sender"));
    }

    @Test
    void testHandlePullEventWhenRepositoryIsNull() {
        String login = "alex";
        senderDTO.setId(setId);
        senderDTO.setLogin(login);
        senderDTO.setUrl("11i3iie//aoso");
        mockPayload.put("pull_request", payload);
        mockPayload.put("sender", senderDTO);
        webHookListenerServiceMock.listen(mockPayload);
        assertNull(mockPayload.get("repository"));

    }

    @Test
    void testListenIsNull() {
        Map<String, Object> webhookPayload = null;
        webHookListenerServiceMock.listen(webhookPayload);
        verify(logger, never()).info(eq("The following payload is null"));
        assertNull(webhookPayload);
    }

    @Test
    void testListenIFPayloadIsDifferent() {
        mockPayload.put("action", "newAction");
        webHookListenerServiceMock.listen(mockPayload);
        verify(logger, never()).info(eq(" Following webhook payload is not yet supported"));
        assertNotNull(mockPayload);
    }

}
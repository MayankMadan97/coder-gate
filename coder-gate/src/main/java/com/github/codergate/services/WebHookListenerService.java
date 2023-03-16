package com.github.codergate.services;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.codergate.dto.installation.AccountDTO;
import com.github.codergate.dto.installation.InstallationPayloadDTO;
import com.github.codergate.dto.installation.RepositoriesAddedDTO;
import com.github.codergate.dto.pullRequest.Payload;
import com.github.codergate.dto.pullRequest.PullRequest;
import com.github.codergate.dto.pullRequest.PullRequestPayloadDTO;
import com.github.codergate.dto.push.PusherPayloadDTO;
import com.github.codergate.dto.push.RepositoryDTO;
import com.github.codergate.dto.push.SenderDTO;
import com.github.codergate.entities.RepositoryEntity;
import com.github.codergate.entities.UserEntity;
import com.github.codergate.services.utility.WebHookListenerUtil;
import com.github.codergate.utils.Constants;
import com.github.codergate.utils.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.ThreadContext.containsKey;

@Service
public class WebHookListenerService {

    @Autowired
    UserService userService;

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    EventService eventService;

    @Autowired
    TagService repositoryTagService;

    @Autowired
    BranchService repositoryBranchService;

    @Autowired
    PullRequestService pullRequestService;

    @Autowired
    WebHookListenerUtil webHookListenerUtil;


    private static final Logger LOGGER = LoggerFactory.getLogger(WebHookListenerService.class);

    /***
     * user action implementation are called in this method,
     * It checks the event if it is push or installation.
     * @param webhookPayload data
     */
    public void handleIncomingRequest(Map<String, Object> webhookPayload) {
        String action;

        if (webhookPayload.containsKey("pusher"))
            action = Constants.PUSH_EVENT;
        else if(webhookPayload.containsKey("pull_request")){
            action =Constants.PULL_REQUEST_EVENT;
        }
        else
            action = (String) webhookPayload.get(Constants.INSTALLATION_ACTION);



        switch (action) {
//            case Constants.INSTALLATION_REPOSITORY_ADDED:

            case Constants.INSTALLATION_CREATED:
                installationAddRepositoryWebhookListener(webhookPayload);
                break;
            case Constants.INSTALLATION_DELETED:
                handleInstallationDeletion(webhookPayload);
                break;
            case Constants.INSTALLATION_REPOSITORY_REMOVED:
                removeRepository(webhookPayload);
                break;
            case Constants.PUSH_EVENT:
                handlePushEvent(webhookPayload);
                break;
            case Constants.PULL_REQUEST_EVENT:
                handlePullRequestEvent(webhookPayload);
                break;
            default:
                LOGGER.warn("handleIncomingRequest : Following webhook payload is not yet supported {}", webhookPayload);
                break;
        }
    }


    /***
     * When a user installs or adds this method will be implemented
     * Create and Add action is implemented, which sets user, repositoryRepository and event information.
     * @param webhookPayload data
     */
    private void installationAddRepositoryWebhookListener(Map<String, Object> webhookPayload) {

        InstallationPayloadDTO payload = Mapper.getInstance().convertValue(webhookPayload,
                InstallationPayloadDTO.class);

        if (payload != null && payload.getInstallation() != null && payload.getInstallation().getAccount() != null
                && payload.getRepositories() != null && payload.getAction() != null) {

            handleInstallationCreation(payload);
            LOGGER.info("installationAddRepositoryWebhookListener : user has installed the application");



        } else if (payload != null && payload.getInstallation() != null && payload.getInstallation().getAccount() != null
                && payload.getRepositoriesAdded() != null && payload.getAction() != null) {

            addRepository(payload);
            LOGGER.info("installationAddRepositoryWebhookListener : user has added repositories");

        }
    }


    private void addRepository(InstallationPayloadDTO payload) {
        long userId = payload.getInstallation().getAccount().getId();
        if (userId != 0) {
            AccountDTO user = userService.getUserById(userId);
            if (user != null) {
                List<RepositoriesAddedDTO> repositoryList = repositoryService.addRepository(payload.getRepositoriesAdded(), user.getId());
                List<Integer> repositoryIdList = repositoryList.stream()
                        .map(RepositoriesAddedDTO::getId)
                        .collect(Collectors.toList());
                eventService.addEvent(payload.getAction(), user.getId(), repositoryIdList);
            }
        }
    }

    private void handleInstallationCreation(InstallationPayloadDTO payload) {
        List<RepositoriesAddedDTO> repositoriesAddedDTOList = Mapper.getInstance().convertValue(payload.getRepositories(), new TypeReference<>() {});

        // adding user
        AccountDTO user = userService.addUser(payload.getInstallation().getAccount());

        // creating repositoryRepository
        repositoriesAddedDTOList = repositoryService.addRepository(repositoriesAddedDTOList, user.getId());

        // getting repositoryRepository id
        List<Integer> repositoryIdList = repositoriesAddedDTOList.stream()
                .map(RepositoriesAddedDTO::getId)
                .collect(Collectors.toList());

        // adding events
        eventService.addEvent(payload.getAction(), user.getId(), repositoryIdList);
    }

    /***
     * Note we are using List<RepositoriesAdded> here, as the attributes of RepositoriesRemoved is sames as added, we are
     * using one dto class.
     * Removing repositories as per user request
     * @param webhookPayload data
     */
    private void removeRepository(Map<String, Object> webhookPayload) {

        InstallationPayloadDTO payload = Mapper.getInstance().convertValue(webhookPayload, InstallationPayloadDTO.class);

        if (payload != null && payload.getInstallation() != null && payload.getInstallation().getAccount() != null
                && payload.getRepositoriesRemoved() != null && payload.getAction() != null) {
            List<RepositoriesAddedDTO> removedRepositories = Mapper.getInstance().convertValue(payload.getRepositoriesRemoved(), new TypeReference<>() {});

            List<Integer> removedRepositoryIds = removedRepositories.stream().map(RepositoriesAddedDTO::getId).collect(Collectors.toList());

            if (!removedRepositoryIds.isEmpty()) {
                List<RepositoriesAddedDTO> repositoriesToRemove = repositoryService.getRepositoryById(removedRepositoryIds);
                if (repositoriesToRemove != null) {
                    for (RepositoriesAddedDTO repositoryId : repositoriesToRemove) {
                        repositoryService.deleteRepositoryById(repositoryId.getId());
                    }
                    LOGGER.info("removeRepository : user has removed repositories");
                }
            }
        }
    }

    /***
     * when user deletes the application, this implementation will be executed, which deletes all information about user
     * @param webhookPayload data
     */
    private void handleInstallationDeletion(Map<String, Object> webhookPayload) {

        InstallationPayloadDTO payload = Mapper.getInstance().convertValue(webhookPayload, InstallationPayloadDTO.class);

        if (payload != null && payload.getInstallation() != null && payload.getInstallation().getAccount() != null
                && payload.getRepositories() != null && payload.getAction() != null) {

            // Extract the user ID from the payload
            long userId = payload.getInstallation().getAccount().getId();
            // Check if the user exists in the database
            if (userService.getUserById(userId) != null) {
                userService.deleteUserByID(userId);
                LOGGER.info("removeRepository : user has deleted the application");

            } else {
                LOGGER.error("handleInstallationDeletion : User doesn't exist");
            }
        }
    }

    private void handlePushEvent(Map<String, Object> webhookPayload) {
        PusherPayloadDTO pushEventPayload = Mapper.getInstance().convertValue(webhookPayload, PusherPayloadDTO.class);

        if (pushEventPayload != null && pushEventPayload.getPusher() != null && pushEventPayload.getSender() != null
                && pushEventPayload.getHeadCommit() != null && pushEventPayload.getRepository() != null) {

            UserEntity userEntity = userService.addUser(pushEventPayload.getSender().getId(),pushEventPayload.getSender().getLogin(), pushEventPayload.getPusher().getEmail());
            RepositoryEntity repositoryEntity = repositoryService.addRepository(pushEventPayload.getRepository().getId(),pushEventPayload.getRepository().getName(),pushEventPayload.getRepository().getFork(), pushEventPayload.getRepository().getOwner().getId());
            repositoryTagService.addTag(pushEventPayload.getRepository().getTagsUrl(),pushEventPayload.getRepository().getId());
            repositoryBranchService.addBranch(pushEventPayload.getRepository().getBranchesUrl(),pushEventPayload.getRepository().getId());
            eventService.addEvent(pushEventPayload.getHeadCommit(), (int)userEntity.getUserId(), repositoryEntity.getRepositoryId());
            LOGGER.info("removeRepository : user has initialized a push event");

        }
    }

    private void handlePullRequestEvent(Map<String, Object> webhookPayload) {
        Payload pullRequestPayload = Mapper.getInstance().convertValue(webhookPayload, Payload.class);
        if (pullRequestPayload != null) {
            UserEntity userEntity = userService.addUser(pullRequestPayload.getSender().getId(), pullRequestPayload.getSender().getLogin(), pullRequestPayload.getSender().getUrl());
            RepositoryEntity repositoryEntity = repositoryService.addRepository(pullRequestPayload.getRepository().getId(), pullRequestPayload.getRepository().getName(), pullRequestPayload.getRepository().getFork(), pullRequestPayload.getRepository().getOwner().getId());
            repositoryTagService.addTag(pullRequestPayload.getRepository().getTagsUrl(),pullRequestPayload.getRepository().getId());
            repositoryBranchService.addBranch(pullRequestPayload.getRepository().getBranchesUrl(),pullRequestPayload.getRepository().getId());
            List<Integer> repositoryEntitiesIds = new ArrayList<>();
            repositoryEntitiesIds.add(repositoryEntity.getRepositoryId());
            eventService.addEvent("Pull Request", (int)userEntity.getUserId(), repositoryEntitiesIds);
            Boolean pullRequestCheck = pullRequestService.pullRequestCheck(pullRequestPayload.getRepository().getId());
            if(!pullRequestCheck){
                String token = "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";
                Boolean rejectPullRequest = webHookListenerUtil.rejectPullRequest(
                        pullRequestPayload.getRepository().getOwner().getLogin(),
                        pullRequestPayload.getRepository().getName(),
                        pullRequestPayload.getPullRequest().getNumber(),token);
            }
        }
    }
}






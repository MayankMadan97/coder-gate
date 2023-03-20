package com.github.codergate.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.codergate.dto.installation.AccountDTO;
import com.github.codergate.dto.installation.InstallationPayloadDTO;
import com.github.codergate.dto.installation.RepositoriesAddedDTO;
import com.github.codergate.dto.installation.RepositoriesDTO;
import com.github.codergate.dto.pullRequest.PullRequestPayloadDTO;
import com.github.codergate.dto.push.PusherPayloadDTO;
import com.github.codergate.dto.threshold.ThresholdDTO;
import com.github.codergate.entities.EventEntity;
import com.github.codergate.entities.RepositoryEntity;
import com.github.codergate.entities.UserEntity;
import com.github.codergate.utils.Constants;
import com.github.codergate.utils.JwtUtils;
import com.github.codergate.utils.Mapper;
import com.github.codergate.utils.RestClient;

@Service
public class WebHookListenerService {

    private static final String REQUESTED = "requested";
    private static final String ACTION = "action";
    private static final String PUSHER = "pusher";

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
    private RestClient restClient;

    @Autowired
    AnalysisService analysisService;

    @Autowired
    ThresholdService thresholdService;

    private static final Logger LOGGER = LoggerFactory.getLogger(WebHookListenerService.class);

    /***
     * user action implementation are called in this method,
     * It checks the event if it is push or installation.
     * 
     * @param webhookPayload data
     */
    public void handleIncomingRequest(Map<String, Object> webhookPayload) {
        String action;

        if (webhookPayload.containsKey(PUSHER))
            action = Constants.PUSH_EVENT;
        else if (webhookPayload.get(ACTION).equals(REQUESTED)) {
            action = Constants.PULL_REQUEST_EVENT;
        } else
            action = (String) webhookPayload.get(Constants.INSTALLATION_ACTION);
        switch (action) {
            case Constants.INSTALLATION_REPOSITORY_ADDED:
            case Constants.INSTALLATION_CREATED:
                installationWebhookListener(webhookPayload);
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
                LOGGER.warn("handleIncomingRequest : Following webhook payload is not yet supported {}",
                        webhookPayload);
                break;
        }
    }

    /***
     * When a user installs or adds this method will be implemented
     * Create and Add action is implemented, which sets user, repositoryRepository
     * and event information.
     * 
     * @param webhookPayload data
     */
    private void installationAddRepositoryWebhookListener(Map<String, Object> webhookPayload) {

        InstallationPayloadDTO payload = Mapper.getInstance().convertValue(webhookPayload,
                InstallationPayloadDTO.class);

        if (payload != null && payload.getInstallation() != null && payload.getInstallation().getAccount() != null
                && payload.getRepositories() != null && payload.getAction() != null) {

            handleInstallationCreation(payload);
            LOGGER.info("installationAddRepositoryWebhookListener : user has installed the application");

        } else if (payload != null && payload.getInstallation() != null
                && payload.getInstallation().getAccount() != null
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
                List<RepositoriesAddedDTO> repositoryList = repositoryService
                        .addRepository(payload.getRepositoriesAdded(), user.getId());
                List<Integer> repositoryIdList = repositoryList.stream()
                        .map(RepositoriesAddedDTO::getId)
                        .collect(Collectors.toList());
                eventService.addEvent(payload.getAction(), user.getId(), repositoryIdList);
            }
        }
    }

    private void handleInstallationCreation(InstallationPayloadDTO payload) {
        List<RepositoriesAddedDTO> repositoriesAddedDTOList = Mapper.getInstance()
                .convertValue(payload.getRepositories(), new TypeReference<>() {
                });

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
     * Note we are using List<RepositoriesAdded> here, as the attributes of
     * RepositoriesRemoved is sames as added, we are
     * using one dto class.
     * Removing repositories as per user request
     * 
     * @param webhookPayload data
     */
    private void removeRepository(Map<String, Object> webhookPayload) {

        InstallationPayloadDTO payload = Mapper.getInstance().convertValue(webhookPayload,
                InstallationPayloadDTO.class);

        if (payload != null && payload.getInstallation() != null && payload.getInstallation().getAccount() != null
                && payload.getRepositoriesRemoved() != null && payload.getAction() != null) {
            List<RepositoriesAddedDTO> removedRepositories = Mapper.getInstance()
                    .convertValue(payload.getRepositoriesRemoved(), new TypeReference<>() {
                    });

            List<Integer> removedRepositoryIds = removedRepositories.stream().map(RepositoriesAddedDTO::getId)
                    .collect(Collectors.toList());

            if (!removedRepositoryIds.isEmpty()) {
                List<RepositoriesAddedDTO> repositoriesToRemove = repositoryService
                        .getRepositoryById(removedRepositoryIds);
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
     * when user deletes the application, this implementation will be executed,
     * which deletes all information about user
     * 
     * @param webhookPayload data
     */
    private void handleInstallationDeletion(Map<String, Object> webhookPayload) {

        InstallationPayloadDTO payload = Mapper.getInstance().convertValue(webhookPayload,
                InstallationPayloadDTO.class);

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

            UserEntity userEntity = userService.addUser(pushEventPayload.getSender().getId(),
                    pushEventPayload.getSender().getLogin(), pushEventPayload.getPusher().getEmail());
            RepositoryEntity repositoryEntity = repositoryService.addRepository(
                    pushEventPayload.getRepository().getId(), pushEventPayload.getRepository().getName(),
                    pushEventPayload.getRepository().getFork(), pushEventPayload.getRepository().getOwner().getId());
            repositoryTagService.addTag(pushEventPayload.getRepository());
            repositoryBranchService.addBranch(pushEventPayload.getRepository());
            EventEntity eventEntity = eventService.addEvent(pushEventPayload.getHeadCommit(),
                    (int) userEntity.getUserId(), repositoryEntity.getRepositoryId());
            LOGGER.info("handlePushEvent: user has initialized a push event");

            // Everytime a push event happens, we run analysis. So we call it here. I have
            // hard coded the values for DTO.
            // AnalysisDTO analysisDTO = new AnalysisDTO("Code Smell",
            // repositoryEntity.getRepositoryId(), 30, 13, 23, 90, 75, 3, 80, 12, 4, 17, 43,
            // 32, 57, 54, 21, 29, 11);
            // analysisService.addAnalysis(analysisDTO, eventEntity.getEventId(),
            // repositoryEntity.getRepositoryId());
            // LOGGER.info("handlePushEvent : Analysis has been stored database");

            // I have called Threshold service here because IDK where else to call it
            ThresholdDTO thresholdDTO = new ThresholdDTO(1, 1, 1, 90, 75, 3, 80, 12, 4, 17, 43, 32, 57, 54, 21, 29, 11);
            thresholdService.addThreshold(thresholdDTO, repositoryEntity.getRepositoryId());
            LOGGER.info("handlePushEvent : Threshold has been stored in database");
        }
    }

    private void handlePullRequestEvent(Map<String, Object> webhookPayload) {
        PullRequestPayloadDTO pullRequestPayloadDTO = Mapper.getInstance().convertValue(webhookPayload,
                PullRequestPayloadDTO.class);

        if (pullRequestPayloadDTO != null && pullRequestPayloadDTO.getPayload() != null) {

            UserEntity userEntity = userService.addUser(pullRequestPayloadDTO.getPayload().getSender().getId(),
                    pullRequestPayloadDTO.getPayload().getSender().getLogin(),
                    pullRequestPayloadDTO.getPayload().getSender().getUrl());
            RepositoryEntity repositoryEntity = repositoryService.addRepository(
                    pullRequestPayloadDTO.getPayload().getRepository().getId(),
                    pullRequestPayloadDTO.getPayload().getRepository().getName(),
                    pullRequestPayloadDTO.getPayload().getRepository().getFork(),
                    pullRequestPayloadDTO.getPayload().getRepository().getOwner().getId());
            // repositoryTagService.addTag(pullRequestPayloadDTO.getPayload().getRepository().getId());
            // repositoryBranchService.addBranch(pullRequestPayloadDTO.getPayload().getRepository());
            List<Integer> repositoryEntitiesIds = new ArrayList<>();
            repositoryEntitiesIds.add(repositoryEntity.getRepositoryId());
            eventService.addEvent(pullRequestPayloadDTO.getEvent(), (int) userEntity.getUserId(),
                    repositoryEntitiesIds);

        }
    }

    private void installationWebhookListener(Map<String, Object> webhookPayload) {
        InstallationPayloadDTO payload = Mapper.getInstance().convertValue(webhookPayload,
                InstallationPayloadDTO.class);
        LOGGER.debug("webHookListener : Installation payload {}", payload);
        try {
            Map<String, Object> bodyParamForPost = new HashMap<>();
            Map<String, Object> comitter = new HashMap<>();
            bodyParamForPost.put("message", "Code scanning configured");
            bodyParamForPost.put("content", Files.readAllBytes(Path.of(
                    System.getProperty("user.dir")
                            + "/coder-gate/src/main/resources/application-designite.yml")));
            comitter.put("name", payload.getInstallation().getAppSlug() + "[bot]");
            comitter.put("email", payload.getInstallation().getAccount().getId()
                    + payload.getInstallation().getAppSlug() + "[bot]@users.noreply.github.com");
            bodyParamForPost.put("committer", comitter);
            List<RepositoriesDTO> repositoriesToUpdate = payload.getRepositoriesAdded() != null ? Mapper.getInstance()
                    .convertValue(payload.getRepositoriesAdded(), new TypeReference<List<RepositoriesDTO>>() {
                    }) : payload.getRepositories();
            if (repositoriesToUpdate != null && !repositoriesToUpdate.isEmpty()) {
                repositoriesToUpdate.stream().filter(Objects::nonNull).forEach(repo -> {
                    if (payload.getInstallation() != null && payload.getInstallation().getId() != null) {
                        restClient.invokeForPost(
                                "https://api.github.com/repos/" + repo.getFullName()
                                        + "/contents/.github/workflows/application-designite.yml",
                                bodyParamForPost,
                                JwtUtils.getGithubSpecificHeaders(), payload.getInstallation().getId().toString());
                    }
                });
            }
        } catch (IOException e) {
            LOGGER.error("installationWebhookListener : Failed to read github action file");
        }
    }

}

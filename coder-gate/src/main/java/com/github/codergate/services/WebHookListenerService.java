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
import com.github.codergate.dto.installation.Installation;
import com.github.codergate.dto.installation.InstallationPayloadDTO;
import com.github.codergate.dto.installation.RepositoriesAddedDTO;
import com.github.codergate.dto.installation.RepositoriesDTO;
import com.github.codergate.dto.pullRequest.Payload;
import com.github.codergate.dto.pullRequest.Repository;
import com.github.codergate.dto.pullRequest.Sender;
import com.github.codergate.dto.push.PusherPayloadDTO;
import com.github.codergate.entities.RepositoryEntity;
import com.github.codergate.entities.UserEntity;
import com.github.codergate.services.utility.WebHookListenerUtil;
import com.github.codergate.utils.Constants;
import com.github.codergate.utils.JwtUtils;
import com.github.codergate.utils.Mapper;
import com.github.codergate.utils.RestClient;

/*
 * service handles subscribed webhooks from GitHub
 */
@Service
public class WebHookListenerService {

    // Depednencies

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

    @Autowired
    AnalysisService analysisService;

    @Autowired
    ThresholdService thresholdService;

    @Autowired
    private RestClient restClient;

    // class level Constants
    private static final String PULL_REQUEST = "pull_request";
    private static final String PUSHER = "pusher";

    private static final Logger LOGGER = LoggerFactory.getLogger(WebHookListenerService.class);

    /***
     * user action implementation are called in this method,
     * It checks the event if it is push or installation.
     * 
     * @param webhookPayload data
     */
    public void listen(Map<String, Object> webhookPayload) {
        LOGGER.debug("listen :: Entering the method with payload {}", webhookPayload);
        if (webhookPayload.containsKey(PUSHER)) {
            handlePushEvent(webhookPayload);
        } else if (webhookPayload.containsKey(PULL_REQUEST)) {
            handlePullRequestEvent(webhookPayload);
        } else if (webhookPayload.get(Constants.ACTION) != null) {
            switch (webhookPayload.get(Constants.ACTION).toString()) {
                case Constants.ADDITION:
                case Constants.CREATION:
                    handleNewRepositoryInstallation(webhookPayload);
                    configureCodeScanning(webhookPayload);
                    break;
                case Constants.DELETION:
                    handleInstallationDeletion(webhookPayload);
                    break;
                case Constants.REMOVAL:
                    removeRepository(webhookPayload);
                    break;
                default:
                    LOGGER.warn("handleIncomingRequest : Following webhook payload is not yet supported {}",
                            webhookPayload);
            }
        }
        LOGGER.debug("listen :: Exiting the method");
    }

    /***
     * When a user installs or adds this method will be implemented
     * Create and Add action is implemented, which sets user, repositoryRepository
     * and event information.
     * 
     * @param webhookPayload data
     */
    private void handleNewRepositoryInstallation(Map<String, Object> webhookPayload) {
        if (webhookPayload != null) {
            InstallationPayloadDTO payload = Mapper.getInstance().convertValue(webhookPayload,
                    InstallationPayloadDTO.class);
            if (payload != null && payload.getInstallation() != null) {
                if (payload.getRepositories() != null) {
                    handleRepositoryCreation(payload.getRepositories(),
                            payload.getInstallation(), payload.getAction());
                    LOGGER.info("installationAddRepositoryWebhookListener : user has installed the application");
                } else if (payload.getRepositoriesAdded() != null) {
                    handleRepositoryAddition(payload.getRepositoriesAdded(),
                            payload.getInstallation(), payload.getAction());
                    LOGGER.info("installationAddRepositoryWebhookListener : user has added repositories");
                }
            }
        }
    }

    /**
     * @param payload
     */
    private void handleRepositoryAddition(List<RepositoriesAddedDTO> repositories, Installation installation,
            String action) {
        if (installation != null && installation.getAccount() != null) {
            long userId = installation.getAccount().getId();
            if (userId != 0L) {
                AccountDTO account = userService.getUserById(userId);
                if (account != null) {
                    List<RepositoriesAddedDTO> repositoryList = repositoryService
                            .addRepository(repositories, account.getId());
                    List<Integer> repositoryIdList = repositoryList.stream()
                            .map(RepositoriesAddedDTO::getId)
                            .collect(Collectors.toList());
                    eventService.addEvent(action, account.getId(), repositoryIdList);
                }
            }
        }
    }

    /**
     * @param payload
     */
    private void handleRepositoryCreation(List<RepositoriesDTO> repositories, Installation installation,
            String action) {
        if (installation != null && installation.getAccount() != null) {
            List<RepositoriesAddedDTO> reposAdded = Mapper.getInstance()
                    .convertValue(repositories, new TypeReference<>() {
                    });
            // adding user
            AccountDTO account = userService.addUser(installation.getAccount());
            // creating repositoryRepository
            reposAdded = repositoryService.addRepository(reposAdded, account.getId());
            // getting repositoryRepository id
            List<Integer> reposIdList = reposAdded.stream()
                    .map(RepositoriesAddedDTO::getId)
                    .collect(Collectors.toList());
            // adding events
            eventService.addEvent(action, account.getId(), reposIdList);
        }
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

    /**
     * @param webhookPayload
     */
    private void handlePushEvent(Map<String, Object> webhookPayload) {
        PusherPayloadDTO pushEventPayload = Mapper.getInstance().convertValue(webhookPayload, PusherPayloadDTO.class);
        if (pushEventPayload != null && pushEventPayload.getPusher() != null && pushEventPayload.getSender() != null
                && pushEventPayload.getHeadCommit() != null && pushEventPayload.getRepository() != null) {

            UserEntity userEntity = userService.addUser(pushEventPayload.getSender().getId(),
                    pushEventPayload.getSender().getLogin(), pushEventPayload.getPusher().getEmail());
            RepositoryEntity repositoryEntity = repositoryService.addRepository(
                    pushEventPayload.getRepository().getId(), pushEventPayload.getRepository().getName(),
                    pushEventPayload.getRepository().getFork(), pushEventPayload.getRepository().getOwner().getId(),
                    pushEventPayload.getInstallation().getId().toString());
            repositoryTagService.addTag(pushEventPayload.getRepository().getTagsUrl(),
                    pushEventPayload.getRepository().getId());
            repositoryBranchService.addBranch(pushEventPayload.getRepository().getBranchesUrl(),
                    pushEventPayload.getRepository().getId());
            eventService.addEvent(pushEventPayload.getHeadCommit(), (int) userEntity.getUserId(),
                    repositoryEntity.getRepositoryId());
            LOGGER.info("removeRepository : user has initialized a push event");
            eventService.addEvent(pushEventPayload.getHeadCommit(), (int) userEntity.getUserId(),
                    repositoryEntity.getRepositoryId());
            LOGGER.info("handlePushEvent : Threshold has been stored in database");
        }
    }

    /**
     * @param webhookPayload
     */
    private void handlePullRequestEvent(Map<String, Object> webhookPayload) {
        Payload payload = Mapper.getInstance().convertValue(webhookPayload, Payload.class);
        if (payload != null) {
            Repository repo = payload.getRepository();
            Sender sender = payload.getSender();
            if (repo != null && sender != null) {
                List<Integer> repositoryEntitiesIds = new ArrayList<>();
                UserEntity userEntity = userService.addUser(sender.getId(), sender.getLogin(), sender.getUrl());
                RepositoryEntity repositoryEntity = repositoryService.addRepository(
                        repo.getId(), repo.getName(), repo.getFork(), repo.getOwner().getId(),
                        payload.getInstallation().getId().toString());
                repositoryTagService.addTag(repo.getTagsUrl(), repo.getId());
                repositoryBranchService.addBranch(repo.getBranchesUrl(), repo.getId());
                repositoryEntitiesIds.add(repositoryEntity.getRepositoryId());
                eventService.addEvent("Pull Request", (int) userEntity.getUserId(), repositoryEntitiesIds);
                boolean pullRequestCheck = pullRequestService.pullRequestCheck(repo.getId());
                if (!pullRequestCheck) {
                    webHookListenerUtil.rejectPullRequest(
                            repo.getOwner().getLogin(), repo.getName(),
                            payload.getPullRequest().getNumber(),
                            payload.getInstallation().getId().toString());
                }
            }
        }
    }

    /**
     * @param webhookPayload
     */
    private void configureCodeScanning(Map<String, Object> webhookPayload) {
        LOGGER.debug("webHookListener : Installation payload {}", webhookPayload);
        Map<String, Object> bodyParamForPost = new HashMap<>();
        Map<String, Object> comitter = new HashMap<>();
        List<RepositoriesDTO> repositoriesToUpdate = null;
        if (webhookPayload != null) {
            InstallationPayloadDTO payload = Mapper.getInstance().convertValue(webhookPayload,
                    InstallationPayloadDTO.class);
            if (payload.getInstallation() != null && payload.getInstallation().getId() != null) {
                try {
                    Path workflowFilePath = Path.of(
                            System.getProperty(Constants.USER_DIR)
                                    + "/coder-gate/src/main/resources/application-designite.yml");
                    bodyParamForPost.put("message", "Code scanning configured");
                    bodyParamForPost.put("content", Files.readAllBytes(workflowFilePath));
                    comitter.put("name", payload.getInstallation().getAppSlug() + "[bot]");
                    comitter.put("email", payload.getInstallation().getAccount().getId()
                            + payload.getInstallation().getAppSlug() + "[bot]@users.noreply.github.com");
                    bodyParamForPost.put("committer", comitter);
                    if (payload.getRepositories() != null) {
                        repositoriesToUpdate = payload.getRepositories();
                    } else if (payload.getRepositoriesAdded() != null) {
                        repositoriesToUpdate = Mapper.getInstance()
                                .convertValue(payload.getRepositoriesAdded(),
                                        new TypeReference<List<RepositoriesDTO>>() {
                                        });
                    }
                    if (repositoriesToUpdate != null && !repositoriesToUpdate.isEmpty()) {
                        repositoriesToUpdate.stream().filter(Objects::nonNull).forEach(repo -> {
                            String apiURL = Constants.BASE_API + "/repos/" + repo.getFullName()
                                    + "/contents/.github/workflows/application-designite.yml";
                            restClient.invokeForPut(apiURL, bodyParamForPost, JwtUtils.getGithubSpecificHeaders(),
                                    payload.getInstallation().getId().toString());
                        });
                    }
                } catch (IOException e) {
                    LOGGER.error("installationWebhookListener : Failed to read github action file");
                }
            }
        }
    }

}

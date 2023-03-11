package com.github.codergate.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.util.stream.Collectors;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.codergate.dto.installation.Account;
import com.github.codergate.dto.push.PusherPayloadDTO;
import com.github.codergate.dto.push.RepositoryDTO;
import com.github.codergate.dto.push.SenderDTO;
import com.github.codergate.services.TagService;
import com.github.codergate.dto.installation.RepositoriesAdded;
import com.github.codergate.dto.installation.RepositoriesRemoved;
import org.bouncycastle.util.Integers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.codergate.dto.installation.InstallationPayload;
import com.github.codergate.utils.JwtUtils;
import com.github.codergate.utils.Mapper;
import com.github.codergate.utils.RestClient;

@Service
public class WebHookListenerService {

    @Autowired
    private RestClient restClient;

    @Autowired
    UserService userService;

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    EventService eventService;

    @Autowired
    TagService tagService;

    @Autowired
    BranchService branchService;
    
    private static final String INSTALLATION_CREATED = "created";
    private static final String INSTALLATION_ACTION = "action";
    private static final String INSTALLATION_DELETED = "deleted";
    private static final String INSTALLATION_REPOSITORY_REMOVED = "removed";
    private static final String INSTALLATION_REPOSITORY_ADDED = "added";
    private static final String PUSH_EVENT = "push";
    private static final Logger LOGGER = LoggerFactory.getLogger(WebHookListenerService.class);


    /***
     * user action implementation are called in this method
     * @param webhookPayload data
     */
    public void listen(Map<String, Object> webhookPayload) {
        
        String installationAction = (String) webhookPayload.get(INSTALLATION_ACTION);
        switch (installationAction) {
            case INSTALLATION_REPOSITORY_ADDED:
            case INSTALLATION_CREATED:
                installationAddRepositoryWebhookListener(webhookPayload);
                break;
            case INSTALLATION_DELETED:
                installationDeleteWebhookListener(webhookPayload);
                break;
            case INSTALLATION_REPOSITORY_REMOVED:
                installationRemoveRepositoryWebhookListener(webhookPayload);
                break;
            case PUSH_EVENT:
//                implementation of push
                break;
            default:
                LOGGER.warn("webHookListener : Following webhook payload is not yet supported {}", webhookPayload);
                break;
        }
    }

    private void installationWebhookListener(Map<String, Object> webhookPayload) {
        InstallationPayload payload = Mapper.getInstance().convertValue(webhookPayload,
                InstallationPayload.class);
        LOGGER.debug("webHookListener : Installation payload {}", payload);
        try {
            Map<String, Object> bodyParamForPost = new HashMap<>();
            Map<String, Object> comitter = new HashMap<>();
            bodyParamForPost.put("message", "Code scanning configured");
            bodyParamForPost.put("content", Files.readAllBytes(Path.of(
                    System.getProperty("user.dir")
                            + "/coder-gate/src/main/resources/codeql.yml")));
            comitter.put("name", "codergate[bot]");
            comitter.put("email", "293667+codergate[bot]@users.noreply.github.com");
            bodyParamForPost.put("committer", comitter);
            if (payload.getInstallation() != null && payload.getInstallation().getId() != null) {
                restClient.invokeForGet("https://api.github.com/repos/MayankMadan97/CoderGate/issues", null,
                        payload.getInstallation().getId().toString());
                restClient.invokeForPost(
                        "https://api.github.com/repos/MayankMadan97/CoderGate/contents/.github/workflows/codeql.yml",
                        bodyParamForPost,
                        JwtUtils.getGithubSpecificHeaders(), payload.getInstallation().getId().toString());
            }
        } catch (IOException e) {
            LOGGER.error("installationWebhookListener : Failed to read github action file");
        }
    }


    /***
     * When a user installs or adds this method will be implemented
     * Create and Add action is implemented, which sets user, repository and event information.
     * @param webhookPayload data
     */
    private void installationAddRepositoryWebhookListener(Map<String, Object> webhookPayload) {
        InstallationPayload payload = Mapper.getInstance().convertValue(webhookPayload,
                InstallationPayload.class);

        if (payload != null && payload.getInstallation() != null && payload.getInstallation().getAccount() != null
                && payload.getRepositories() != null && payload.getAction()!=null) {

            List<RepositoriesAdded> repositoriesAddedList= Mapper.getInstance().convertValue(payload.getRepositories(), new TypeReference<List<RepositoriesAdded>>() {
            });

            // adding user
            Account user = userService.addUser(payload.getInstallation().getAccount());

            // creating repository
            repositoriesAddedList = repositoryService.addRepository(repositoriesAddedList,user.getId());

            // getting repository id
            List<Integer> repositoryIdList = repositoriesAddedList.stream()
                    .map(x -> x.getId())
                    .collect(Collectors.toList());

            // adding events
            eventService.addEvent(payload.getAction(), user.getId(), repositoryIdList);

        }else if(payload != null && payload.getInstallation() != null && payload.getInstallation().getAccount() != null
                && payload.getRepositoriesAdded() != null && payload.getAction()!=null)
        {

            long userId = payload.getInstallation().getAccount().getId();
            if(userId!=0){
                Account user = userService.getUser(userId);
                if(user!=null)
                {
                    List<RepositoriesAdded> repositoryList = repositoryService.addRepository(payload.getRepositoriesAdded(), user.getId());
                    List<Integer> repositoryIdList = repositoryList.stream()
                            .map(x -> x.getId())
                            .collect(Collectors.toList());
                    eventService.addEvent(payload.getAction(), user.getId(), repositoryIdList);
                }
            }

        }
    }

    /***
     * Removing repositories as per user request
     * @param webhookPayload data
     */
    public void installationRemoveRepositoryWebhookListener(Map<String, Object> webhookPayload) {
        InstallationPayload payload = Mapper.getInstance().convertValue(webhookPayload,
                InstallationPayload.class);
        if (payload != null && payload.getInstallation() != null && payload.getInstallation().getAccount() != null
                && payload.getRepositoriesRemoved() != null && payload.getAction() != null) {

            List<RepositoriesAdded> repositoriesAddedList = Mapper.getInstance().convertValue(payload.getRepositoriesRemoved(), new TypeReference<List<RepositoriesAdded>>() {
            });

            List<Integer> repositoryIds = repositoriesAddedList.stream().map(i->{i.getId();return i.getId();}).collect(Collectors.toList());
            if(repositoryIds!=null)
            {
                List<RepositoriesAdded> repositoryIdPresentInDb= repositoryService.getRepository(repositoryIds);
                if(repositoryIdPresentInDb!=null)
                {
                    for(RepositoriesAdded id : repositoryIdPresentInDb)
                    {
                        repositoryService.deleteRepository(id.getId());
                    }
                }
            }
        }
    }


    /***
     * when user deletes the application, this implementation will be executed, which deletes all information about user
     * @param webhookPayload data
     */
    public void installationDeleteWebhookListener(Map<String, Object> webhookPayload) {
        InstallationPayload payload = Mapper.getInstance().convertValue(webhookPayload,
                InstallationPayload.class);
        if (payload != null && payload.getInstallation() != null && payload.getInstallation().getAccount() != null
                && payload.getRepositories() != null && payload.getAction()!=null) {

            long userId = payload.getInstallation().getAccount().getId();
            if(userService.getUser(userId)!=null)
            {
                userService.deleteUserByID(userId);

            }else
            {
                LOGGER.error("installationWebhookListener : User doesn't exist");
            }
        }

    }

    private void pushEventWebhookListener(Map<String, Object> webhookPayload) {
        PusherPayloadDTO payload = Mapper.getInstance().convertValue(webhookPayload,
                PusherPayloadDTO.class);
        if (payload != null && payload.getPusher() != null && payload.getSender() != null
                && payload.getHeadCommit() != null && payload.getRepository() != null) {
            SenderDTO user = userService.addUser(payload.getSender(), payload.getPusher().getEmail());
            RepositoryDTO repository = repositoryService.addRepository(payload.getRepository());
            tagService.createTag(payload.getRepository());
            branchService.createBranch(payload.getRepository());
            eventService.addEvent(payload.getHeadCommit(), user.getId(), repository.getId());
        }
    }



}






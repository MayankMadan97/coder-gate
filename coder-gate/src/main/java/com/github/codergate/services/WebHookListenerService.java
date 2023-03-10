package com.github.codergate.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.github.codergate.dto.installation.Account;
import com.github.codergate.dto.push.PusherPayloadDTO;
import com.github.codergate.dto.push.RepositoryDTO;
import com.github.codergate.dto.push.SenderDTO;
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

    private static final String INSTALLATION_CREATED = "created";
    private static final String INSTALLATION_ACTION = "action";

    private static final String INSTALLATION_ADDED = "added";

    private static final String PUSH_PUSHER = "pusher";

    private static final Logger LOGGER = LoggerFactory.getLogger(WebHookListenerService.class);

    public void listen(Map<String, Object> webhookPayload) {
        // checking if the payload contains attributes for installation event
        if (webhookPayload.containsKey(INSTALLATION_ACTION)
                && webhookPayload.get(INSTALLATION_ACTION).equals(INSTALLATION_CREATED)) {
            installationWebhookListener(webhookPayload);
        } else if (webhookPayload.containsKey(INSTALLATION_ACTION)
                && webhookPayload.get(INSTALLATION_ACTION).equals(INSTALLATION_ADDED)) {
            installationAddWebhookListener(webhookPayload);
        } else if (webhookPayload.containsKey(PUSH_PUSHER)) {
            pushEventWebhookListener(webhookPayload);
        } else {
            LOGGER.warn("webHookListener : Following webhook payload is not yet supported {}", webhookPayload);
        }
    }

    private void installationWebhookListener(Map<String, Object> webhookPayload) {
        InstallationPayload payload = Mapper.getInstance().convertValue(webhookPayload,
                InstallationPayload.class);
//        userService.addUser(payload.getInstallation().getAccount());
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

    private void installationAddWebhookListener(Map<String, Object> webhookPayload) {
        InstallationPayload payload = Mapper.getInstance().convertValue(webhookPayload,
                InstallationPayload.class);
        if (payload != null && payload.getInstallation() != null && payload.getInstallation().getAccount() != null
                && payload.getRepositoriesAdded() != null) {
            Account user = userService.addUser(payload.getInstallation().getAccount());
            repositoryService.addRepository(payload.getRepositoriesAdded(), user.getId());

            repositoryService.getRepository(user.getId());
            System.out.println( repositoryService.getRepository(user.getId()));

//            userService.getUser(87960612L);
//            userService.updateUser(87960612L);
//            userService.deleteUserByID(87960612L);
        }




    }

    private void pushEventWebhookListener(Map<String, Object> webhookPayload) {
        PusherPayloadDTO payload = Mapper.getInstance().convertValue(webhookPayload,
                PusherPayloadDTO.class);
        if (payload != null && payload.getPusher() != null && payload.getSender() != null
                && payload.getHeadCommit() != null && payload.getRepository() != null) {
            SenderDTO user = userService.addUser(payload.getSender());
            RepositoryDTO repository = repositoryService.updateRepository(payload.getRepository().getId(), payload.getRepository().getFork());
            //RepositoryDTO repository = repositoryService.addRepository(payload.getRepository());
            eventService.addEvent(payload.getHeadCommit(), user.getId(), repository.getId());
        }
    }



}


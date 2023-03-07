package com.github.codergate.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

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

    private static final String INSTALLATION_ADDED = "created";
    private static final String INSTALLATION_ACTION = "action";

    private static final Logger LOGGER = LoggerFactory.getLogger(WebHookListenerService.class);

    public void listen(Map<String, Object> webhookPayload) {
        // checking if the payload contains attributes for installation event
        if (webhookPayload.containsKey(INSTALLATION_ACTION)
                && webhookPayload.get(INSTALLATION_ACTION).equals(INSTALLATION_ADDED)) {
            installationWebhookListener(webhookPayload);
        } else {
            LOGGER.warn("webHookListener : Following webhook payload is not yet supported {}", webhookPayload);
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
            bodyParamForPost.put("content", Base64.getEncoder().encode(Files.readAllBytes(Path.of(
                    System.getProperty("user.dir")
                            + "/coder-gate/src/main/resources/codeql.yml"))));
            comitter.put("name", "codergate[bot]");
            comitter.put("email", null);
            bodyParamForPost.put("committer", comitter);
            restClient.invokeForGet("https://api.github.com/users/CoderGate[bot]",
                    JwtUtils.getGithubSpecificHeaders(), false);
            restClient.invokeForPost(
                    "https://api.github.com/repos/MayankMadan97/CoderGate/contents/codeql.yml", bodyParamForPost,
                    JwtUtils.getGithubSpecificHeaders(), true);
        } catch (IOException e) {
            LOGGER.error("installationWebhookListener : Failed to read github action file");
        }
    }
}

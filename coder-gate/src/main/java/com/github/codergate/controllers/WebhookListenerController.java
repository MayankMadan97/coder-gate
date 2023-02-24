package com.github.codergate.controllers;

import java.util.Map;

import com.github.codergate.dto.push.PusherPayloadDTO;
import com.github.codergate.services.PushPayloadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.codergate.dto.installation.InstallationPayload;
import com.github.codergate.utils.JwtUtils;
import com.github.codergate.utils.Mapper;
import com.github.codergate.utils.RestClient;

@RestController
public class WebhookListenerController {

    @Autowired
    private RestClient restClient;

    @Autowired
    private PushPayloadService pushPayloadService;

    private static final String INSTALLATION_ADDED = "added";
    private static final String INSTALLATION_ACTION = "action";
    private static final String PUSH_PUSHER = "pusher";

    private static final Logger LOGGER = LoggerFactory.getLogger(WebhookListenerController.class);

    /**
     * endpoint listening to the webhooks
     * 
     * @param webhookPayload
     * @return ResponseEntity<String>
     */
    @PostMapping(value = "/")
    public ResponseEntity<String> webHookListener(@RequestBody Map<String, Object> webhookPayload) {
        LOGGER.debug("webHookListener : Entering the method");
        if (webhookPayload != null && !webhookPayload.isEmpty()) {
            // checking if the payload contains attributes for installation event
            if (webhookPayload.containsKey(INSTALLATION_ACTION) && webhookPayload.get(INSTALLATION_ACTION).equals(INSTALLATION_ADDED)) {
                InstallationPayload payload = Mapper.getInstance().convertValue(webhookPayload,
                        InstallationPayload.class);
                LOGGER.debug("webHookListener : Installation payload {}", payload);
                //TODO: API call to test JWT and RestClient; remove once verified
                restClient.invokeForGet("https://api.github.com/app/installations",
                        JwtUtils.getGithubSpecificHeaders());
            }

            else if(webhookPayload.containsKey(PUSH_PUSHER)){
                PusherPayloadDTO payload = Mapper.getInstance().convertValue(webhookPayload,
                        PusherPayloadDTO.class);
                LOGGER.debug("webHookListener : Pusher payload {}", payload);
                // calling service for saving data to the database
                pushPayloadService.pushPayload(payload);
            }

            else {
                LOGGER.warn("webHookListener : Following webhook payload is not yet supported {}", webhookPayload);
            }
        }
        LOGGER.debug("webHookListener : Exiting the method");
        return ResponseEntity.ok(null);
    }

}

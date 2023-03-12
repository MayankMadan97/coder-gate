package com.github.codergate.controllers;

import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.codergate.services.WebHookListenerService;

@RestController
public class WebhookListenerController {

    @Autowired
    private WebHookListenerService service;

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
            service.handleIncomingRequest(webhookPayload);
        }
        LOGGER.debug("webHookListener : Exiting the method");
        return ResponseEntity.ok(null);
    }

}

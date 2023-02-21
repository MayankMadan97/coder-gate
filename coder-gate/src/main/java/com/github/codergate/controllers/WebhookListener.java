package com.github.codergate.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.codergate.dto.installation.InstallationPayload;
import com.github.codergate.utils.Mapper;
import com.github.codergate.utils.RestClient;

@RestController
public class WebhookListener {

    @Autowired
    private RestClient restClient;

    @PostMapping(value = "/")
    public ResponseEntity<String> webHookListener(@RequestBody Map<String, Object> webhookPayload) {
        if (webhookPayload != null && !webhookPayload.isEmpty()) {
            if (webhookPayload.containsKey("action") && webhookPayload.get("action").equals("added")) {
                InstallationPayload payload = Mapper.getInstance().convertValue(webhookPayload, InstallationPayload.class);
                System.out.print(payload);
                System.out.println("++++++++++");
                MultiValueMap<String,String> customHeaders = new LinkedMultiValueMap<>();
                customHeaders.add("Accept", "application/vnd.github+json");
                System.out.println(restClient.invokeForGet("https://api.github.com/app/installations", customHeaders)); 
            }
        }
        return ResponseEntity.ok(null);
    }

}

package com.github.codergate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
public class GithubOAuthController {

    @Autowired
    private WebClient webClient;

    @GetMapping("/github/access-token")
    public Mono<String> getAccessToken(@RequestParam("code") String code) {
        String clientId = "0386b3f4c4ac6c744a13";
        String clientSecret = "d28502316241ccb882ab04879d8e9114cea04b8a";
        String redirectUri = "http://localhost:4200/dashboard";
        String accessTokenUrl = "https://github.com/login/oauth/access_token";

        return webClient.post()
                .uri(accessTokenUrl)
                .header("Accept", "application/json")
                .body(BodyInserters.fromFormData("client_id", clientId)
                        .with("client_secret", clientSecret)
                        .with("code", code)
                        .with("redirect_uri", redirectUri))
                .retrieve()
                .bodyToMono(String.class);
    }
}

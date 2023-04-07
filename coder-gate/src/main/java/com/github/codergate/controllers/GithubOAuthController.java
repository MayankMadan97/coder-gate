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
        String clientId = "86c61a5d24378f5a9ea0";
        String clientSecret = "26f94b9f588a5179b1c1b3388f387c6ee593ff14";
        String redirectUri = "http://localhost:4200/github-callback";
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

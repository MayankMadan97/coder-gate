package com.github.codergate.controllers;

import java.net.http.HttpRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WebhookListener {

    @PostMapping(value="/")
    public ResponseEntity<String> webHookListener(@RequestBody HttpRequest entity) {
        System.out.print(entity);
        return ResponseEntity.ok(null);
    }

}

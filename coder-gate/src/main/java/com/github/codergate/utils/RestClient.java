package com.github.codergate.utils;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

@Component
public class RestClient {

    @Value("${github.app.id}")
    private String appId;

    private static final RestTemplate restTemplate = new RestTemplate();
    private static final Logger LOGGER = LoggerFactory.getLogger(RestClient.class);

    /**
     * utility method to make post calls to an external api
     * 
     * @param uri
     * @param bodyParams
     * @param customHeaders
     * @return Object
     */
    public Object invokeForPost(String uri, Object bodyParams,
            MultiValueMap<String, String> customHeaders) {
        LOGGER.debug("RestClient :: invokeForPost : Entering the method");
        Object response = null;
        HttpEntity<String> request = null;
        if (uri != null && URI.create(uri) != null) {
            customHeaders = appendAuthenticationHeaders(customHeaders);
            try {
                request = new HttpEntity<>(
                        bodyParams != null ? Mapper.getInstance().writeValueAsString(bodyParams) : null,
                        customHeaders);
            } catch (JsonProcessingException e) {
                LOGGER.error("RestClient :: invokeForPost : API request parsing failed");
            }
            ResponseEntity<String> apiResponse = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
            if (apiResponse.getBody() != null) {
                try {
                    response = Mapper.getInstance().readValue(apiResponse.getBody(), new TypeReference<Object>() {
                    });
                } catch (JsonProcessingException e) {
                    LOGGER.error("invokeForGet : Failed to map api response to the required type");
                }
            }

        } else {
            throw new IllegalArgumentException("Mandatory parameters not found");
        }
        LOGGER.debug("RestClient :: invokeForPost : Exiting the method");
        return response;
    }

    /**
     * utility method to make get calls to an external api
     * 
     * @param uri
     * @param customHeaders
     * @return Object
     */
    public Object invokeForGet(String uri, MultiValueMap<String, String> customHeaders) {
        LOGGER.debug("RestClient :: invokeForPost : Entering the method");
        Object response = null;
        if (uri != null && URI.create(uri) != null) {
            customHeaders = appendAuthenticationHeaders(customHeaders);
            HttpEntity<String> request = new HttpEntity<>(customHeaders);
            ResponseEntity<String> apiResponse = restTemplate.exchange(uri, HttpMethod.GET, request, String.class);
            if (apiResponse.getBody() != null) {
                try {
                    response = Mapper.getInstance().readValue(apiResponse.getBody(), new TypeReference<Object>() {
                    });
                } catch (JsonProcessingException e) {
                    LOGGER.error("invokeForGet : Failed to map api response to the required type");
                }
            }
        } else {
            throw new IllegalArgumentException("Mandatory parameters not found");
        }
        LOGGER.debug("invokeForPost : Exiting the method");
        return response;
    }

    /**
     * method reposnsible to append authorization header in each call
     * 
     * @param existingHeaders
     * @return MultiValueMap<String, String>
     */
    private MultiValueMap<String, String> appendAuthenticationHeaders(
            MultiValueMap<String, String> existingHeaders) {
        if (existingHeaders == null) {
            existingHeaders = new LinkedMultiValueMap<>();
        }
        existingHeaders.add("Authorization", "Bearer " + JwtUtils.generateJwtToken(appId));
        return existingHeaders;
    }

    private RestClient() {
    }
}

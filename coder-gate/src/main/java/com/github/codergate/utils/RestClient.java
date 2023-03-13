package com.github.codergate.utils;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Arrays;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

@Component
public class RestClient {

    private static final String TOKEN = "token";
    private static final String BEARER = "Bearer ";
    private static final String AUTHORIZATION_HEADER = "Authorization";

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
            MultiValueMap<String, String> customHeaders, String installationId) {
        LOGGER.debug("RestClient :: invokeForPost : Entering the method");
        Object response = null;
        HttpEntity<String> request = null;
        if (uri != null && URI.create(uri) != null) {
            if (installationId != null) {
                customHeaders = appendAuthenticationHeaders(customHeaders, installationId);
            }
            try {
                request = new HttpEntity<>(
                        bodyParams != null ? Mapper.getInstance().writeValueAsString(bodyParams) : null,
                        customHeaders);
            } catch (JsonProcessingException e) {
                LOGGER.error("RestClient :: invokeForPost : API request parsing failed");
            }
            ResponseEntity<String> apiResponse = restTemplate.exchange(uri, HttpMethod.PUT, request, String.class);
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
    public Object invokeForGet(String uri, MultiValueMap<String, String> customHeaders, String installationId) {
        LOGGER.debug("RestClient :: invokeForPost : Entering the method");
        Object response = null;
        try {
            if (uri != null && URI.create(URLEncoder.encode(uri, "UTF-8")) != null) {
                if (installationId != null) {
                    customHeaders = appendAuthenticationHeaders(customHeaders, installationId);
                }
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
        } catch (RestClientException | UnsupportedEncodingException e) {
            LOGGER.error("invokeForGet :: Failed to call api due to error {}", Arrays.toString(e.getStackTrace()));
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
            MultiValueMap<String, String> existingHeaders, String installationId) {
        if (existingHeaders == null) {
            existingHeaders = new LinkedMultiValueMap<>();
        }
        String jwtToken = JwtUtils.generateJwtToken(appId);
        existingHeaders.add(AUTHORIZATION_HEADER, BEARER + generateAccessFromJwtToken(jwtToken, installationId));
        return existingHeaders;
    }

    private String generateAccessFromJwtToken(String jwtToken, String installationId) {
        LOGGER.debug("generateAccessFromJwtToken :: Entering the method with JWT token {}", jwtToken);
        String accessToken = null;
        if (jwtToken != null && !jwtToken.trim().isEmpty()) {
            MultiValueMap<String, String> existingHeaders = JwtUtils.getGithubSpecificHeaders();
            existingHeaders.add(AUTHORIZATION_HEADER, BEARER + jwtToken);
            HttpEntity<String> request = new HttpEntity<>(null, existingHeaders);
            ResponseEntity<String> apiResponse = restTemplate.exchange(
                    "https://api.github.com/app/installations/" + installationId + "/access_tokens", HttpMethod.POST,
                    request, String.class);
            if (apiResponse.getBody() != null) {
                JSONObject jsonifiedResponse = new JSONObject(apiResponse.getBody());
                if (jsonifiedResponse.get(TOKEN) != null) {
                    accessToken = jsonifiedResponse.get(TOKEN).toString();
                }
            }
        }
        LOGGER.debug("generateAccessFromJwtToken :: Exiting the method with access token {}", accessToken);
        return accessToken;
    }

    private RestClient() {
    }
}

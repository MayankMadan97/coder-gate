package com.github.codergate.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.github.codergate.dto.controller.PullRequestRestRequest;

@Component
public class WebHookListenerUtil {
    @Autowired
    RestClient restClient;

    public Boolean rejectPullRequest(String owner, String repositoryName, Integer pullRequestNumber,
            String installationId) {
        String apiUrl = String.format("https://api.github.com/repos/%s/%s/pulls/%s", owner, repositoryName,
                pullRequestNumber);
        MultiValueMap<String, String> customHeaders = new LinkedMultiValueMap<>();
        customHeaders.add("X-HTTP-Method-Override", "PATCH");
        customHeaders.add("Content-Type", "application/json");
        PullRequestRestRequest pullRequestRestRequest = new PullRequestRestRequest();
        pullRequestRestRequest.setMessage("Sorry, this pull request cannot be merged at this time.");
        pullRequestRestRequest.setState("closed");
        restClient.invokeForPost(apiUrl, pullRequestRestRequest, customHeaders, installationId);
        return true;
    }

    public boolean commentOnPullRequest(String owner, String repositoryName, String title,
            String body, String assignee, String[] label, String installationId) {
        boolean isIssueRaised = false;
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("title", title);
        bodyMap.put("body", body);
        bodyMap.put("assignee", assignee);
        bodyMap.put("label", label);
        if (owner != null && repositoryName != null) {
            String url = "https://api.github.com/repos/" + owner + "/" + repositoryName + "/issues";
            restClient.invokeForPost(url, bodyMap, null, installationId);
        }
        return isIssueRaised;
    }
}

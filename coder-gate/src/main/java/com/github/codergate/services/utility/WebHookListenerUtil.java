package com.github.codergate.services.utility;

import com.github.codergate.dto.controller.PullRequestRestRequest;
import com.github.codergate.utils.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;



@Component
public class WebHookListenerUtil {
    @Autowired
    RestClient restClient;


    public Boolean rejectPullRequest(String owner, String repositoryName, Integer pullRequestNumber,String installationId){

        String apiUrl = String.format("https://api.github.com/repos/%s/%s/pulls/%s", owner, repositoryName, pullRequestNumber);
        MultiValueMap<String,String> customHeaders = new LinkedMultiValueMap<>();
        customHeaders.add("X-HTTP-Method-Override", "PATCH");
        customHeaders.add("Content-Type", "application/json");
        PullRequestRestRequest pullRequestRestRequest = new PullRequestRestRequest();
        pullRequestRestRequest.setMessage("Sorry, this pull request cannot be merged at this time.");
        pullRequestRestRequest.setState("closed");
        restClient.invokeForPost(apiUrl,pullRequestRestRequest,customHeaders,installationId);
        return true;
    }
}

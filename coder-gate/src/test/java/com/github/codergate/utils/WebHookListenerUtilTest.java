package com.github.codergate.utils;

import com.github.codergate.dto.controller.PullRequestRestRequest;
import com.github.codergate.utils.RestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WebHookListenerUtilTest {

    @Mock
    RestClient restClient;

    @InjectMocks
    WebHookListenerUtil webHookListenerUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    String owner = "alex";
    String repositoryName = "alexRepository";
    Integer pullRequestNumber = 123;
    String installationId = "456";

    @Test
    void testRejectPullRequest() {

        String apiUrl = String.format("https://api.github.com/repos/%s/%s/pulls/%s", owner, repositoryName,
                pullRequestNumber);
        MultiValueMap<String, String> customHeaders = new LinkedMultiValueMap<>();
        customHeaders.add("X-HTTP-Method-Override", "PATCH");
        customHeaders.add("Content-Type", "application/json");
        when(restClient.invokeForPost(eq(apiUrl), argThat(new ArgumentMatcher<PullRequestRestRequest>() {
            @Override
            public boolean matches(PullRequestRestRequest argument) {
                return argument.getMessage().equals("Sorry, this pull request cannot be merged at this time.")
                        && argument.getState().equals("closed");
            }
        }), eq(customHeaders), eq(installationId))).thenReturn(true);
        Boolean result = webHookListenerUtil.rejectPullRequest(owner, repositoryName, pullRequestNumber, installationId);
        assertTrue(result);
    }

    @Test
    void testCommentOnPullRequest() {

        String title = "Test Issue Title";
        String body = "Test Issue Body";
        String assignee = "testAssignee";
        String[] label = new String[]{"testLabel1", "testLabel2"};
        String apiUrl = "https://api.github.com/repos/" + owner + "/" + repositoryName + "/issues";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", title);
        requestBody.put("body", body);
        requestBody.put("assignee", assignee);
        requestBody.put("label", label);
        when(restClient.invokeForPost(apiUrl, requestBody, null, installationId))
                .thenReturn(true);
        boolean result = webHookListenerUtil.commentOnPullRequest(owner, repositoryName, title, body, assignee, label, installationId);
        assertFalse(result);
        verify(restClient).invokeForPost(apiUrl, requestBody, null, installationId);
    }





}





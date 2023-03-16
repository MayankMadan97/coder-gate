package com.github.codergate;

import com.github.codergate.services.utility.WebHookListenerUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoderGateApplicationTests {

    @Autowired
    WebHookListenerUtil webHookListenerUtil;

    @Test
    void pullRequestReject(){
        String token = "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";
        webHookListenerUtil.rejectPullRequest(
                "limysh",
                "lim",
                4,token);
    }
}



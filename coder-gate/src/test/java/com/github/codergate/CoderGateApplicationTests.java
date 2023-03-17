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
        webHookListenerUtil.rejectPullRequest(
                "limysh",
                "lim",
                8,"35166207");
    }
}



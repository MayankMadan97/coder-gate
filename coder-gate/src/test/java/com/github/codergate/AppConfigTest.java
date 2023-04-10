package com.github.codergate;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AppConfigTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void testWebClientBean() {
        AppConfig appConfig = new AppConfig();
        WebClient webClient = (WebClient) ReflectionTestUtils.invokeMethod(appConfig, "webClient");
        assertNotNull(webClient);
    }

}

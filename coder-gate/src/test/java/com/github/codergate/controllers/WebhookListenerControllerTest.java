package com.github.codergate.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.codergate.services.WebHookListenerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WebhookListenerController.class)
class WebhookListenerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WebHookListenerService serviceMock;
    Map<String, Object> payload = new HashMap<>();


    @Test
    void testWebhookListenerWhenStatusIs200() throws Exception {
        payload.put("action", "deleted");

        MvcResult result = mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(payload)))
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        assertEquals("", responseContent);
        verify(serviceMock, times(1)).listen(payload);
    }

    @Test
    void testWebhookListenerWhenPayloadHasMissingActionField1() throws Exception {
        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(payload)))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
        verify(serviceMock, never()).listen(any(Map.class));
        MvcResult result = mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(payload)))
                .andReturn();
        assertEquals("", result.getResponse().getContentAsString());
    }



}






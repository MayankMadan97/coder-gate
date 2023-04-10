package com.github.codergate.controllers;

import com.github.codergate.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetUserDetailsWithValidAccessToken() throws Exception {
        String accessToken = "valid-access-token";
        String userDetails = "User details for valid access token";
        String token ="githubAccessToken";
        String urlTemplate ="/getUserDetails";

        when(userService.getUserDetails(accessToken)).thenReturn(userDetails);

        MvcResult result = mockMvc.perform(get(urlTemplate)
                        .param(token, accessToken))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        assertEquals(userDetails, responseContent);
        verify(userService, times(1)).getUserDetails(accessToken);
    }


}


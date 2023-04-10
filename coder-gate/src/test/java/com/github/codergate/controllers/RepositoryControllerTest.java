package com.github.codergate.controllers;

import com.github.codergate.dto.RepositoryMinimal;
import com.github.codergate.dto.controller.RepositoryResponse;
import com.github.codergate.services.RepositoryService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RepositoryController.class)
class RepositoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepositoryService repositoryService;
    long userId = 456;
    int repoIdOne =1;
    int repoIdTwo =2;

    String urlTemplate ="/getRepositories/{userId}";
    String expectedResponse = "{\"repositories\":[{\"timestamp\":0,\"id\":1,\"name\":\"repo1\"}," +
            "{\"timestamp\":0,\"id\":2,\"name\":\"repo2\"}]}";

    RepositoryResponse repositoryResponse = new RepositoryResponse();


    @Test
    void testFetchRepositoriesWithValidUserId() throws Exception {
        RepositoryMinimal repository1 = new RepositoryMinimal();
        repository1.setTimestamp(0);
        repository1.setId(repoIdOne);
        repository1.setName("repo1");

        RepositoryMinimal repository2 = new RepositoryMinimal();
        repository1.setTimestamp(0);
        repository2.setId(repoIdTwo);
        repository2.setName("repo2");

        repositoryResponse.setRepositories(Arrays.asList(repository1, repository2));

        when(repositoryService.getRepositoryResponse(eq(userId))).thenReturn(repositoryResponse);

        MvcResult result = mockMvc.perform(get(urlTemplate, userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String actualResponse = result.getResponse().getContentAsString();

        assertEquals(expectedResponse, actualResponse);
        verify(repositoryService, times(1)).getRepositoryResponse(eq(userId));
    }

    @Test
    void testFetchRepositoriesWithNoRepositoriesForValidUserId() throws Exception {

        repositoryResponse.setRepositories(Collections.emptyList());

        when(repositoryService.getRepositoryResponse(eq(userId))).thenReturn(repositoryResponse);

        MvcResult result = mockMvc.perform(get(urlTemplate, userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String expectedResponse = "{\"repositories\":[]}";
        String actualResponse = result.getResponse().getContentAsString();

        assertEquals(expectedResponse, actualResponse);
        verify(repositoryService, times(1)).getRepositoryResponse(eq(userId));
    }











}



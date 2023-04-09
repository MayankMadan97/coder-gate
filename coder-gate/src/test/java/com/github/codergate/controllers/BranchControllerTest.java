package com.github.codergate.controllers;
import com.github.codergate.services.BranchService;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(BranchController.class)
public class BranchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BranchService branchService;
    int branchNumber =3;
    String urlTemplate = "/getBranches/{repoId}";
    String jsonContent ="[\"main\", \"dev\", \"prod\"]";

    @Test
    public void testFetchTimeStampInsights() throws Exception {
        String repoId = "123";
        List<String> branches = Arrays.asList("main", "dev", "prod");
        Mockito.when(branchService.getBranchesByRepoId(repoId)).thenReturn(branches);
        mockMvc.perform(get(urlTemplate, repoId))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonContent));
        Mockito.verify(branchService).getBranchesByRepoId(repoId);
        assertEquals(branchNumber, branches.size());

    }
}

package com.github.codergate.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.codergate.dto.threshold.ThresholdDTO;
import com.github.codergate.services.ThresholdService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ThresholdControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ThresholdService thresholdService;

    private final int repositoryID = 101;
    private final boolean allowAction = true;
    private final int bugs = 10;
    private final int vulnerabilities = 20;
    private final double codeSmell = 30.5;
    private final double testCoverage = 40.6;
    private final double duplicatedLines5 = 0.7;
    private final double cyclomaticComplexity = 60.8;
    private final double documentedLines= 70.9;
    private final int cyclicDependency = 80;
    private final int godComponents = 90;
    private final int cycDependentMod = 80;
    private final int insufficientModularization = 70;
    private final int unnecessaryAbstraction = 60;
    private final int complexConditional = 50;
    private final int emptyCatchClause = 40;
    private final int missingAssertion = 30;
    private final int emptyTest = 20;
    private final double archSmellDensity = 10.1;
    private final double designSmellDensity = 20.2;
    private final double impSmellDensity = 30.3;

    private ThresholdDTO setValuesToThresholdDTO() {
        ThresholdDTO thresholdDTO = new ThresholdDTO();
        thresholdDTO.setAllowAction(allowAction);
        thresholdDTO.setBugs(bugs);
        thresholdDTO.setVulnerabilities(vulnerabilities);
        thresholdDTO.setCodeSmell(codeSmell);
        thresholdDTO.setTestCoverage(testCoverage);
        thresholdDTO.setDuplicatedLines(duplicatedLines5);
        thresholdDTO.setCyclomaticComplexity(cyclomaticComplexity);
        thresholdDTO.setDocumentedLines(documentedLines);
        thresholdDTO.setCyclicDependency(cyclicDependency);
        thresholdDTO.setGodComponents(godComponents);
        thresholdDTO.setCycDependentMod(cycDependentMod);
        thresholdDTO.setInsufficientModularization(insufficientModularization);
        thresholdDTO.setUnnecessaryAbstraction(unnecessaryAbstraction);
        thresholdDTO.setComplexConditional(complexConditional);
        thresholdDTO.setEmptyCatchClause(emptyCatchClause);
        thresholdDTO.setMissingAssertion(missingAssertion);
        thresholdDTO.setEmptyTest(emptyTest);
        thresholdDTO.setArchSmellDensity(archSmellDensity);
        thresholdDTO.setDesignSmellDensity(designSmellDensity);
        thresholdDTO.setImpSmellDensity(impSmellDensity);
        return thresholdDTO;
    }

    @Test
    public void testGetThresholdValuesWithValidRepoID() throws Exception {
        ThresholdDTO expected = setValuesToThresholdDTO();
        when(thresholdService.getThresholdByID(repositoryID)).thenReturn(expected);

        MvcResult mvcResult = mockMvc.perform(get("/threshold/{repoID}", repositoryID))
                .andExpect(status().isOk())
                .andReturn();

        String responseJson = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        ThresholdDTO actual = objectMapper.readValue(responseJson, ThresholdDTO.class);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void testPostThresholdWithNullThresholdDTO() throws Exception {
        ThresholdDTO thresholdDTO = null;
        String expected = "";

        String jsonRequest = new ObjectMapper().writeValueAsString(thresholdDTO);

        MvcResult mvcResult = mockMvc.perform(post("/threshold/{repoID}", repositoryID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                        .andReturn();

        String actual = mvcResult.getResponse().getContentAsString();
        assertEquals(expected, actual);
    }

    @Test
    public void testPostThresholdWithCorrectThresholdDTO() throws Exception {
        ThresholdDTO thresholdDTO = setValuesToThresholdDTO();
        int expectedStatus = 200;

        String jsonRequest = new ObjectMapper().writeValueAsString(thresholdDTO);

        MvcResult mvcResult = mockMvc.perform(post("/threshold/{repoID}", repositoryID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andReturn();

        int actualStatus = mvcResult.getResponse().getStatus();
        assertEquals(expectedStatus, actualStatus);
    }
}
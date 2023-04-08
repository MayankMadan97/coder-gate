package com.github.codergate.services;

import com.github.codergate.entities.*;
import com.github.codergate.repositories.AnalysisRepository;
import com.github.codergate.repositories.ThresholdRepository;
import com.github.codergate.services.utility.PullRequestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PullRequestServiceTest {

    @Mock
    private ThresholdRepository thresholdRepository;

    @Mock
    private AnalysisRepository analysisRepository;

    @Mock
    private PullRequestUtil pullRequestUtil;

    @InjectMocks
    private PullRequestService pullRequestService;

    private int thresholdId = 1;
    private int bugs = 10;
    private int vulnerabilities = 5;
    private double codeSmell = 0.7;
    private double testCoverage = 0.9;
    private double duplicatedLines = 0.5;
    private double cyclomaticComplexity = 2.5;
    private double documentedLines = 0.8;
    private int cyclicDependency = 2;
    private int godComponents = 1;
    private int cycDependentMod = 3;
    private int insufficientModularization = 1;
    private int unnecessaryAbstraction = 0;
    private int complexConditional = 4;
    private int emptyCatchClause = 2;
    private int missingAssertion = 3;
    private int emptyTest = 1;
    private double archSmellDensity = 0.2;
    private double designSmellDensity = 0.3;
    private double impSmellDensity = 0.1;
    private int repositoryId = 1;
    private String repositoryName = "example-repo";
    private boolean fork = false;
    private String installationId = "1234";
    private long userId = 1L;
    private String userName = "john_doe";
    private String email = "john_doe@example.com";
    private int id = 1;
    private String type = "static";
    private long timestamp = 1648825200;
    private String branchUrl = "https://branchurl";

    ThresholdEntity thresholdEntity = new ThresholdEntity();
    AnalysisEntity analysisEntity = new AnalysisEntity();
    RepositoryEntity repositoryEntity = new RepositoryEntity();
    UserEntity userEntity = new UserEntity();
    BranchEntity branchEntity = new BranchEntity();
    BranchId branchId = new BranchId();


    @Before
    public void setUp(){
        userEntity.setUserId(userId);
        userEntity.setUserName(userName);
        userEntity.setEmail(email);
        repositoryEntity.setRepositoryId(repositoryId);
        repositoryEntity.setRepositoryName(repositoryName);
        repositoryEntity.setFork(fork);
        repositoryEntity.setInstallationId(installationId);
        repositoryEntity.setUserEntity(userEntity);
        thresholdEntity.setThresholdId(thresholdId);
        thresholdEntity.setRepositoryIdInThreshold(repositoryEntity);
        thresholdEntity.setBugs(bugs);
        thresholdEntity.setVulnerabilities(vulnerabilities);
        thresholdEntity.setCodeSmell(codeSmell);
        thresholdEntity.setTestCoverage(testCoverage);
        thresholdEntity.setDuplicatedLines(duplicatedLines);
        thresholdEntity.setCyclomaticComplexity(cyclomaticComplexity);
        thresholdEntity.setDocumentedLines(documentedLines);
        thresholdEntity.setCyclicDependency(cyclicDependency);
        thresholdEntity.setGodComponents(godComponents);
        thresholdEntity.setCycDependentMod(cycDependentMod);
        thresholdEntity.setInsufficientModularization(insufficientModularization);
        thresholdEntity.setUnnecessaryAbstraction(unnecessaryAbstraction);
        thresholdEntity.setComplexConditional(complexConditional);
        thresholdEntity.setEmptyCatchClause(emptyCatchClause);
        thresholdEntity.setMissingAssertion(missingAssertion);
        thresholdEntity.setEmptyTest(emptyTest);
        thresholdEntity.setArchSmellDensity(archSmellDensity);
        thresholdEntity.setImpSmellDensity(impSmellDensity);
        thresholdEntity.setDesignSmellDensity(designSmellDensity);
        branchId.setRepositoryId(repositoryId);
        branchId.setBranchUrl(branchUrl);
        branchEntity.setBranchId(branchId);
        branchEntity.setRepositoryIdInBranch(repositoryEntity);
        analysisEntity.setId(id);
        analysisEntity.setBugs(bugs);
        analysisEntity.setVulnerabilities(vulnerabilities);
        analysisEntity.setCodeSmell(codeSmell);
        analysisEntity.setTestCoverage(testCoverage);
        analysisEntity.setDuplicatedLines(duplicatedLines);
        analysisEntity.setCyclomaticComplexity(cyclomaticComplexity);
        analysisEntity.setDocumentedLines(documentedLines);
        analysisEntity.setCyclicDependency(cyclicDependency);
        analysisEntity.setGodComponents(godComponents);
        analysisEntity.setCyclicallyDependentModularization(cycDependentMod);
        analysisEntity.setInsufficientModularization(insufficientModularization);
        analysisEntity.setUnnecessaryAbstraction(unnecessaryAbstraction);
        analysisEntity.setComplexConditional(complexConditional);
        analysisEntity.setEmptyCatchClause(emptyCatchClause);
        analysisEntity.setMissingAssertion(missingAssertion);
        analysisEntity.setEmptyTest(emptyTest);
        analysisEntity.setArchSmellDensity(archSmellDensity);
        analysisEntity.setImpSmellDensity(impSmellDensity);
        analysisEntity.setDesignSmellDensity(designSmellDensity);
        analysisEntity.setBranchId(branchEntity);
    }

    @Test
    public void testPullRequestCheckWithValidThresholdAndAnalysisEntities() {
        when(thresholdRepository.findByRepositoryId(repositoryId)).thenReturn(thresholdEntity);
        when(analysisRepository.findLatestAnalysisByRepositoryId(repositoryId)).thenReturn(analysisEntity);
        when(pullRequestUtil.checkThreshold(analysisEntity, thresholdEntity)).thenReturn(true);
        Boolean result = pullRequestService.pullRequestCheck(1);
        assertEquals(true, result);
    }

    @Test
    public void testPullRequestCheckWithNullThresholdEntity() {
        when(thresholdRepository.findByRepositoryId(repositoryId)).thenReturn(null);
        when(analysisRepository.findLatestAnalysisByRepositoryId(repositoryId)).thenReturn(analysisEntity);
        Boolean result = pullRequestService.pullRequestCheck(1);
        assertEquals(false, result);
    }

    @Test
    public void testPullRequestCheckWithNullAnalysisEntity() {
        when(thresholdRepository.findByRepositoryId(repositoryId)).thenReturn(thresholdEntity);
        when(analysisRepository.findLatestAnalysisByRepositoryId(repositoryId)).thenReturn(null);
        Boolean result = pullRequestService.pullRequestCheck(1);
        assertEquals(false, result);
    }

    @Test
    public void testPullRequestCheckWithNullThresholdAndAnalysisEntities() {
        when(thresholdRepository.findByRepositoryId(repositoryId)).thenReturn(null);
        when(analysisRepository.findLatestAnalysisByRepositoryId(repositoryId)).thenReturn(null);
        Boolean result = pullRequestService.pullRequestCheck(1);
        assertEquals(false, result);
    }
}

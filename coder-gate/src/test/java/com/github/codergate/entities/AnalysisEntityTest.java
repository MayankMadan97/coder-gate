package com.github.codergate.entities;

import com.github.codergate.repositories.AnalysisRepository;
import com.github.codergate.repositories.BranchRepository;
import com.github.codergate.repositories.RepositoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AnalysisEntityTest {
    private final int repositoryID = 101;
    private final String branchURL = "main";
    private final long timestamp = 10010101;
    private final int bugs = 10;
    private final int vulnerabilities = 20;
    private final double codeSmell = 30.5;
    private final double testCoverage = 40.6;
    private final double duplicatedLines5 = 0.7;
    private final double cyclomaticComplexity = 60.8;
    private final double documentedLines = 70.9;
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

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    BranchRepository branchRepository;
    @Autowired
    RepositoryRepository repositoryRepository;
    @Autowired
    AnalysisRepository analysisRepository;

    private AnalysisEntity setup() {
        BranchId branchId = new BranchId();
        branchId.setBranchUrl(branchURL);
        branchId.setRepositoryId(repositoryID);

        RepositoryEntity repositoryEntity = new RepositoryEntity();
        repositoryEntity.setRepositoryId(repositoryID);

        BranchEntity branchEntity = new BranchEntity();
        branchEntity.setBranchId(branchId);
        branchEntity.setRepositoryIdInBranch(repositoryEntity);

        List<BranchEntity> list = new ArrayList<>();
        list.add(branchEntity);
        repositoryEntity.setBranchEntities(list);

        EventEntity event = new EventEntity();
        event.setEventName("test event");
        event.setCommitId("12345");
        event.setCommitMessage("test commit message");
        Set<EventEntity> set = new HashSet<>();

        repositoryEntity.setEventAndRepository(set);

        entityManager.persist(repositoryEntity);
        entityManager.persist(branchEntity);
        entityManager.persist(event);

        AnalysisEntity analysisEntity = new AnalysisEntity();
        analysisEntity.setBranchId(branchEntity);

        analysisEntity.setBugs(bugs);
        analysisEntity.setVulnerabilities(vulnerabilities);
        analysisEntity.setCodeSmell(codeSmell);
        analysisEntity.setDuplicatedLines(duplicatedLines5);
        analysisEntity.setTestCoverage(testCoverage);
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
        analysisEntity.setDesignSmellDensity(designSmellDensity);
        analysisEntity.setImpSmellDensity(impSmellDensity);

        analysisEntity.setTimestamp(timestamp);

        return analysisEntity;
    }

    @Test
    public void testCustomConstructor() {
        AnalysisEntity expected = new AnalysisEntity();
        expected.setTimestamp(timestamp);

        BranchId branchId = new BranchId(repositoryID, branchURL);
        RepositoryEntity repositoryEntity = new RepositoryEntity();
        repositoryEntity.setRepositoryId(repositoryID);
        BranchEntity branchEntity = new BranchEntity(branchId, repositoryEntity);

        expected.setBranchId(branchEntity);

        AnalysisEntity actual = new AnalysisEntity(repositoryID, branchURL, timestamp);

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void testAnalysisEntityPersistence() {
        AnalysisEntity expected = setup();

        entityManager.persistAndFlush(expected);

        entityManager.clear();

        AnalysisEntity actual = entityManager.find(AnalysisEntity.class, expected.getId());

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void testDeleteAnalysisEntity() {
        AnalysisEntity analysisEntity = setup();

        entityManager.persistAndFlush(analysisEntity);
        entityManager.clear();

        AnalysisEntity retrievedAnalysis = entityManager.find(AnalysisEntity.class, analysisEntity.getId());
        if (retrievedAnalysis != null) {
            System.out.println("Successfully retrieved");
        }

        entityManager.remove(retrievedAnalysis);
        entityManager.flush();

        AnalysisEntity deletedAnalysis = entityManager.find(AnalysisEntity.class, analysisEntity.getId());
        assertNull(deletedAnalysis);
    }

    @Test
    public void testAllGetters() {
        AnalysisEntity expected = setup();

        BranchId branchId = new BranchId();
        branchId.setBranchUrl(branchURL);
        branchId.setRepositoryId(repositoryID);

        RepositoryEntity repositoryEntity = new RepositoryEntity();
        repositoryEntity.setRepositoryId(repositoryID);

        BranchEntity branchEntity = new BranchEntity();
        branchEntity.setBranchId(branchId);
        branchEntity.setRepositoryIdInBranch(repositoryEntity);

        List<BranchEntity> list = new ArrayList<>();
        list.add(branchEntity);
        repositoryEntity.setBranchEntities(list);

        EventEntity event = new EventEntity();
        event.setEventName("test event");
        event.setCommitId("12345");
        event.setCommitMessage("test commit message");
        Set<EventEntity> set = new HashSet<>();

        repositoryEntity.setEventAndRepository(set);

        AnalysisEntity actual = new AnalysisEntity();

        actual.setBranchId(branchEntity);
        actual.setId(expected.getId());
        actual.setType(expected.getType());
        actual.setBugs(expected.getBugs());
        actual.setVulnerabilities(expected.getVulnerabilities());
        actual.setCodeSmell(expected.getCodeSmell());
        actual.setTestCoverage(expected.getTestCoverage());
        actual.setDuplicatedLines(expected.getDuplicatedLines());
        actual.setCyclomaticComplexity(expected.getCyclomaticComplexity());
        actual.setDocumentedLines(expected.getDocumentedLines());

        actual.setCyclicDependency(expected.getCyclicDependency());
        actual.setGodComponents(expected.getGodComponents());

        actual.setCyclicallyDependentModularization(expected.getCyclicallyDependentModularization());
        actual.setInsufficientModularization(expected.getInsufficientModularization());
        actual.setUnnecessaryAbstraction(expected.getUnnecessaryAbstraction());

        actual.setComplexConditional(expected.getComplexConditional());
        actual.setEmptyCatchClause(expected.getEmptyCatchClause());

        actual.setMissingAssertion(expected.getMissingAssertion());
        actual.setEmptyTest(expected.getEmptyTest());

        actual.setArchSmellDensity(expected.getArchSmellDensity());
        actual.setDesignSmellDensity(expected.getDesignSmellDensity());
        actual.setImpSmellDensity(expected.getImpSmellDensity());

        actual.setTimestamp(expected.getTimestamp());

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
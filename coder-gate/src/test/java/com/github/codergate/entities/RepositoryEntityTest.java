package com.github.codergate.entities;

import com.github.codergate.repositories.EventRepository;
import com.github.codergate.repositories.RepositoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoryEntityTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RepositoryRepository repositoryRepository;

    @Autowired
    private EventRepository eventRepository;

    RepositoryEntity repositoryEntity = new RepositoryEntity();
    UserEntity userEntity = new UserEntity();
    EventEntity eventEntity = new EventEntity();
    BranchEntity branchEntity = new BranchEntity();
    TagEntity tagEntity = new TagEntity();


    int repositoryId =123;
    int userId =2;
    int thresholdId =1;
    int bugs = 100;
    int vulnerabilities = 80;
    double codeSmell =99;
    double testCoverage = 89;
    double duplicatedLines =12;
    double cyclomaticComplexity =12;
    double documentedLines =11;

    // Architecture Smells
    int cyclicDependency = 2;
    int godComponents = 0;

    // Design Smells
    int cycDependentMod = 1;
    int insufficientModularization= 5;
    int unnecessaryAbstraction = 8;

    // Implementation Smells
    int complexConditional = 9;
    int emptyCatchClause = 88;

    // Test Smells
    int missingAssertion =90;
    int emptyTest =1;

    // Densities
    double archSmellDensity =12;
    double designSmellDensity =76;
    double impSmellDensity =111;

    @Test
    void testRepositoryEntitySave() {
        repositoryEntity.setRepositoryId(repositoryId);
        repositoryEntity.setRepositoryName("alex_repo");
        repositoryEntity.setFork(false);
        userEntity.setUserId(userId);
        userEntity.setUserName("alex");
        repositoryEntity.setUserEntity(userEntity);
        entityManager.persist(userEntity);
        entityManager.persist(repositoryEntity);
        entityManager.flush();
        entityManager.clear();
        UserEntity retrievedUser = entityManager.find(UserEntity.class, userEntity.getUserId());
        RepositoryEntity retrievedRepo = entityManager.find(RepositoryEntity.class, repositoryEntity.getRepositoryId());
        assertNotNull(retrievedUser);
        assertEquals(userEntity.getUserId(), retrievedUser.getUserId());
        assertEquals(userEntity.getUserName(), retrievedUser.getUserName());
        assertNotNull(retrievedRepo);
        assertEquals(repositoryEntity.getRepositoryId(), retrievedRepo.getRepositoryId());
        assertEquals(repositoryEntity.getRepositoryName(), retrievedRepo.getRepositoryName());
        assertEquals(repositoryEntity.isFork(), retrievedRepo.isFork());
        assertEquals(repositoryEntity.getUserEntity().getUserId(), retrievedRepo.getUserEntity().getUserId());
    }

    @Test
    void testRepositoryEntityUpdate() {
        String installationId ="123";
        String updatedValue ="456";
        repositoryEntity.setRepositoryName("alex_repo");
        repositoryEntity.setFork(false);
        repositoryEntity.setInstallationId(installationId);
        repositoryEntity = repositoryRepository.save(repositoryEntity);
        int repositoryId = repositoryEntity.getRepositoryId();
        repositoryEntity.setFork(true);
        repositoryEntity.setInstallationId(updatedValue);
        repositoryEntity = repositoryRepository.save(repositoryEntity);
        RepositoryEntity updatedRepositoryEntity = repositoryRepository.findById(repositoryId).orElse(null);
        assertEquals(updatedValue, updatedRepositoryEntity.getInstallationId());
    }

    @Test
    void testRepositoryEntityDelete() {
        userEntity.setUserId(userId);
        userEntity.setUserName("alex");
        repositoryEntity.setRepositoryId(repositoryId);
        repositoryEntity.setRepositoryName("alex_repo");
        repositoryEntity.setFork(false);
        repositoryEntity.setUserEntity(userEntity);
        entityManager.persist(userEntity);
        entityManager.persist(repositoryEntity);
        entityManager.flush();
        entityManager.remove(repositoryEntity);
        entityManager.flush();
        RepositoryEntity retrievedRepository = entityManager.find(RepositoryEntity.class, repositoryEntity.getRepositoryId());
        assertNull(retrievedRepository);
    }

    @Test
    void testUserEntityMapping() {
        userEntity.setUserId(userId);
        userEntity.setUserName("alex");
        entityManager.persist(userEntity);
        repositoryEntity.setRepositoryId(repositoryId);
        repositoryEntity.setRepositoryName("alex_repo");
        repositoryEntity.setFork(false);
        repositoryEntity.setUserEntity(userEntity);
        entityManager.persist(repositoryEntity);
        entityManager.flush();
        Optional<RepositoryEntity> found = repositoryRepository.findById(repositoryEntity.getRepositoryId());
        assertTrue(found.isPresent());
        assertEquals(repositoryEntity.getRepositoryName(), found.get().getRepositoryName());;
    }

    @Test
    void testRepositoryEventMapping() {
        repositoryEntity.setRepositoryId(1);
        repositoryEntity.setRepositoryName("alex_repo");
        repositoryEntity.setFork(false);
        eventEntity.setEventId(1L);
        eventEntity.setEventName("push");
        eventEntity.setCommitId("123456");
        eventEntity.setCommitMessage("commit push");
        repositoryEntity.setEventAndRepository(new HashSet<>());
        repositoryEntity.getEventAndRepository().add(eventEntity);
        repositoryEntity = repositoryRepository.save(repositoryEntity);
        RepositoryEntity savedRepository = repositoryRepository.findById(1).orElse(null);
        assertThat(savedRepository.getEventAndRepository()).isNotNull();
        assertEquals(1, savedRepository.getEventAndRepository().size());
    }

    @Test
    void testRepositoryBranchMapping() {
        repositoryEntity.setRepositoryId(repositoryId);
        repositoryEntity.setRepositoryName("alex-repo");
        repositoryEntity.setFork(false);
        BranchId branchId = new BranchId();
        branchId.setRepositoryId(repositoryEntity.getRepositoryId());
        branchId.setBranchUrl("http://dev");
        branchEntity.setBranchId(branchId);
        branchEntity.setRepositoryIdInBranch(repositoryEntity);
        repositoryEntity.setBranchEntities(new ArrayList<>());
        repositoryEntity.getBranchEntities().add(branchEntity);
        repositoryEntity = repositoryRepository.save(repositoryEntity);
        RepositoryEntity savedRepository = repositoryRepository.findById(repositoryId).orElse(null);
        assertThat(savedRepository.getBranchEntities()).isNotNull();
        BranchEntity savedBranch = savedRepository.getBranchEntities().get(0);
        assertThat(savedBranch.getBranchId().getRepositoryId()).isEqualTo(repositoryId);
        ;
    }

    @Test
    void testRepositoryTagMapping() {
        repositoryEntity.setRepositoryId(repositoryId);
        repositoryEntity.setRepositoryName("my-repo");
        repositoryEntity.setFork(false);
        TagId tagId = new TagId();
        tagId.setRepositoryId(repositoryEntity.getRepositoryId());
        tagId.setTagUrl("alex_tag");
        tagEntity.setTagId(tagId);
        tagEntity.setRepositoryIdInTag(repositoryEntity);
        repositoryEntity.setTagEntities(new ArrayList<>());
        repositoryEntity.getTagEntities().add(tagEntity);
        repositoryEntity = repositoryRepository.save(repositoryEntity);
        RepositoryEntity savedRepository = repositoryRepository.findById(repositoryId).orElse(null);
        assertThat(savedRepository.getTagEntities()).isNotNull();
        TagEntity savedTag = savedRepository.getTagEntities().get(0);
        assertThat(savedTag.getTagId().getRepositoryId()).isEqualTo(repositoryId);
        assertThat(savedTag.getTagId().getTagUrl()).isEqualTo("alex_tag");
        assertThat(savedTag.getRepositoryIdInTag().getRepositoryId()).isEqualTo(repositoryId);
    }

    @Test
    void testAllArgsConstructor() {
        String installationId ="123";
        userEntity.setUserId(userId);
        userEntity.setUserName("alex");
        RepositoryEntity repository = new RepositoryEntity(repositoryId, "alex_repo", false, userEntity,
                new ArrayList<>(), new ArrayList<>(),
                new HashSet<>(), null, installationId);
        assertThat(repository.getInstallationId()).isEqualTo("123");
    }

    @Test
    void testRepositoryThresholdMapping() {
        RepositoryEntity repositoryEntity = new RepositoryEntity();
        repositoryEntity.setRepositoryId(repositoryId);
        boolean action = true;
        repositoryEntity.setRepositoryName("example");
        repositoryEntity.setFork(true);
        ThresholdEntity thresholdEntity = new ThresholdEntity(thresholdId,repositoryEntity,bugs,vulnerabilities,codeSmell,
                testCoverage,duplicatedLines,cyclomaticComplexity,documentedLines,cyclicDependency,
                godComponents,cycDependentMod,insufficientModularization,unnecessaryAbstraction,complexConditional,
                emptyCatchClause,missingAssertion,emptyTest,archSmellDensity,designSmellDensity,impSmellDensity,action);
        repositoryEntity.setThresholdAndRepository(thresholdEntity);
        entityManager.persist(repositoryEntity);
        entityManager.flush();
        RepositoryEntity expected = repositoryRepository.findById(repositoryEntity.getRepositoryId()).orElse(null);
        assertThat(expected).usingRecursiveComparison().isEqualTo(repositoryEntity);
    }
}
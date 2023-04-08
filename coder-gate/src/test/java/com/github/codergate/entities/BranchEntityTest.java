package com.github.codergate.entities;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BranchEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    private final RepositoryEntity repositoryEntity = new RepositoryEntity();
    private final BranchId branchId = new BranchId();
    private final BranchEntity branch = new BranchEntity();



    @Test
     void testAllArgsConstructor() {
        int repositoryId = 1;
        String branchName = "main";
        repositoryEntity.setRepositoryId(repositoryId);
        BranchId branchId = new BranchId(repositoryId, branchName);
        BranchEntity branchEntity = new BranchEntity(branchId, repositoryEntity);
        assertThat(branchEntity.getBranchId()).isEqualTo(branchId);
    }

    @Test
     void testBranchEntityPersisted() {
        repositoryEntity.setRepositoryName("testRepo");
        entityManager.persist(repositoryEntity);
        branchId.setRepositoryId(repositoryEntity.getRepositoryId());
        branchId.setBranchUrl("http://testBranch/kksslslldn");
        branch.setBranchId(branchId);
        branch.setRepositoryIdInBranch(repositoryEntity);
        entityManager.persist(branch);
        entityManager.flush();
        BranchEntity expected = entityManager.find(BranchEntity.class, branchId);
        assertEquals(branch.getBranchId(), expected.getBranchId());
    }

    @Test
     void testBranchEntityDelete() {
        int repositoryId = 1;
        BranchId branchId = new BranchId(repositoryId, "master");
        repositoryEntity.setRepositoryId(repositoryId);
        repositoryEntity.setRepositoryName("alex_repo");
        branch.setBranchId(branchId);
        branch.setRepositoryIdInBranch(repositoryEntity);
        entityManager.persist(repositoryEntity);
        entityManager.persist(branch);
        entityManager.flush();
        BranchEntity expected = entityManager.find(BranchEntity.class, branchId);
        assertNotNull(expected);
        assertEquals(branchId, expected.getBranchId());
        assertEquals(repositoryEntity.getRepositoryName(), expected.getRepositoryIdInBranch().getRepositoryName());
        entityManager.remove(expected);
        entityManager.flush();
        assertNull(entityManager.find(BranchEntity.class, branchId));
    }

    @Test
     void testBranchEntityUpdate() {
        int repositoryId = 1;
        BranchId branchId = new BranchId(repositoryId, "master");
        repositoryEntity.setRepositoryId(repositoryId);
        repositoryEntity.setRepositoryName("alex_repo");
        BranchEntity branch = new BranchEntity();
        branch.setBranchId(branchId);
        branch.setRepositoryIdInBranch(repositoryEntity);
        entityManager.persist(repositoryEntity);
        entityManager.persist(branch);
        entityManager.flush();
        BranchEntity foundBranch = entityManager.find(BranchEntity.class, branchId);
        assertNotNull(foundBranch);
        assertEquals(branchId, foundBranch.getBranchId());
        assertEquals(repositoryEntity.getRepositoryName(), foundBranch.getRepositoryIdInBranch().getRepositoryName());
        repositoryEntity.setRepositoryName("newRepoName");
        entityManager.flush();
        BranchEntity updatedBranch = entityManager.find(BranchEntity.class, branchId);
        assertNotNull(updatedBranch);
        assertEquals("newRepoName", updatedBranch.getRepositoryIdInBranch().getRepositoryName());
    }

}
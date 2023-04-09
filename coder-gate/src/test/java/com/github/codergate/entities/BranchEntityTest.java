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
    private final BranchEntity branchEntity = new BranchEntity();

    int repositoryId = 1;

    @Test
     void testAllArgsConstructor() {
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
        branchEntity.setBranchId(branchId);
        branchEntity.setRepositoryIdInBranch(repositoryEntity);
        entityManager.persist(branchEntity);
        entityManager.flush();
        BranchEntity expected = entityManager.find(BranchEntity.class, branchId);
        assertEquals(branchEntity.getBranchId(), expected.getBranchId());
    }


    @Test
    void testBranchEntityDelete() {
        // Arrange
        repositoryEntity.setRepositoryId(repositoryId);
        repositoryEntity.setRepositoryName("alex_repo");
        entityManager.persist(repositoryEntity);
        BranchId branchId = new BranchId(repositoryId, "master");
        branchEntity.setBranchId(branchId);
        branchEntity.setRepositoryIdInBranch(repositoryEntity);
        entityManager.persist(branchEntity);
        entityManager.remove(branchEntity);
        assertNull(entityManager.find(BranchEntity.class, branchId));
    }



    @Test
    void testBranchEntityUpdate() {
        repositoryEntity.setRepositoryId(repositoryId);
        repositoryEntity.setRepositoryName("alex_repo");
        entityManager.persist(repositoryEntity);
        BranchId branchId = new BranchId(repositoryId, "master");
        branchEntity.setBranchId(branchId);
        branchEntity.setRepositoryIdInBranch(repositoryEntity);
        entityManager.persist(branchEntity);
        repositoryEntity.setRepositoryName("newRepoName");
        entityManager.flush();
        BranchEntity updatedBranch = entityManager.find(BranchEntity.class, branchId);
        assertEquals("newRepoName", updatedBranch.getRepositoryIdInBranch().getRepositoryName());
    }


}
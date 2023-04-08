package com.github.codergate.entities;

import com.github.codergate.repositories.TagRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TagEntityTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    TagRepository tagRepository;

    private final TagEntity tagEntity = new TagEntity();
    private final RepositoryEntity repositoryEntity = new RepositoryEntity();

    String repositoryName = "alex_Repository";
    String tagUrl = "http://testtag//abc";


    @Test
    void testTagEntityPersisted() {

        repositoryEntity.setRepositoryName(repositoryName);
        TagId tagId = new TagId(repositoryEntity.getRepositoryId(),tagUrl);
        tagEntity.setTagId(tagId);
        tagEntity.setRepositoryIdInTag(repositoryEntity);
        entityManager.persist(repositoryEntity);
        entityManager.persist(tagEntity);
        entityManager.flush();
        TagEntity expected = entityManager.find(TagEntity.class, tagId);
        assertEquals(tagId, expected.getTagId());
    }


    @Test
    void testUpdateTagEntity() {
        TagEntity tagEntity;
        repositoryEntity.setRepositoryName(repositoryName);
        entityManager.persist(repositoryEntity);
        TagId tagId = new TagId(repositoryEntity.getRepositoryId(), tagUrl);
        tagEntity = new TagEntity(tagId, repositoryEntity);
        TagId newTagId = new TagId(tagEntity.getTagId().getRepositoryId(), "http://testtag//abccc");
        tagEntity.setTagId(newTagId);
        entityManager.merge(tagEntity);
        entityManager.flush();
        TagEntity updatedTagEntity = entityManager.find(TagEntity.class, newTagId);
        assertEquals(newTagId, updatedTagEntity.getTagId());
    }

    @Test
    void testDeleteTagEntity() {
        TagEntity tagEntity;
        repositoryEntity.setRepositoryName(repositoryName);
        entityManager.persist(repositoryEntity);
        TagId tagId = new TagId(repositoryEntity.getRepositoryId(), tagUrl);
        tagEntity = new TagEntity(tagId, repositoryEntity);
        entityManager.remove(tagEntity);
        entityManager.flush();
        TagEntity deletedTagEntity = entityManager.find(TagEntity.class, tagEntity.getTagId());
        assertNull(deletedTagEntity);
    }

    @Test
    void testAllArgsConstructor() {
        TagId mockTagId = new TagId();
        RepositoryEntity mockRepoEntity = new RepositoryEntity();
        TagEntity tagEntity = new TagEntity(mockTagId, mockRepoEntity);
        assertEquals(mockRepoEntity, tagEntity.getRepositoryIdInTag());
    }

}
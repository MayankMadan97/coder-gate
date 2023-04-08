package com.github.codergate.entities;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TagIdTest {

    int repositoryId = 1;
    String tagUrl = "http://example.com/tag";
    @Test
    void testTagId() {
        TagId tagId = new TagId(repositoryId, tagUrl);
        TagEntity tagEntity = new TagEntity(tagId, null);
        assertThat(tagEntity.getTagId()).isEqualTo(tagId);
    }
    @Test
    void testRepositoryIdInTag() {
        RepositoryEntity repositoryEntity = new RepositoryEntity();
        repositoryEntity.setRepositoryId(repositoryId);
        TagId tagId = new TagId(repositoryId, tagUrl);
        TagEntity tagEntity = new TagEntity(tagId, repositoryEntity);
        assertThat(tagEntity.getTagId()).isEqualTo(tagId);
    }



}
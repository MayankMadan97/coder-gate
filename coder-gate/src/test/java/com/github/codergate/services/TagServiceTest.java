package com.github.codergate.services;

import com.github.codergate.entities.RepositoryEntity;
import com.github.codergate.entities.TagEntity;
import com.github.codergate.entities.TagId;
import com.github.codergate.repositories.TagRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {
    private final String tagURL = "tag1";
    private final int repositoryID = 101;

    @Mock
    TagRepository tagRepositoryMock;
    @InjectMocks
    TagService tagServiceMock;

    @Test
    void addTagWithAllData() {
        TagId tagId = new TagId(repositoryID, tagURL);
        RepositoryEntity repositoryEntity = new RepositoryEntity();
        repositoryEntity.setRepositoryId(repositoryID);
        TagEntity expected = new TagEntity(tagId, repositoryEntity);

        ArgumentCaptor<TagEntity> entityCaptor = ArgumentCaptor.forClass(TagEntity.class);
        when(tagRepositoryMock.save(entityCaptor.capture())).thenReturn(expected);

        tagServiceMock.addTag(tagURL, repositoryID);

        verify(tagRepositoryMock).save(entityCaptor.getValue());
        TagEntity actual = entityCaptor.getValue();

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void addTagWithNullData() {
        String nullTagURL = null;
        int nullRepositoryID = 0;

        TagId tagId = new TagId();
        RepositoryEntity repositoryEntity = new RepositoryEntity();
        TagEntity expected = new TagEntity(tagId, repositoryEntity);

        ArgumentCaptor<TagEntity> entityCaptor = ArgumentCaptor.forClass(TagEntity.class);
        when(tagRepositoryMock.save(entityCaptor.capture())).thenReturn(expected);

        tagServiceMock.addTag(nullTagURL, nullRepositoryID);

        verify(tagRepositoryMock).save(entityCaptor.getValue());
        TagEntity actual = entityCaptor.getValue();

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void addTagWithNullTagURL() {
        String nullTagURL = null;

        TagId tagId = new TagId();
        tagId.setRepositoryId(repositoryID);
        RepositoryEntity repositoryEntity = new RepositoryEntity();
        repositoryEntity.setRepositoryId(repositoryID);
        TagEntity expected = new TagEntity(tagId, repositoryEntity);

        ArgumentCaptor<TagEntity> entityCaptor = ArgumentCaptor.forClass(TagEntity.class);
        when(tagRepositoryMock.save(entityCaptor.capture())).thenReturn(expected);

        tagServiceMock.addTag(nullTagURL, repositoryID);

        verify(tagRepositoryMock).save(entityCaptor.getValue());
        TagEntity actual = entityCaptor.getValue();

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void addTagWithNullRepositoryID() {
        int nullRepositoryID = 0;

        TagId tagId = new TagId();
        tagId.setTagUrl(tagURL);
        RepositoryEntity repositoryEntity = new RepositoryEntity();
        TagEntity expected = new TagEntity(tagId, repositoryEntity);

        ArgumentCaptor<TagEntity> entityCaptor = ArgumentCaptor.forClass(TagEntity.class);
        when(tagRepositoryMock.save(entityCaptor.capture())).thenReturn(expected);

        tagServiceMock.addTag(tagURL, nullRepositoryID);

        verify(tagRepositoryMock).save(entityCaptor.getValue());
        TagEntity actual = entityCaptor.getValue();

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
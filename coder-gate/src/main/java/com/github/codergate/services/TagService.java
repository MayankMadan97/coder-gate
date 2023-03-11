package com.github.codergate.services;

import com.github.codergate.dto.push.RepositoryDTO;
import com.github.codergate.entities.TagEntity;
import com.github.codergate.entities.TagId;
import com.github.codergate.repositories.TagRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    TagRepository tagRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(WebHookListenerService.class);

    public RepositoryDTO createTag(RepositoryDTO repository) {
        RepositoryDTO repositoryDTO;
        TagEntity tagEntity = dtoToEntity(repository);
        TagEntity saveEntity = tagRepository.save(tagEntity);
        LOGGER.info("TagService : The tag information is added");
        repositoryDTO = entityToDto(saveEntity);
        return repositoryDTO;
    }

    private TagEntity dtoToEntity(RepositoryDTO repositoryDTO) {
        TagEntity tagEntity = null;
        if(repositoryDTO != null)
        {
            tagEntity = new TagEntity();
            if(repositoryDTO.getTagsUrl() != null && repositoryDTO.getId() != null)
            {
                TagId tagId = new TagId(repositoryDTO.getId(), repositoryDTO.getTagsUrl());
                tagEntity.setTagId(tagId);
            }
            LOGGER.info("TagService : Repository DTO has been converted to Tag Entity");
        } else {
            LOGGER.warn("TagService : Repository value is null");
        }
        return tagEntity;
    }

    private RepositoryDTO entityToDto(TagEntity tag) {
        RepositoryDTO repository = null;
        if(tag != null)
        {
            repository = new RepositoryDTO();
            if(tag.getTagId() != null)
            {
                TagId tagIdObject = tag.getTagId();
                repository.setId(tagIdObject.getRepositoryId());
                repository.setTagsUrl(tagIdObject.getTagUrl());
            }
            LOGGER.info("TagService : Tag Entity has been converted to RepositoryDTO");
        } else {
            LOGGER.warn("TagService : Tag value is null");
        }
        return repository;
    }
}

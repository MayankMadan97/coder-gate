package com.github.codergate.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.codergate.entities.RepositoryEntity;
import com.github.codergate.entities.TagEntity;
import com.github.codergate.entities.TagId;
import com.github.codergate.repositories.TagRepository;

@Service
public class TagService {

    @Autowired
    TagRepository tagRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(TagService.class);

    /***
     * adds tag information to table
     * 
     * @return RepositoryDTO object
     */
    public void addTag(String tagsUrl, Integer repositoryId) {
        TagEntity tagEntity = convertDTOToEntity(tagsUrl, repositoryId);
        if (tagEntity != null) {
            TagEntity savedEntity = tagRepository.save(tagEntity);
            LOGGER.info("addTag : The tag information is added {}", savedEntity);
        }
    }

    /***
     * converts RepositoryDTO to Tag Entity
     * 
     * @return Tag Entity
     */
    private TagEntity convertDTOToEntity(String tagUrls, Integer id) {
        TagEntity tagEntity = null;
        tagEntity = new TagEntity();
        TagId tagId = new TagId(id, tagUrls);
        RepositoryEntity repositoryEntity = new RepositoryEntity();
        repositoryEntity.setRepositoryId(id);
        tagEntity.setTagId(tagId);
        tagEntity.setRepositoryIdInTag(repositoryEntity);
        return tagEntity;
    }
}

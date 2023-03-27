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
    private static final Logger LOGGER = LoggerFactory.getLogger(TagService.class);

    /***
     * adds tag information to table
     * @return RepositoryDTO object
     */
    public void addTag(String tagsUrl, Integer repositoryId) {
        TagEntity tagEntity = convertDTOToEntity(tagsUrl,repositoryId);
        if(tagEntity!=null) {
            TagEntity savedEntity = tagRepository.save(tagEntity);
            LOGGER.info("addTag : The tag information is added {}", savedEntity);
        }
    }

    /***
     * converts RepositoryDTO to Tag Entity
     * @return Tag Entity
     */
    private TagEntity convertDTOToEntity(String tagUrls, Integer id) {
        TagEntity tagEntity = null;
        tagEntity = new TagEntity();
        TagId tagId = new TagId(id, tagUrls);
        tagEntity.setTagId(tagId);
        return tagEntity;
    }

    /***
     * Converts Tag entity to RepositoryDTO
     * @param tag Tag Entity
     * @return RepositoryDTO Object
     */
    private RepositoryDTO convertEntityToDTO(TagEntity tag) {
        RepositoryDTO repositoryDTO = null;
        if(tag != null)
        {
            repositoryDTO = new RepositoryDTO();
            if(tag.getTagId() != null)
            {
                TagId tagIdObject = tag.getTagId();
                repositoryDTO.setId(tagIdObject.getRepositoryId());
                repositoryDTO.setTagsUrl(tagIdObject.getTagUrl());
            }
            LOGGER.info("ConvertEntityToDto : Tag Entity has been converted to RepositoryDTO {}", repositoryDTO);
        } else {
            LOGGER.warn("ConvertEntityToDto : Tag entity value is null");
        }
        return repositoryDTO;
    }
}

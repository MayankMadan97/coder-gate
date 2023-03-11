package com.github.codergate.repositories;

import com.github.codergate.entities.TagEntity;
import com.github.codergate.entities.TagId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagEntity, TagId> {
}

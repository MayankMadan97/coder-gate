package com.github.codergate.repositories;

import com.github.codergate.entities.RepositoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<RepositoryEntity, Integer> {
}

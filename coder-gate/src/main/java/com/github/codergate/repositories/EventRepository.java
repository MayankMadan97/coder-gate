package com.github.codergate.repositories;

import com.github.codergate.entities.EventEntity;
import com.github.codergate.entities.RepositoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface EventRepository extends JpaRepository<EventEntity, Integer> {
    @Query(value = "SELECT e.user_id FROM event e WHERE e.repository_id =:repoId ",nativeQuery = true)
    List<Long> findUsersByRepoId(@Param("repoId")int repoId);
    @Query(value = "SELECT * FROM event e WHERE e.repository_id =:repoId ",nativeQuery = true)
    List<EventEntity> findEventsByRepoId(@Param("repoId")int repoId);

    @Query(value = "SELECT * FROM event e WHERE e.user_id =:userId ",nativeQuery = true)
    List<EventEntity> findEventsByUserId(@Param("userId")long userId);
}

package com.github.codergate.repositories;

import com.github.codergate.entities.RepositoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@org.springframework.stereotype.Repository
public interface RepositoryRepository extends JpaRepository<RepositoryEntity, Integer> {

    @Query(value = "SELECT * FROM repository r WHERE r.user_id =:userID ",nativeQuery = true)
    List<RepositoryEntity> findByUserId(@Param("userID")long userId);

}

package com.github.codergate.repositories;
import com.github.codergate.entities.ThresholdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ThresholdRepository extends JpaRepository<ThresholdEntity, Integer> {

    @Query(value = "SELECT * FROM threshold t WHERE t.repository_id =:repositoryID ;",nativeQuery = true)
    ThresholdEntity findByRepositoryId(@Param("repositoryID") int repositoryId);

}

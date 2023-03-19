package com.github.codergate.repositories;
import com.github.codergate.entities.AnalysisEntity;
import com.github.codergate.entities.ThresholdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ThresholdRepository extends JpaRepository<ThresholdEntity, Integer> {

    @Query("SELECT t FROM ThresholdEntity t WHERE t.repositoryIdInThreshold.id = :repositoryID")
    ThresholdEntity findByRepositoryId(@Param("repositoryID") int repositoryId);

}

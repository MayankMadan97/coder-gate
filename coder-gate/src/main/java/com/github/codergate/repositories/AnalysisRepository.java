package com.github.codergate.repositories;

import com.github.codergate.entities.AnalysisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnalysisRepository  extends JpaRepository<AnalysisEntity, Integer> {
    @Query("SELECT a FROM AnalysisEntity a WHERE a.repositoryID = :repositoryId ")
    AnalysisEntity findAnalysisByRepositoryId(@Param("repositoryId") Integer repositoryId);
}

package com.github.codergate.repositories;

import com.github.codergate.entities.AnalysisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnalysisRepository extends JpaRepository<AnalysisEntity, Integer> {
    @Query(value = "SELECT * FROM analysis a WHERE a.repository_id =:repositoryId", nativeQuery = true)
    AnalysisEntity findAnalysisByRepositoryId(@Param("repositoryId") Integer repositoryId);

    @Query(value = "SELECT * FROM analysis a WHERE a.repository_id =:repositoryID AND a.timestamp = (SELECT MAX(a.timestamp) FROM analysis a WHERE a.repository_id =:repositoryID)", nativeQuery = true)
    AnalysisEntity findLatestAnalysisByRepositoryId(@Param("repositoryID") int repositoryId);

    @Query(value = "SELECT * FROM analysis a WHERE a.repository_id = :repositoryID and a.branch_Id = :branchId ", nativeQuery = true)
    List<AnalysisEntity> findAnalysisByRepositoryAndBranchId(@Param("repositoryID") int repositoryId, @Param("branchId") String branchId);

}

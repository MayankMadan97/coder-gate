package com.github.codergate.repositories;

import com.github.codergate.entities.AnalysisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnalysisRepository  extends JpaRepository<AnalysisEntity, Integer> {
<<<<<<< HEAD
    @Query("SELECT a FROM AnalysisEntity a WHERE a.repositoryID = :repositoryId ")
    AnalysisEntity findAnalysisByRepositoryId(@Param("repositoryId") Integer repositoryId);
=======

    @Query(value = "SELECT * FROM analysis a WHERE a.repository_id =:repositoryID AND a.timestamp = (SELECT MAX(a.timestamp) FROM analysis a WHERE a.repository_id =:repositoryID)", nativeQuery = true)
    AnalysisEntity findLatestAnalysisByRepositoryId(@Param("repositoryID") int repositoryId);

//    @Query(value = "DELETE FROM analysis a WHERE a.repository_id = :repositoryID")
//    AnalysisEntity deleteByRepositoryId(@Param("repositoryID") int repositoryId);
>>>>>>> f79a1113bffc2b1a2a0c59dae712fe48151abdd0
}

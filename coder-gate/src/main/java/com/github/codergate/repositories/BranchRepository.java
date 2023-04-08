package com.github.codergate.repositories;

import com.github.codergate.entities.BranchEntity;
import com.github.codergate.entities.BranchId;
import com.github.codergate.entities.RepositoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BranchRepository extends JpaRepository<BranchEntity, BranchId> {
    @Query(value = "SELECT branch_url FROM branch t WHERE t.repository_id =:repoId ",nativeQuery = true)
    List<String> findByRepoId(@Param("repoId")int repId);
}

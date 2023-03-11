package com.github.codergate.repositories;

import com.github.codergate.entities.BranchEntity;
import com.github.codergate.entities.BranchId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<BranchEntity, BranchId> {
}

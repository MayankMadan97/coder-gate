package com.github.codergate.repositories;

import com.github.codergate.entities.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<BranchEntity, String> {
}

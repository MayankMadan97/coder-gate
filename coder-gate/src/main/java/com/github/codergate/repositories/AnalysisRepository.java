package com.github.codergate.repositories;

import com.github.codergate.entities.AnalysisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalysisRepository  extends JpaRepository<AnalysisEntity, Integer> {
}

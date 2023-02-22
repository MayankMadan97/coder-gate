package com.github.codergate.repositories;

import com.github.codergate.entities.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebhookListenerRepository extends JpaRepository<Analysis, Integer> {
}

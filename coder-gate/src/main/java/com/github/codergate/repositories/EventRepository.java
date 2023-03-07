package com.github.codergate.repositories;

import com.github.codergate.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventRepository extends JpaRepository<EventEntity, Integer> {
}

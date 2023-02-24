package com.github.codergate.repositories;

import com.github.codergate.entities.Analysis;
import com.github.codergate.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public interface WebhookListenerRepository extends JpaRepository<com.github.codergate.entities.Repository, Integer> {
}

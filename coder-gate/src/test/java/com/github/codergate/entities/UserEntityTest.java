package com.github.codergate.entities;

import com.github.codergate.repositories.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserEntityTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;


    UserEntity userEntity = new UserEntity();
    RepositoryEntity repositoryEntity = new RepositoryEntity();
    EventEntity eventEntity = new EventEntity();
    private final List<RepositoryEntity> repositoryEntities = new ArrayList<>();
    private final Set<EventEntity> eventEntities = new HashSet<>();



    int userId =11;
    int repositoryId =123;

    String email = "test@gmail.com";

    @Test
    void testUserEntityPersisted() {
        userEntity.setUserName("alex");
        userEntity.setUserId(userId);
        userEntity.setEmail(email);
        entityManager.persist(userEntity);
        entityManager.flush();
        UserEntity expected = entityManager.find(UserEntity.class, userEntity.getUserId());
        assertEquals(userEntity.getUserName(), expected.getUserName());
    }


    @Test
    void testRepositoryEntityPersisted() {
        repositoryEntity.setRepositoryId(repositoryId);
        repositoryEntity.setRepositoryName("alexrepo");
        entityManager.persist(repositoryEntity);
        entityManager.flush();
        RepositoryEntity expected = entityManager.find(RepositoryEntity.class, repositoryEntity.getRepositoryId());
        assertEquals(repositoryEntity.getRepositoryName(), expected.getRepositoryName());
    }

    @Test
    void testEventEntityPersisted() {
        eventEntity.setEventName("add");
        entityManager.persist(eventEntity);
        entityManager.flush();
        EventEntity expected = entityManager.find(EventEntity.class, eventEntity.getEventId());
        assertEquals(eventEntity.getEventName(), expected.getEventName());
    }

    @Test
    void testAllArgsConstructor() {
        long userId = 1;
        String userName = "alex";
        UserEntity user = new UserEntity(userId, userName, email, repositoryEntities, eventEntities);
        assertEquals(userId, user.getUserId());;
    }

    @Test
    void testDeleteUserEntity() {
        userEntity.setUserId(1L);
        userEntity.setUserName("alex");
        userEntity.setEmail(email);
        userEntity = userRepository.save(userEntity);
        userRepository.delete(userEntity);
        Optional<UserEntity> deletedUser = userRepository.findById(userEntity.getUserId());
        assertFalse(deletedUser.isPresent());
    }

    @Test
    void testDeleteUserEntityById() {
        long userId =1;
        userEntity.setUserId(userId);
        userEntity.setUserName("testuser");
        userEntity.setEmail(email);
        userEntity = userRepository.save(userEntity);
        userRepository.deleteById(userEntity.getUserId());
        Optional<UserEntity> deletedUser = userRepository.findById(userEntity.getUserId());
        assertFalse(deletedUser.isPresent());
    }

    @Test
    void testFindByUserId() {
        long userId = 1;
        userEntity.setUserId(userId);
        userEntity.setUserName("test_user");
        userEntity.setEmail(email);
        repositoryEntity.setRepositoryId(repositoryId);
        repositoryEntity.setRepositoryName("alex_repository");
        repositoryEntity.setFork(false);
        repositoryEntity.setUserEntity(userEntity);
        eventEntity.setEventName("test_event");
        eventEntity.setCommitId("111234");
        eventEntity.setCommitMessage("adding file");
        eventEntity.setUserIdInEvent(userEntity);
        eventEntity.setRepositoryIdInEvent(repositoryEntity);
        repositoryEntities.add(repositoryEntity);
        userEntity.setRepositoryEntity(repositoryEntities);
        eventEntities.add(eventEntity);
        userEntity.setUserAndEvent(eventEntities);
        entityManager.persist(userEntity);
        entityManager.flush();
        Optional<UserEntity> expected = userRepository.findById(userId);
        assertTrue(expected.isPresent());
        UserEntity retrievedUser = expected.get();
        assertEquals("test_user", retrievedUser.getUserName());
        List<RepositoryEntity> retrievedRepositories = retrievedUser.getRepositoryEntity();
        assertEquals(1, retrievedRepositories.size());
        RepositoryEntity retrievedRepository = retrievedRepositories.get(0);
        assertEquals("alex_repository", retrievedRepository.getRepositoryName());
        Set<EventEntity> retrievedEvents = retrievedUser.getUserAndEvent();
        assertEquals(1, retrievedEvents.size());
        EventEntity retrievedEvent = retrievedEvents.iterator().next();
        assertEquals("test_event", retrievedEvent.getEventName());
    }


}


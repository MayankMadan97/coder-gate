package com.github.codergate.entities;

import com.github.codergate.repositories.EventRepository;
import com.github.codergate.repositories.RepositoryRepository;
import com.github.codergate.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EventEntityTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    EventRepository eventRepository;

    @Autowired
    private RepositoryRepository repositoryRepository;

    @Autowired
    private UserRepository userRepository;

    private final EventEntity eventEntity = new EventEntity();
    private final UserEntity userEntity =new UserEntity();
    private final RepositoryEntity repositoryEntity =new RepositoryEntity();

    long eventId = 1;
    int userId =12;
    int repositoryId =123;

    @Test
    void testDeleteEventEntity() {
        eventEntity.setEventName("test event");
        eventEntity.setCommitId("12345");
        eventEntity.setCommitMessage("test commit message");
        entityManager.persist(eventEntity);
        entityManager.flush();
        EventEntity retrievedEvent = entityManager.find(EventEntity.class, eventEntity.getEventId());
        entityManager.remove(retrievedEvent);
        entityManager.flush();
        EventEntity deletedEvent = entityManager.find(EventEntity.class, eventEntity.getEventId());
        assertNull(deletedEvent);
    }

    @Test
    void testAllArgsConstructor() {
        String eventName = "push";
        String commitId = "1223";
        String commitMessage = "created file";
        userEntity.setUserId(userId);
        repositoryEntity.setRepositoryId(repositoryId);
        EventEntity event = new EventEntity(eventId, eventName, commitId, commitMessage, userEntity,repositoryEntity);
        assertEquals(eventId, event.getEventId());
    }


    @Test
    void testEventEntityPersistence() {
        userEntity.setUserName("alex");
        entityManager.persist(userEntity);
        repositoryEntity.setRepositoryName("alex_repo");
        entityManager.persist(repositoryEntity);
        eventEntity.setEventName("check");
        eventEntity.setCommitId("test_commit");
        eventEntity.setCommitMessage("test_message");
        eventEntity.setUserIdInEvent(userEntity);
        eventEntity.setRepositoryIdInEvent(repositoryEntity);
        entityManager.persist(eventEntity);
        entityManager.flush();
        entityManager.clear();
        EventEntity retrievedEvent = entityManager.find(EventEntity.class, eventEntity.getEventId());
        assertNotNull(retrievedEvent);
        assertEquals(eventEntity.getEventName(), retrievedEvent.getEventName());
        assertEquals(eventEntity.getCommitId(), retrievedEvent.getCommitId());
        assertEquals(eventEntity.getCommitMessage(), retrievedEvent.getCommitMessage());
        assertNotNull(retrievedEvent.getUserIdInEvent());
        assertEquals(userEntity.getUserId(), retrievedEvent.getUserIdInEvent().getUserId());
        assertEquals(userEntity.getUserName(), retrievedEvent.getUserIdInEvent().getUserName());
        assertNotNull(retrievedEvent.getRepositoryIdInEvent());
        assertEquals(repositoryEntity.getRepositoryId(), retrievedEvent.getRepositoryIdInEvent().getRepositoryId());
        assertEquals(repositoryEntity.getRepositoryName(), retrievedEvent.getRepositoryIdInEvent().getRepositoryName());
    }

}








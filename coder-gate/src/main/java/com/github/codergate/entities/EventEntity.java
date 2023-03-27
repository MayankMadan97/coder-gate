package com.github.codergate.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Event")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long eventId;
    private String eventName;
    private String commitId;
    private String commitMessage;
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity userIdInEvent;

    @ManyToOne
    @JoinColumn(name = "repositoryId")
    private RepositoryEntity repositoryIdInEvent;

}

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
    private int eventID;
    private String eventName;
    private int commitID;
    private String commitMessage;
    @ManyToOne
    @JoinColumn(name = "userID")
    private UserEntity u2;

    @ManyToOne
    @JoinColumn(name = "repositoryID")
    private RepositoryEntity r2;

}

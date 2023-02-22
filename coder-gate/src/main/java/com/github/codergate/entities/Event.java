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
public class Event {

    @Id
    private int eventID;
    private String eventName;
    private int commitID;
    private String commitMessage;
    @ManyToOne
    @JoinColumn(name = "userID")
    private User u2;

    @ManyToOne
    @JoinColumn(name = "repositoryID")
    private Repository r2;

}

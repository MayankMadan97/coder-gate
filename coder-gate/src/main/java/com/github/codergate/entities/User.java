package com.github.codergate.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private int userId;
    private String userName;
    private String email;
    private String organization;
    private String role;
    private String collaborators;

    @ManyToOne
    @JoinColumn(name = "repositoryId")
    private Repository r1;

    @OneToMany(mappedBy = "u2", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Event> e1;

}


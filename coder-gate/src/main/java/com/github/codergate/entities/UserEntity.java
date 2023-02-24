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
public class UserEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long userId;
    private String userName;
    private String email;

    @ManyToOne
    @JoinColumn(name = "repositoryId")
    private RepositoryEntity r1;

    @OneToMany(mappedBy = "u2", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EventEntity> e1;

}


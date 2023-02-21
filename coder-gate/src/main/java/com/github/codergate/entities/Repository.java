package com.github.codergate.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "repository")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Repository {
    @Id
    private int repositoryId;
    private String repositoryName;
    private String fork;

    @OneToMany(mappedBy = "r1", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> u1;

    @OneToMany(mappedBy = "b", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Branch> branches = new ArrayList<>();
    @OneToMany(mappedBy = "t", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "r2", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Event> e2;

}
package com.github.codergate.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tag")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagEntity {
    @EmbeddedId
    @Column(name = "tagId")
    private TagId tagId;

    @ManyToOne
    @JoinColumn(name = "repositoryId", insertable = false, updatable = false)
    private  RepositoryEntity repositoryIdInTag;
}

package com.github.codergate.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    private long userId;
    private String userName;
    private String email;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
   private List<RepositoryEntity> repositoryEntity;



   @OneToMany(mappedBy = "userIdInEvent", cascade = CascadeType.ALL)
    private Set<EventEntity> userAndEvent;


    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }

}


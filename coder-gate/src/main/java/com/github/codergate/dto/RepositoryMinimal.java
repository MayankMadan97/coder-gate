package com.github.codergate.dto;

import com.github.codergate.dto.installation.RepositoriesAddedDTO;

public class RepositoryMinimal extends RepositoriesAddedDTO {

    private long timestamp;

    String health;

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public RepositoryMinimal(Integer id, String name, long timestamp, String health) {
        super(id, name);
        this.timestamp = timestamp;
        this.health = health;
    }

    public RepositoryMinimal(){}

    public RepositoryMinimal(long timestamp) {
        this.timestamp = timestamp;
    }
}

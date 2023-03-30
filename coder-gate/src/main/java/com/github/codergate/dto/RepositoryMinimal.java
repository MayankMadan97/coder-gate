package com.github.codergate.dto;

import com.github.codergate.dto.installation.RepositoriesAddedDTO;

public class RepositoryMinimal extends RepositoriesAddedDTO {

    private long timestamp;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public RepositoryMinimal(Integer id, String name, long timestamp) {
        super(id, name);
        this.timestamp = timestamp;
    }

    public RepositoryMinimal(long timestamp) {
        this.timestamp = timestamp;
    }
}

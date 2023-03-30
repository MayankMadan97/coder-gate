
package com.github.codergate.dto.installation;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RepositoriesAddedDTO implements Serializable {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("node_id")
    private String nodeId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("full_name")
    private String fullName;
    @JsonProperty("private")
    private Boolean _private;
    @JsonIgnore
    private static final long serialVersionUID = -4726706613928656811L;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    public RepositoriesAddedDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public RepositoriesAddedDTO() {
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public RepositoriesAddedDTO withId(Integer id) {
        this.id = id;
        return this;
    }

    @JsonProperty("node_id")
    public String getNodeId() {
        return nodeId;
    }

    @JsonProperty("node_id")
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public RepositoriesAddedDTO withNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public RepositoriesAddedDTO withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("full_name")
    public String getFullName() {
        return fullName;
    }

    @JsonProperty("full_name")
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public RepositoriesAddedDTO withFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @JsonProperty("private")
    public Boolean getPrivate() {
        return _private;
    }

    @JsonProperty("private")
    public void setPrivate(Boolean _private) {
        this._private = _private;
    }

    public RepositoriesAddedDTO withPrivate(Boolean _private) {
        this._private = _private;
        return this;
    }

}

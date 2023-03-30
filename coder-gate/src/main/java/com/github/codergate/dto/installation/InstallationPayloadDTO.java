
package com.github.codergate.dto.installation;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InstallationPayloadDTO implements Serializable {

    @JsonProperty("action")
    private String action;
    @JsonProperty("installation")
    private Installation installation;
    @JsonProperty("repository_selection")
    private String repositorySelection;
    @JsonProperty("repositories_added")
    private List<RepositoriesAddedDTO> repositoriesAddedDto;
    @JsonProperty("repositories_removed")
    private List<Object> repositoriesRemoved;

    @JsonProperty("repositories")
    private List<RepositoriesDTO> repositories;
    @JsonProperty("requester")
    private Object requester;
    @JsonProperty("sender")
    private Sender sender;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<>();
    private static final long serialVersionUID = -7622948711784252212L;

    /**
     * No args constructor for use in serialization
     *
     */
    public InstallationPayloadDTO() {
    }

    /**
     *
     * @param requester
     * @param sender
     * @param repositorySelection
     * @param installation
     * @param action
     * @param repositoriesAddedDto
     * @param repositoriesRemoved
     */
    public InstallationPayloadDTO(String action, Installation installation, String repositorySelection,
            List<RepositoriesAddedDTO> repositoriesAddedDto, List<Object> repositoriesRemoved, Object requester,
            Sender sender) {
        super();
        this.action = action;
        this.installation = installation;
        this.repositorySelection = repositorySelection;
        this.repositoriesAddedDto = repositoriesAddedDto;
        this.repositoriesRemoved = repositoriesRemoved;
        this.requester = requester;
        this.sender = sender;
    }

    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    @JsonProperty("action")
    public void setAction(String action) {
        this.action = action;
    }

    public InstallationPayloadDTO withAction(String action) {
        this.action = action;
        return this;
    }

    @JsonProperty("installation")
    public Installation getInstallation() {
        return installation;
    }

    @JsonProperty("installation")
    public void setInstallation(Installation installation) {
        this.installation = installation;
    }

    public InstallationPayloadDTO withInstallation(Installation installation) {
        this.installation = installation;
        return this;
    }

    @JsonProperty("repository_selection")
    public String getRepositorySelection() {
        return repositorySelection;
    }

    @JsonProperty("repository_selection")
    public void setRepositorySelection(String repositorySelection) {
        this.repositorySelection = repositorySelection;
    }

    public InstallationPayloadDTO withRepositorySelection(String repositorySelection) {
        this.repositorySelection = repositorySelection;
        return this;
    }

    @JsonProperty("repositories_added")
    public List<RepositoriesAddedDTO> getRepositoriesAdded() {
        return repositoriesAddedDto;
    }

    @JsonProperty("repositories_added")
    public void setRepositoriesAdded(List<RepositoriesAddedDTO> repositoriesAddedDto) {
        this.repositoriesAddedDto = repositoriesAddedDto;
    }

    public InstallationPayloadDTO withRepositoriesAdded(List<RepositoriesAddedDTO> repositoriesAddedDto) {
        this.repositoriesAddedDto = repositoriesAddedDto;
        return this;
    }

    @JsonProperty("repositories_created")
    public List<RepositoriesDTO> getRepositories() {
        return repositories;
    }

    @JsonProperty("repositories_created")
    public void setRepositories(List<RepositoriesDTO> repositories) {
        this.repositories = repositories;
    }

    public InstallationPayloadDTO withRepositoriesCreated(List<RepositoriesDTO> repositories) {
        this.repositories = repositories;
        return this;
    }

    @JsonProperty("repositories_removed")
    public List<Object> getRepositoriesRemoved() {
        return repositoriesRemoved;
    }

    @JsonProperty("repositories_removed")
    public void setRepositoriesRemoved(List<Object> repositoriesRemoved) {
        this.repositoriesRemoved = repositoriesRemoved;
    }

    public InstallationPayloadDTO withRepositoriesRemoved(List<Object> repositoriesRemoved) {
        this.repositoriesRemoved = repositoriesRemoved;
        return this;
    }

    @JsonProperty("requester")
    public Object getRequester() {
        return requester;
    }

    @JsonProperty("requester")
    public void setRequester(Object requester) {
        this.requester = requester;
    }

    public InstallationPayloadDTO withRequester(Object requester) {
        this.requester = requester;
        return this;
    }

    @JsonProperty("sender")
    public Sender getSender() {
        return sender;
    }

    @JsonProperty("sender")
    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public InstallationPayloadDTO withSender(Sender sender) {
        this.sender = sender;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public InstallationPayloadDTO withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}

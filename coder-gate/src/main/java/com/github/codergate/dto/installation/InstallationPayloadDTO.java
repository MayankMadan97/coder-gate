
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
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "action",
        "installation",
        "repository_selection",
        "repositories_added",
        "repositories_removed",
        "repositories",
        "requester",
        "sender"
})
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
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = -7622948711784252212L;

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
    public List<RepositoriesDTO> getRepositories (){
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(InstallationPayloadDTO.class.getName()).append('@')
                .append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("action");
        sb.append('=');
        sb.append(((this.action == null) ? "<null>" : this.action));
        sb.append(',');
        sb.append("installation");
        sb.append('=');
        sb.append(((this.installation == null) ? "<null>" : this.installation));
        sb.append(',');
        sb.append("repositorySelection");
        sb.append('=');
        sb.append(((this.repositorySelection == null) ? "<null>" : this.repositorySelection));
        sb.append(',');
        sb.append("repositoriesAdded");
        sb.append('=');
        sb.append(((this.repositoriesAddedDto == null) ? "<null>" : this.repositoriesAddedDto));
        sb.append(',');
        sb.append("repositories");
        sb.append('=');
        sb.append(((this.repositories == null) ? "<null>" : this.repositories));
        sb.append(',');
        sb.append("repositoriesRemoved");
        sb.append('=');
        sb.append(((this.repositoriesRemoved == null) ? "<null>" : this.repositoriesRemoved));
        sb.append(',');
        sb.append("requester");
        sb.append('=');
        sb.append(((this.requester == null) ? "<null>" : this.requester));
        sb.append(',');
        sb.append("sender");
        sb.append('=');
        sb.append(((this.sender == null) ? "<null>" : this.sender));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null) ? "<null>" : this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result * 31) + ((this.requester == null) ? 0 : this.requester.hashCode()));
        result = ((result * 31) + ((this.sender == null) ? 0 : this.sender.hashCode()));
        result = ((result * 31) + ((this.repositorySelection == null) ? 0 : this.repositorySelection.hashCode()));
        result = ((result * 31) + ((this.installation == null) ? 0 : this.installation.hashCode()));
        result = ((result * 31) + ((this.action == null) ? 0 : this.action.hashCode()));
        result = ((result * 31) + ((this.additionalProperties == null) ? 0 : this.additionalProperties.hashCode()));
        result = ((result * 31) + ((this.repositoriesAddedDto == null) ? 0 : this.repositoriesAddedDto.hashCode()));
        result = ((result * 31) + ((this.repositories == null) ? 0 : this.repositories.hashCode()));
        result = ((result * 31) + ((this.repositoriesRemoved == null) ? 0 : this.repositoriesRemoved.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof InstallationPayloadDTO) == false) {
            return false;
        }
        InstallationPayloadDTO rhs = ((InstallationPayloadDTO) other);
        return (((((((((this.requester == rhs.requester)
                || ((this.requester != null) && this.requester.equals(rhs.requester)))
                && ((this.sender == rhs.sender) || ((this.sender != null) && this.sender.equals(rhs.sender))))
                && ((this.repositorySelection == rhs.repositorySelection) || ((this.repositorySelection != null)
                        && this.repositorySelection.equals(rhs.repositorySelection))))
                && ((this.installation == rhs.installation)
                        || ((this.installation != null) && this.installation.equals(rhs.installation))))
                && ((this.action == rhs.action) || ((this.action != null) && this.action.equals(rhs.action))))
                && ((this.additionalProperties == rhs.additionalProperties) || ((this.additionalProperties != null)
                        && this.additionalProperties.equals(rhs.additionalProperties))))
                && ((this.repositoriesAddedDto == rhs.repositoriesAddedDto)
                        || ((this.repositoriesAddedDto != null) && this.repositoriesAddedDto.equals(rhs.repositoriesAddedDto))))
                && ((this.repositories == rhs.repositories)
                || ((this.repositories != null) && this.repositories.equals(rhs.repositories))))
                && ((this.repositoriesRemoved == rhs.repositoriesRemoved) || ((this.repositoriesRemoved != null)
                        && this.repositoriesRemoved.equals(rhs.repositoriesRemoved)));
    }

}


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
        "requester",
        "sender"
})
public class InstallationPayload implements Serializable {

    @JsonProperty("action")
    private String action;
    @JsonProperty("installation")
    private Installation installation;
    @JsonProperty("repository_selection")
    private String repositorySelection;
    @JsonProperty("repositories_added")
    private List<RepositoriesAdded> repositoriesAdded;
    @JsonProperty("repositories_removed")
    private List<Object> repositoriesRemoved;
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
    public InstallationPayload() {
    }

    /**
     * 
     * @param requester
     * @param sender
     * @param repositorySelection
     * @param installation
     * @param action
     * @param repositoriesAdded
     * @param repositoriesRemoved
     */
    public InstallationPayload(String action, Installation installation, String repositorySelection,
            List<RepositoriesAdded> repositoriesAdded, List<Object> repositoriesRemoved, Object requester,
            Sender sender) {
        super();
        this.action = action;
        this.installation = installation;
        this.repositorySelection = repositorySelection;
        this.repositoriesAdded = repositoriesAdded;
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

    public InstallationPayload withAction(String action) {
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

    public InstallationPayload withInstallation(Installation installation) {
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

    public InstallationPayload withRepositorySelection(String repositorySelection) {
        this.repositorySelection = repositorySelection;
        return this;
    }

    @JsonProperty("repositories_added")
    public List<RepositoriesAdded> getRepositoriesAdded() {
        return repositoriesAdded;
    }

    @JsonProperty("repositories_added")
    public void setRepositoriesAdded(List<RepositoriesAdded> repositoriesAdded) {
        this.repositoriesAdded = repositoriesAdded;
    }

    public InstallationPayload withRepositoriesAdded(List<RepositoriesAdded> repositoriesAdded) {
        this.repositoriesAdded = repositoriesAdded;
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

    public InstallationPayload withRepositoriesRemoved(List<Object> repositoriesRemoved) {
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

    public InstallationPayload withRequester(Object requester) {
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

    public InstallationPayload withSender(Sender sender) {
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

    public InstallationPayload withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(InstallationPayload.class.getName()).append('@')
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
        sb.append(((this.repositoriesAdded == null) ? "<null>" : this.repositoriesAdded));
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
        result = ((result * 31) + ((this.repositoriesAdded == null) ? 0 : this.repositoriesAdded.hashCode()));
        result = ((result * 31) + ((this.repositoriesRemoved == null) ? 0 : this.repositoriesRemoved.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof InstallationPayload) == false) {
            return false;
        }
        InstallationPayload rhs = ((InstallationPayload) other);
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
                && ((this.repositoriesAdded == rhs.repositoriesAdded)
                        || ((this.repositoriesAdded != null) && this.repositoriesAdded.equals(rhs.repositoriesAdded))))
                && ((this.repositoriesRemoved == rhs.repositoriesRemoved) || ((this.repositoriesRemoved != null)
                        && this.repositoriesRemoved.equals(rhs.repositoriesRemoved))));
    }

}


package com.github.codergate.dto.installation;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "checks",
        "issues",
        "actions",
        "contents",
        "metadata",
        "workflows",
        "pull_requests",
        "security_events",
        "repository_hooks"
})
public class Permissions implements Serializable {

    @JsonProperty("checks")
    private String checks;
    @JsonProperty("issues")
    private String issues;
    @JsonProperty("actions")
    private String actions;
    @JsonProperty("contents")
    private String contents;
    @JsonProperty("metadata")
    private String metadata;
    @JsonProperty("workflows")
    private String workflows;
    @JsonProperty("pull_requests")
    private String pullRequests;
    @JsonProperty("security_events")
    private String securityEvents;
    @JsonProperty("repository_hooks")
    private String repositoryHooks;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = -9076112883664283050L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Permissions() {
    }

    /**
     * 
     * @param securityEvents
     * @param metadata
     * @param checks
     * @param contents
     * @param workflows
     * @param pullRequests
     * @param issues
     * @param actions
     * @param repositoryHooks
     */
    public Permissions(String checks, String issues, String actions, String contents, String metadata, String workflows,
            String pullRequests, String securityEvents, String repositoryHooks) {
        super();
        this.checks = checks;
        this.issues = issues;
        this.actions = actions;
        this.contents = contents;
        this.metadata = metadata;
        this.workflows = workflows;
        this.pullRequests = pullRequests;
        this.securityEvents = securityEvents;
        this.repositoryHooks = repositoryHooks;
    }

    @JsonProperty("checks")
    public String getChecks() {
        return checks;
    }

    @JsonProperty("checks")
    public void setChecks(String checks) {
        this.checks = checks;
    }

    public Permissions withChecks(String checks) {
        this.checks = checks;
        return this;
    }

    @JsonProperty("issues")
    public String getIssues() {
        return issues;
    }

    @JsonProperty("issues")
    public void setIssues(String issues) {
        this.issues = issues;
    }

    public Permissions withIssues(String issues) {
        this.issues = issues;
        return this;
    }

    @JsonProperty("actions")
    public String getActions() {
        return actions;
    }

    @JsonProperty("actions")
    public void setActions(String actions) {
        this.actions = actions;
    }

    public Permissions withActions(String actions) {
        this.actions = actions;
        return this;
    }

    @JsonProperty("contents")
    public String getContents() {
        return contents;
    }

    @JsonProperty("contents")
    public void setContents(String contents) {
        this.contents = contents;
    }

    public Permissions withContents(String contents) {
        this.contents = contents;
        return this;
    }

    @JsonProperty("metadata")
    public String getMetadata() {
        return metadata;
    }

    @JsonProperty("metadata")
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public Permissions withMetadata(String metadata) {
        this.metadata = metadata;
        return this;
    }

    @JsonProperty("workflows")
    public String getWorkflows() {
        return workflows;
    }

    @JsonProperty("workflows")
    public void setWorkflows(String workflows) {
        this.workflows = workflows;
    }

    public Permissions withWorkflows(String workflows) {
        this.workflows = workflows;
        return this;
    }

    @JsonProperty("pull_requests")
    public String getPullRequests() {
        return pullRequests;
    }

    @JsonProperty("pull_requests")
    public void setPullRequests(String pullRequests) {
        this.pullRequests = pullRequests;
    }

    public Permissions withPullRequests(String pullRequests) {
        this.pullRequests = pullRequests;
        return this;
    }

    @JsonProperty("security_events")
    public String getSecurityEvents() {
        return securityEvents;
    }

    @JsonProperty("security_events")
    public void setSecurityEvents(String securityEvents) {
        this.securityEvents = securityEvents;
    }

    public Permissions withSecurityEvents(String securityEvents) {
        this.securityEvents = securityEvents;
        return this;
    }

    @JsonProperty("repository_hooks")
    public String getRepositoryHooks() {
        return repositoryHooks;
    }

    @JsonProperty("repository_hooks")
    public void setRepositoryHooks(String repositoryHooks) {
        this.repositoryHooks = repositoryHooks;
    }

    public Permissions withRepositoryHooks(String repositoryHooks) {
        this.repositoryHooks = repositoryHooks;
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

    public Permissions withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Permissions.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
                .append('[');
        sb.append("checks");
        sb.append('=');
        sb.append(((this.checks == null) ? "<null>" : this.checks));
        sb.append(',');
        sb.append("issues");
        sb.append('=');
        sb.append(((this.issues == null) ? "<null>" : this.issues));
        sb.append(',');
        sb.append("actions");
        sb.append('=');
        sb.append(((this.actions == null) ? "<null>" : this.actions));
        sb.append(',');
        sb.append("contents");
        sb.append('=');
        sb.append(((this.contents == null) ? "<null>" : this.contents));
        sb.append(',');
        sb.append("metadata");
        sb.append('=');
        sb.append(((this.metadata == null) ? "<null>" : this.metadata));
        sb.append(',');
        sb.append("workflows");
        sb.append('=');
        sb.append(((this.workflows == null) ? "<null>" : this.workflows));
        sb.append(',');
        sb.append("pullRequests");
        sb.append('=');
        sb.append(((this.pullRequests == null) ? "<null>" : this.pullRequests));
        sb.append(',');
        sb.append("securityEvents");
        sb.append('=');
        sb.append(((this.securityEvents == null) ? "<null>" : this.securityEvents));
        sb.append(',');
        sb.append("repositoryHooks");
        sb.append('=');
        sb.append(((this.repositoryHooks == null) ? "<null>" : this.repositoryHooks));
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
        result = ((result * 31) + ((this.securityEvents == null) ? 0 : this.securityEvents.hashCode()));
        result = ((result * 31) + ((this.metadata == null) ? 0 : this.metadata.hashCode()));
        result = ((result * 31) + ((this.checks == null) ? 0 : this.checks.hashCode()));
        result = ((result * 31) + ((this.contents == null) ? 0 : this.contents.hashCode()));
        result = ((result * 31) + ((this.workflows == null) ? 0 : this.workflows.hashCode()));
        result = ((result * 31) + ((this.pullRequests == null) ? 0 : this.pullRequests.hashCode()));
        result = ((result * 31) + ((this.additionalProperties == null) ? 0 : this.additionalProperties.hashCode()));
        result = ((result * 31) + ((this.issues == null) ? 0 : this.issues.hashCode()));
        result = ((result * 31) + ((this.actions == null) ? 0 : this.actions.hashCode()));
        result = ((result * 31) + ((this.repositoryHooks == null) ? 0 : this.repositoryHooks.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Permissions) == false) {
            return false;
        }
        Permissions rhs = ((Permissions) other);
        return (((((((((((this.securityEvents == rhs.securityEvents)
                || ((this.securityEvents != null) && this.securityEvents.equals(rhs.securityEvents)))
                && ((this.metadata == rhs.metadata) || ((this.metadata != null) && this.metadata.equals(rhs.metadata))))
                && ((this.checks == rhs.checks) || ((this.checks != null) && this.checks.equals(rhs.checks))))
                && ((this.contents == rhs.contents) || ((this.contents != null) && this.contents.equals(rhs.contents))))
                && ((this.workflows == rhs.workflows)
                        || ((this.workflows != null) && this.workflows.equals(rhs.workflows))))
                && ((this.pullRequests == rhs.pullRequests)
                        || ((this.pullRequests != null) && this.pullRequests.equals(rhs.pullRequests))))
                && ((this.additionalProperties == rhs.additionalProperties) || ((this.additionalProperties != null)
                        && this.additionalProperties.equals(rhs.additionalProperties))))
                && ((this.issues == rhs.issues) || ((this.issues != null) && this.issues.equals(rhs.issues))))
                && ((this.actions == rhs.actions) || ((this.actions != null) && this.actions.equals(rhs.actions))))
                && ((this.repositoryHooks == rhs.repositoryHooks)
                        || ((this.repositoryHooks != null) && this.repositoryHooks.equals(rhs.repositoryHooks))));
    }

}

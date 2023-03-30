
package com.github.codergate.dto.installation;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
    private Map<String, Object> additionalProperties = new LinkedHashMap<>();
    private static final long serialVersionUID = -9076112883664283050L;

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

}

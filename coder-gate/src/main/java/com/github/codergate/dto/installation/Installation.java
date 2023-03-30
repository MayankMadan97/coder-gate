
package com.github.codergate.dto.installation;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Installation implements Serializable {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("account")
    private AccountDTO accountDto;
    @JsonProperty("repository_selection")
    private String repositorySelection;
    @JsonProperty("access_tokens_url")
    private String accessTokensUrl;
    @JsonProperty("repositories_url")
    private String repositoriesUrl;
    @JsonProperty("html_url")
    private String htmlUrl;
    @JsonProperty("app_id")
    private Integer appId;
    @JsonProperty("app_slug")
    private String appSlug;
    @JsonProperty("target_id")
    private Integer targetId;
    @JsonProperty("target_type")
    private String targetType;
    @JsonProperty("permissions")
    private Permissions permissions;
    @JsonProperty("events")
    private List<String> events;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("single_file_name")
    private Object singleFileName;
    @JsonProperty("has_multiple_single_files")
    private Boolean hasMultipleSingleFiles;
    @JsonProperty("single_file_paths")
    private List<Object> singleFilePaths;
    @JsonProperty("suspended_by")
    private Object suspendedBy;
    @JsonProperty("suspended_at")
    private Object suspendedAt;
    private static final long serialVersionUID = -4502662394749074220L;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public Installation withId(Integer id) {
        this.id = id;
        return this;
    }

    @JsonProperty("account")
    public AccountDTO getAccount() {
        return accountDto;
    }

    @JsonProperty("account")
    public void setAccount(AccountDTO accountDto) {
        this.accountDto = accountDto;
    }

    public Installation withAccount(AccountDTO accountDto) {
        this.accountDto = accountDto;
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

    public Installation withRepositorySelection(String repositorySelection) {
        this.repositorySelection = repositorySelection;
        return this;
    }

    @JsonProperty("access_tokens_url")
    public String getAccessTokensUrl() {
        return accessTokensUrl;
    }

    @JsonProperty("access_tokens_url")
    public void setAccessTokensUrl(String accessTokensUrl) {
        this.accessTokensUrl = accessTokensUrl;
    }

    public Installation withAccessTokensUrl(String accessTokensUrl) {
        this.accessTokensUrl = accessTokensUrl;
        return this;
    }

    @JsonProperty("repositories_url")
    public String getRepositoriesUrl() {
        return repositoriesUrl;
    }

    @JsonProperty("repositories_url")
    public void setRepositoriesUrl(String repositoriesUrl) {
        this.repositoriesUrl = repositoriesUrl;
    }

    public Installation withRepositoriesUrl(String repositoriesUrl) {
        this.repositoriesUrl = repositoriesUrl;
        return this;
    }

    @JsonProperty("html_url")
    public String getHtmlUrl() {
        return htmlUrl;
    }

    @JsonProperty("html_url")
    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public Installation withHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
        return this;
    }

    @JsonProperty("app_id")
    public Integer getAppId() {
        return appId;
    }

    @JsonProperty("app_id")
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Installation withAppId(Integer appId) {
        this.appId = appId;
        return this;
    }

    @JsonProperty("app_slug")
    public String getAppSlug() {
        return appSlug;
    }

    @JsonProperty("app_slug")
    public void setAppSlug(String appSlug) {
        this.appSlug = appSlug;
    }

    public Installation withAppSlug(String appSlug) {
        this.appSlug = appSlug;
        return this;
    }

    @JsonProperty("target_id")
    public Integer getTargetId() {
        return targetId;
    }

    @JsonProperty("target_id")
    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Installation withTargetId(Integer targetId) {
        this.targetId = targetId;
        return this;
    }

    @JsonProperty("target_type")
    public String getTargetType() {
        return targetType;
    }

    @JsonProperty("target_type")
    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public Installation withTargetType(String targetType) {
        this.targetType = targetType;
        return this;
    }

    @JsonProperty("permissions")
    public Permissions getPermissions() {
        return permissions;
    }

    @JsonProperty("permissions")
    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    public Installation withPermissions(Permissions permissions) {
        this.permissions = permissions;
        return this;
    }

    @JsonProperty("events")
    public List<String> getEvents() {
        return events;
    }

    @JsonProperty("events")
    public void setEvents(List<String> events) {
        this.events = events;
    }

    public Installation withEvents(List<String> events) {
        this.events = events;
        return this;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Installation withCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Installation withUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @JsonProperty("single_file_name")
    public Object getSingleFileName() {
        return singleFileName;
    }

    @JsonProperty("single_file_name")
    public void setSingleFileName(Object singleFileName) {
        this.singleFileName = singleFileName;
    }

    public Installation withSingleFileName(Object singleFileName) {
        this.singleFileName = singleFileName;
        return this;
    }

    @JsonProperty("has_multiple_single_files")
    public Boolean getHasMultipleSingleFiles() {
        return hasMultipleSingleFiles;
    }

    @JsonProperty("has_multiple_single_files")
    public void setHasMultipleSingleFiles(Boolean hasMultipleSingleFiles) {
        this.hasMultipleSingleFiles = hasMultipleSingleFiles;
    }

    public Installation withHasMultipleSingleFiles(Boolean hasMultipleSingleFiles) {
        this.hasMultipleSingleFiles = hasMultipleSingleFiles;
        return this;
    }

    @JsonProperty("single_file_paths")
    public List<Object> getSingleFilePaths() {
        return singleFilePaths;
    }

    @JsonProperty("single_file_paths")
    public void setSingleFilePaths(List<Object> singleFilePaths) {
        this.singleFilePaths = singleFilePaths;
    }

    public Installation withSingleFilePaths(List<Object> singleFilePaths) {
        this.singleFilePaths = singleFilePaths;
        return this;
    }

    @JsonProperty("suspended_by")
    public Object getSuspendedBy() {
        return suspendedBy;
    }

    @JsonProperty("suspended_by")
    public void setSuspendedBy(Object suspendedBy) {
        this.suspendedBy = suspendedBy;
    }

    public Installation withSuspendedBy(Object suspendedBy) {
        this.suspendedBy = suspendedBy;
        return this;
    }

    @JsonProperty("suspended_at")
    public Object getSuspendedAt() {
        return suspendedAt;
    }

    @JsonProperty("suspended_at")
    public void setSuspendedAt(Object suspendedAt) {
        this.suspendedAt = suspendedAt;
    }

    public Installation withSuspendedAt(Object suspendedAt) {
        this.suspendedAt = suspendedAt;
        return this;
    }

}

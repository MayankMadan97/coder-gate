
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
        "id",
        "account",
        "repository_selection",
        "access_tokens_url",
        "repositories_url",
        "html_url",
        "app_id",
        "app_slug",
        "target_id",
        "target_type",
        "permissions",
        "events",
        "created_at",
        "updated_at",
        "single_file_name",
        "has_multiple_single_files",
        "single_file_paths",
        "suspended_by",
        "suspended_at"
})
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
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = -4502662394749074220L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Installation() {
    }

    /**
     * 
     * @param repositoriesUrl
     * @param appSlug
     * @param accessTokensUrl
     * @param targetId
     * @param repositorySelection
     * @param suspendedAt
     * @param htmlUrl
     * @param targetType
     * @param singleFilePaths
     * @param createdAt
     * @param permissions
     * @param appId
     * @param id
     * @param suspendedBy
     * @param singleFileName
     * @param accountDto
     * @param events
     * @param updatedAt
     * @param hasMultipleSingleFiles
     */
    public Installation(Integer id, AccountDTO accountDto, String repositorySelection, String accessTokensUrl,
                        String repositoriesUrl, String htmlUrl, Integer appId, String appSlug, Integer targetId, String targetType,
                        Permissions permissions, List<String> events, String createdAt, String updatedAt, Object singleFileName,
                        Boolean hasMultipleSingleFiles, List<Object> singleFilePaths, Object suspendedBy, Object suspendedAt) {
        super();
        this.id = id;
        this.accountDto = accountDto;
        this.repositorySelection = repositorySelection;
        this.accessTokensUrl = accessTokensUrl;
        this.repositoriesUrl = repositoriesUrl;
        this.htmlUrl = htmlUrl;
        this.appId = appId;
        this.appSlug = appSlug;
        this.targetId = targetId;
        this.targetType = targetType;
        this.permissions = permissions;
        this.events = events;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.singleFileName = singleFileName;
        this.hasMultipleSingleFiles = hasMultipleSingleFiles;
        this.singleFilePaths = singleFilePaths;
        this.suspendedBy = suspendedBy;
        this.suspendedAt = suspendedAt;
    }

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

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Installation withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Installation.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
                .append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null) ? "<null>" : this.id));
        sb.append(',');
        sb.append("account");
        sb.append('=');
        sb.append(((this.accountDto == null) ? "<null>" : this.accountDto));
        sb.append(',');
        sb.append("repositorySelection");
        sb.append('=');
        sb.append(((this.repositorySelection == null) ? "<null>" : this.repositorySelection));
        sb.append(',');
        sb.append("accessTokensUrl");
        sb.append('=');
        sb.append(((this.accessTokensUrl == null) ? "<null>" : this.accessTokensUrl));
        sb.append(',');
        sb.append("repositoriesUrl");
        sb.append('=');
        sb.append(((this.repositoriesUrl == null) ? "<null>" : this.repositoriesUrl));
        sb.append(',');
        sb.append("htmlUrl");
        sb.append('=');
        sb.append(((this.htmlUrl == null) ? "<null>" : this.htmlUrl));
        sb.append(',');
        sb.append("appId");
        sb.append('=');
        sb.append(((this.appId == null) ? "<null>" : this.appId));
        sb.append(',');
        sb.append("appSlug");
        sb.append('=');
        sb.append(((this.appSlug == null) ? "<null>" : this.appSlug));
        sb.append(',');
        sb.append("targetId");
        sb.append('=');
        sb.append(((this.targetId == null) ? "<null>" : this.targetId));
        sb.append(',');
        sb.append("targetType");
        sb.append('=');
        sb.append(((this.targetType == null) ? "<null>" : this.targetType));
        sb.append(',');
        sb.append("permissions");
        sb.append('=');
        sb.append(((this.permissions == null) ? "<null>" : this.permissions));
        sb.append(',');
        sb.append("events");
        sb.append('=');
        sb.append(((this.events == null) ? "<null>" : this.events));
        sb.append(',');
        sb.append("createdAt");
        sb.append('=');
        sb.append(((this.createdAt == null) ? "<null>" : this.createdAt));
        sb.append(',');
        sb.append("updatedAt");
        sb.append('=');
        sb.append(((this.updatedAt == null) ? "<null>" : this.updatedAt));
        sb.append(',');
        sb.append("singleFileName");
        sb.append('=');
        sb.append(((this.singleFileName == null) ? "<null>" : this.singleFileName));
        sb.append(',');
        sb.append("hasMultipleSingleFiles");
        sb.append('=');
        sb.append(((this.hasMultipleSingleFiles == null) ? "<null>" : this.hasMultipleSingleFiles));
        sb.append(',');
        sb.append("singleFilePaths");
        sb.append('=');
        sb.append(((this.singleFilePaths == null) ? "<null>" : this.singleFilePaths));
        sb.append(',');
        sb.append("suspendedBy");
        sb.append('=');
        sb.append(((this.suspendedBy == null) ? "<null>" : this.suspendedBy));
        sb.append(',');
        sb.append("suspendedAt");
        sb.append('=');
        sb.append(((this.suspendedAt == null) ? "<null>" : this.suspendedAt));
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
        result = ((result * 31) + ((this.repositoriesUrl == null) ? 0 : this.repositoriesUrl.hashCode()));
        result = ((result * 31) + ((this.appSlug == null) ? 0 : this.appSlug.hashCode()));
        result = ((result * 31) + ((this.accessTokensUrl == null) ? 0 : this.accessTokensUrl.hashCode()));
        result = ((result * 31) + ((this.targetId == null) ? 0 : this.targetId.hashCode()));
        result = ((result * 31) + ((this.repositorySelection == null) ? 0 : this.repositorySelection.hashCode()));
        result = ((result * 31) + ((this.suspendedAt == null) ? 0 : this.suspendedAt.hashCode()));
        result = ((result * 31) + ((this.htmlUrl == null) ? 0 : this.htmlUrl.hashCode()));
        result = ((result * 31) + ((this.targetType == null) ? 0 : this.targetType.hashCode()));
        result = ((result * 31) + ((this.singleFilePaths == null) ? 0 : this.singleFilePaths.hashCode()));
        result = ((result * 31) + ((this.createdAt == null) ? 0 : this.createdAt.hashCode()));
        result = ((result * 31) + ((this.permissions == null) ? 0 : this.permissions.hashCode()));
        result = ((result * 31) + ((this.appId == null) ? 0 : this.appId.hashCode()));
        result = ((result * 31) + ((this.id == null) ? 0 : this.id.hashCode()));
        result = ((result * 31) + ((this.suspendedBy == null) ? 0 : this.suspendedBy.hashCode()));
        result = ((result * 31) + ((this.additionalProperties == null) ? 0 : this.additionalProperties.hashCode()));
        result = ((result * 31) + ((this.singleFileName == null) ? 0 : this.singleFileName.hashCode()));
        result = ((result * 31) + ((this.accountDto == null) ? 0 : this.accountDto.hashCode()));
        result = ((result * 31) + ((this.events == null) ? 0 : this.events.hashCode()));
        result = ((result * 31) + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode()));
        result = ((result * 31) + ((this.hasMultipleSingleFiles == null) ? 0 : this.hasMultipleSingleFiles.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Installation) == false) {
            return false;
        }
        Installation rhs = ((Installation) other);
        return (((((((((((((((((((((this.repositoriesUrl == rhs.repositoriesUrl)
                || ((this.repositoriesUrl != null) && this.repositoriesUrl.equals(rhs.repositoriesUrl)))
                && ((this.appSlug == rhs.appSlug) || ((this.appSlug != null) && this.appSlug.equals(rhs.appSlug))))
                && ((this.accessTokensUrl == rhs.accessTokensUrl)
                        || ((this.accessTokensUrl != null) && this.accessTokensUrl.equals(rhs.accessTokensUrl))))
                && ((this.targetId == rhs.targetId) || ((this.targetId != null) && this.targetId.equals(rhs.targetId))))
                && ((this.repositorySelection == rhs.repositorySelection) || ((this.repositorySelection != null)
                        && this.repositorySelection.equals(rhs.repositorySelection))))
                && ((this.suspendedAt == rhs.suspendedAt)
                        || ((this.suspendedAt != null) && this.suspendedAt.equals(rhs.suspendedAt))))
                && ((this.htmlUrl == rhs.htmlUrl) || ((this.htmlUrl != null) && this.htmlUrl.equals(rhs.htmlUrl))))
                && ((this.targetType == rhs.targetType)
                        || ((this.targetType != null) && this.targetType.equals(rhs.targetType))))
                && ((this.singleFilePaths == rhs.singleFilePaths)
                        || ((this.singleFilePaths != null) && this.singleFilePaths.equals(rhs.singleFilePaths))))
                && ((this.createdAt == rhs.createdAt)
                        || ((this.createdAt != null) && this.createdAt.equals(rhs.createdAt))))
                && ((this.permissions == rhs.permissions)
                        || ((this.permissions != null) && this.permissions.equals(rhs.permissions))))
                && ((this.appId == rhs.appId) || ((this.appId != null) && this.appId.equals(rhs.appId))))
                && ((this.id == rhs.id) || ((this.id != null) && this.id.equals(rhs.id))))
                && ((this.suspendedBy == rhs.suspendedBy)
                        || ((this.suspendedBy != null) && this.suspendedBy.equals(rhs.suspendedBy))))
                && ((this.additionalProperties == rhs.additionalProperties) || ((this.additionalProperties != null)
                        && this.additionalProperties.equals(rhs.additionalProperties))))
                && ((this.singleFileName == rhs.singleFileName)
                        || ((this.singleFileName != null) && this.singleFileName.equals(rhs.singleFileName))))
                && ((this.accountDto == rhs.accountDto) || ((this.accountDto != null) && this.accountDto.equals(rhs.accountDto))))
                && ((this.events == rhs.events) || ((this.events != null) && this.events.equals(rhs.events))))
                && ((this.updatedAt == rhs.updatedAt)
                        || ((this.updatedAt != null) && this.updatedAt.equals(rhs.updatedAt))))
                && ((this.hasMultipleSingleFiles == rhs.hasMultipleSingleFiles)
                        || ((this.hasMultipleSingleFiles != null)
                                && this.hasMultipleSingleFiles.equals(rhs.hasMultipleSingleFiles))));
    }

}

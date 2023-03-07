
package com.github.codergate.dto.push;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "node_id",
    "name",
    "full_name",
    "private",
    "owner",
    "html_url",
    "description",
    "fork",
    "url",
    "forks_url",
    "keys_url",
    "collaborators_url",
    "teams_url",
    "hooks_url",
    "issue_events_url",
    "events_url",
    "assignees_url",
    "branches_url",
    "tags_url",
    "blobs_url",
    "git_tags_url",
    "git_refs_url",
    "trees_url",
    "statuses_url",
    "languages_url",
    "stargazers_url",
    "contributors_url",
    "subscribers_url",
    "subscription_url",
    "commits_url",
    "git_commits_url",
    "comments_url",
    "issue_comment_url",
    "contents_url",
    "compare_url",
    "merges_url",
    "archive_url",
    "downloads_url",
    "issues_url",
    "pulls_url",
    "milestones_url",
    "notifications_url",
    "labels_url",
    "releases_url",
    "deployments_url",
    "created_at",
    "updated_at",
    "pushed_at",
    "git_url",
    "ssh_url",
    "clone_url",
    "svn_url",
    "homepage",
    "size",
    "stargazers_count",
    "watchers_count",
    "language",
    "has_issues",
    "has_projects",
    "has_downloads",
    "has_wiki",
    "has_pages",
    "has_discussions",
    "forks_count",
    "mirror_url",
    "archived",
    "disabled",
    "open_issues_count",
    "license",
    "allow_forking",
    "is_template",
    "web_commit_signoff_required",
    "topics",
    "visibility",
    "forks",
    "open_issues",
    "watchers",
    "default_branch",
    "stargazers",
    "master_branch"
})
@Generated("jsonschema2pojo")
public class RepositoryDTO implements Serializable
{

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
    @JsonProperty("owner")
    private OwnerDTO ownerDTO;
    @JsonProperty("html_url")
    private String htmlUrl;
    @JsonProperty("description")
    private Object description;
    @JsonProperty("fork")
    private Boolean fork;
    @JsonProperty("url")
    private String url;
    @JsonProperty("forks_url")
    private String forksUrl;
    @JsonProperty("keys_url")
    private String keysUrl;
    @JsonProperty("collaborators_url")
    private String collaboratorsUrl;
    @JsonProperty("teams_url")
    private String teamsUrl;
    @JsonProperty("hooks_url")
    private String hooksUrl;
    @JsonProperty("issue_events_url")
    private String issueEventsUrl;
    @JsonProperty("events_url")
    private String eventsUrl;
    @JsonProperty("assignees_url")
    private String assigneesUrl;
    @JsonProperty("branches_url")
    private String branchesUrl;
    @JsonProperty("tags_url")
    private String tagsUrl;
    @JsonProperty("blobs_url")
    private String blobsUrl;
    @JsonProperty("git_tags_url")
    private String gitTagsUrl;
    @JsonProperty("git_refs_url")
    private String gitRefsUrl;
    @JsonProperty("trees_url")
    private String treesUrl;
    @JsonProperty("statuses_url")
    private String statusesUrl;
    @JsonProperty("languages_url")
    private String languagesUrl;
    @JsonProperty("stargazers_url")
    private String stargazersUrl;
    @JsonProperty("contributors_url")
    private String contributorsUrl;
    @JsonProperty("subscribers_url")
    private String subscribersUrl;
    @JsonProperty("subscription_url")
    private String subscriptionUrl;
    @JsonProperty("commits_url")
    private String commitsUrl;
    @JsonProperty("git_commits_url")
    private String gitCommitsUrl;
    @JsonProperty("comments_url")
    private String commentsUrl;
    @JsonProperty("issue_comment_url")
    private String issueCommentUrl;
    @JsonProperty("contents_url")
    private String contentsUrl;
    @JsonProperty("compare_url")
    private String compareUrl;
    @JsonProperty("merges_url")
    private String mergesUrl;
    @JsonProperty("archive_url")
    private String archiveUrl;
    @JsonProperty("downloads_url")
    private String downloadsUrl;
    @JsonProperty("issues_url")
    private String issuesUrl;
    @JsonProperty("pulls_url")
    private String pullsUrl;
    @JsonProperty("milestones_url")
    private String milestonesUrl;
    @JsonProperty("notifications_url")
    private String notificationsUrl;
    @JsonProperty("labels_url")
    private String labelsUrl;
    @JsonProperty("releases_url")
    private String releasesUrl;
    @JsonProperty("deployments_url")
    private String deploymentsUrl;
    @JsonProperty("created_at")
    private Integer createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("pushed_at")
    private Integer pushedAt;
    @JsonProperty("git_url")
    private String gitUrl;
    @JsonProperty("ssh_url")
    private String sshUrl;
    @JsonProperty("clone_url")
    private String cloneUrl;
    @JsonProperty("svn_url")
    private String svnUrl;
    @JsonProperty("homepage")
    private Object homepage;
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("stargazers_count")
    private Integer stargazersCount;
    @JsonProperty("watchers_count")
    private Integer watchersCount;
    @JsonProperty("language")
    private Object language;
    @JsonProperty("has_issues")
    private Boolean hasIssues;
    @JsonProperty("has_projects")
    private Boolean hasProjects;
    @JsonProperty("has_downloads")
    private Boolean hasDownloads;
    @JsonProperty("has_wiki")
    private Boolean hasWiki;
    @JsonProperty("has_pages")
    private Boolean hasPages;
    @JsonProperty("has_discussions")
    private Boolean hasDiscussions;
    @JsonProperty("forks_count")
    private Integer forksCount;
    @JsonProperty("mirror_url")
    private Object mirrorUrl;
    @JsonProperty("archived")
    private Boolean archived;
    @JsonProperty("disabled")
    private Boolean disabled;
    @JsonProperty("open_issues_count")
    private Integer openIssuesCount;
    @JsonProperty("license")
    private Object license;
    @JsonProperty("allow_forking")
    private Boolean allowForking;
    @JsonProperty("is_template")
    private Boolean isTemplate;
    @JsonProperty("web_commit_signoff_required")
    private Boolean webCommitSignoffRequired;
    @JsonProperty("topics")
    private List<Object> topics;
    @JsonProperty("visibility")
    private String visibility;
    @JsonProperty("forks")
    private Integer forks;
    @JsonProperty("open_issues")
    private Integer openIssues;
    @JsonProperty("watchers")
    private Integer watchers;
    @JsonProperty("default_branch")
    private String defaultBranch;
    @JsonProperty("stargazers")
    private Integer stargazers;
    @JsonProperty("master_branch")
    private String masterBranch;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = -5066551299210491337L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RepositoryDTO() {
    }

    /**
     * 
     * @param sshUrl
     * @param archiveUrl
     * @param languagesUrl
     * @param language
     * @param assigneesUrl
     * @param commitsUrl
     * @param openIssues
     * @param cloneUrl
     * @param forksCount
     * @param subscribersUrl
     * @param createdAt
     * @param forksUrl
     * @param watchersCount
     * @param _private
     * @param masterBranch
     * @param issueCommentUrl
     * @param statusesUrl
     * @param id
     * @param collaboratorsUrl
     * @param updatedAt
     * @param forks
     * @param allowForking
     * @param labelsUrl
     * @param visibility
     * @param defaultBranch
     * @param keysUrl
     * @param downloadsUrl
     * @param contentsUrl
     * @param pushedAt
     * @param tagsUrl
     * @param license
     * @param commentsUrl
     * @param size
     * @param isTemplate
     * @param treesUrl
     * @param name
     * @param mergesUrl
     * @param nodeId
     * @param teamsUrl
     * @param blobsUrl
     * @param issueEventsUrl
     * @param hasPages
     * @param milestonesUrl
     * @param issuesUrl
     * @param webCommitSignoffRequired
     * @param releasesUrl
     * @param description
     * @param watchers
     * @param branchesUrl
     * @param contributorsUrl
     * @param gitRefsUrl
     * @param hooksUrl
     * @param openIssuesCount
     * @param archived
     * @param stargazersCount
     * @param disabled
     * @param hasIssues
     * @param ownerDTO
     * @param hasWiki
     * @param compareUrl
     * @param gitCommitsUrl
     * @param topics
     * @param htmlUrl
     * @param stargazersUrl
     * @param fullName
     * @param svnUrl
     * @param hasDiscussions
     * @param url
     * @param pullsUrl
     * @param mirrorUrl
     * @param hasDownloads
     * @param fork
     * @param stargazers
     * @param hasProjects
     * @param deploymentsUrl
     * @param eventsUrl
     * @param gitTagsUrl
     * @param notificationsUrl
     * @param gitUrl
     * @param subscriptionUrl
     * @param homepage
     */
    public RepositoryDTO(Integer id, String nodeId, String name, String fullName, Boolean _private, OwnerDTO ownerDTO, String htmlUrl, Object description, Boolean fork, String url, String forksUrl, String keysUrl, String collaboratorsUrl, String teamsUrl, String hooksUrl, String issueEventsUrl, String eventsUrl, String assigneesUrl, String branchesUrl, String tagsUrl, String blobsUrl, String gitTagsUrl, String gitRefsUrl, String treesUrl, String statusesUrl, String languagesUrl, String stargazersUrl, String contributorsUrl, String subscribersUrl, String subscriptionUrl, String commitsUrl, String gitCommitsUrl, String commentsUrl, String issueCommentUrl, String contentsUrl, String compareUrl, String mergesUrl, String archiveUrl, String downloadsUrl, String issuesUrl, String pullsUrl, String milestonesUrl, String notificationsUrl, String labelsUrl, String releasesUrl, String deploymentsUrl, Integer createdAt, String updatedAt, Integer pushedAt, String gitUrl, String sshUrl, String cloneUrl, String svnUrl, Object homepage, Integer size, Integer stargazersCount, Integer watchersCount, Object language, Boolean hasIssues, Boolean hasProjects, Boolean hasDownloads, Boolean hasWiki, Boolean hasPages, Boolean hasDiscussions, Integer forksCount, Object mirrorUrl, Boolean archived, Boolean disabled, Integer openIssuesCount, Object license, Boolean allowForking, Boolean isTemplate, Boolean webCommitSignoffRequired, List<Object> topics, String visibility, Integer forks, Integer openIssues, Integer watchers, String defaultBranch, Integer stargazers, String masterBranch) {
        super();
        this.id = id;
        this.nodeId = nodeId;
        this.name = name;
        this.fullName = fullName;
        this._private = _private;
        this.ownerDTO = ownerDTO;
        this.htmlUrl = htmlUrl;
        this.description = description;
        this.fork = fork;
        this.url = url;
        this.forksUrl = forksUrl;
        this.keysUrl = keysUrl;
        this.collaboratorsUrl = collaboratorsUrl;
        this.teamsUrl = teamsUrl;
        this.hooksUrl = hooksUrl;
        this.issueEventsUrl = issueEventsUrl;
        this.eventsUrl = eventsUrl;
        this.assigneesUrl = assigneesUrl;
        this.branchesUrl = branchesUrl;
        this.tagsUrl = tagsUrl;
        this.blobsUrl = blobsUrl;
        this.gitTagsUrl = gitTagsUrl;
        this.gitRefsUrl = gitRefsUrl;
        this.treesUrl = treesUrl;
        this.statusesUrl = statusesUrl;
        this.languagesUrl = languagesUrl;
        this.stargazersUrl = stargazersUrl;
        this.contributorsUrl = contributorsUrl;
        this.subscribersUrl = subscribersUrl;
        this.subscriptionUrl = subscriptionUrl;
        this.commitsUrl = commitsUrl;
        this.gitCommitsUrl = gitCommitsUrl;
        this.commentsUrl = commentsUrl;
        this.issueCommentUrl = issueCommentUrl;
        this.contentsUrl = contentsUrl;
        this.compareUrl = compareUrl;
        this.mergesUrl = mergesUrl;
        this.archiveUrl = archiveUrl;
        this.downloadsUrl = downloadsUrl;
        this.issuesUrl = issuesUrl;
        this.pullsUrl = pullsUrl;
        this.milestonesUrl = milestonesUrl;
        this.notificationsUrl = notificationsUrl;
        this.labelsUrl = labelsUrl;
        this.releasesUrl = releasesUrl;
        this.deploymentsUrl = deploymentsUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.pushedAt = pushedAt;
        this.gitUrl = gitUrl;
        this.sshUrl = sshUrl;
        this.cloneUrl = cloneUrl;
        this.svnUrl = svnUrl;
        this.homepage = homepage;
        this.size = size;
        this.stargazersCount = stargazersCount;
        this.watchersCount = watchersCount;
        this.language = language;
        this.hasIssues = hasIssues;
        this.hasProjects = hasProjects;
        this.hasDownloads = hasDownloads;
        this.hasWiki = hasWiki;
        this.hasPages = hasPages;
        this.hasDiscussions = hasDiscussions;
        this.forksCount = forksCount;
        this.mirrorUrl = mirrorUrl;
        this.archived = archived;
        this.disabled = disabled;
        this.openIssuesCount = openIssuesCount;
        this.license = license;
        this.allowForking = allowForking;
        this.isTemplate = isTemplate;
        this.webCommitSignoffRequired = webCommitSignoffRequired;
        this.topics = topics;
        this.visibility = visibility;
        this.forks = forks;
        this.openIssues = openIssues;
        this.watchers = watchers;
        this.defaultBranch = defaultBranch;
        this.stargazers = stargazers;
        this.masterBranch = masterBranch;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("node_id")
    public String getNodeId() {
        return nodeId;
    }

    @JsonProperty("node_id")
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("full_name")
    public String getFullName() {
        return fullName;
    }

    @JsonProperty("full_name")
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @JsonProperty("private")
    public Boolean getPrivate() {
        return _private;
    }

    @JsonProperty("private")
    public void setPrivate(Boolean _private) {
        this._private = _private;
    }

    @JsonProperty("owner")
    public OwnerDTO getOwner() {
        return ownerDTO;
    }

    @JsonProperty("owner")
    public void setOwner(OwnerDTO ownerDTO) {
        this.ownerDTO = ownerDTO;
    }

    @JsonProperty("html_url")
    public String getHtmlUrl() {
        return htmlUrl;
    }

    @JsonProperty("html_url")
    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    @JsonProperty("description")
    public Object getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(Object description) {
        this.description = description;
    }

    @JsonProperty("fork")
    public Boolean getFork() {
        return fork;
    }

    @JsonProperty("fork")
    public void setFork(Boolean fork) {
        this.fork = fork;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("forks_url")
    public String getForksUrl() {
        return forksUrl;
    }

    @JsonProperty("forks_url")
    public void setForksUrl(String forksUrl) {
        this.forksUrl = forksUrl;
    }

    @JsonProperty("keys_url")
    public String getKeysUrl() {
        return keysUrl;
    }

    @JsonProperty("keys_url")
    public void setKeysUrl(String keysUrl) {
        this.keysUrl = keysUrl;
    }

    @JsonProperty("collaborators_url")
    public String getCollaboratorsUrl() {
        return collaboratorsUrl;
    }

    @JsonProperty("collaborators_url")
    public void setCollaboratorsUrl(String collaboratorsUrl) {
        this.collaboratorsUrl = collaboratorsUrl;
    }

    @JsonProperty("teams_url")
    public String getTeamsUrl() {
        return teamsUrl;
    }

    @JsonProperty("teams_url")
    public void setTeamsUrl(String teamsUrl) {
        this.teamsUrl = teamsUrl;
    }

    @JsonProperty("hooks_url")
    public String getHooksUrl() {
        return hooksUrl;
    }

    @JsonProperty("hooks_url")
    public void setHooksUrl(String hooksUrl) {
        this.hooksUrl = hooksUrl;
    }

    @JsonProperty("issue_events_url")
    public String getIssueEventsUrl() {
        return issueEventsUrl;
    }

    @JsonProperty("issue_events_url")
    public void setIssueEventsUrl(String issueEventsUrl) {
        this.issueEventsUrl = issueEventsUrl;
    }

    @JsonProperty("events_url")
    public String getEventsUrl() {
        return eventsUrl;
    }

    @JsonProperty("events_url")
    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    @JsonProperty("assignees_url")
    public String getAssigneesUrl() {
        return assigneesUrl;
    }

    @JsonProperty("assignees_url")
    public void setAssigneesUrl(String assigneesUrl) {
        this.assigneesUrl = assigneesUrl;
    }

    @JsonProperty("branches_url")
    public String getBranchesUrl() {
        return branchesUrl;
    }

    @JsonProperty("branches_url")
    public void setBranchesUrl(String branchesUrl) {
        this.branchesUrl = branchesUrl;
    }

    @JsonProperty("tags_url")
    public String getTagsUrl() {
        return tagsUrl;
    }

    @JsonProperty("tags_url")
    public void setTagsUrl(String tagsUrl) {
        this.tagsUrl = tagsUrl;
    }

    @JsonProperty("blobs_url")
    public String getBlobsUrl() {
        return blobsUrl;
    }

    @JsonProperty("blobs_url")
    public void setBlobsUrl(String blobsUrl) {
        this.blobsUrl = blobsUrl;
    }

    @JsonProperty("git_tags_url")
    public String getGitTagsUrl() {
        return gitTagsUrl;
    }

    @JsonProperty("git_tags_url")
    public void setGitTagsUrl(String gitTagsUrl) {
        this.gitTagsUrl = gitTagsUrl;
    }

    @JsonProperty("git_refs_url")
    public String getGitRefsUrl() {
        return gitRefsUrl;
    }

    @JsonProperty("git_refs_url")
    public void setGitRefsUrl(String gitRefsUrl) {
        this.gitRefsUrl = gitRefsUrl;
    }

    @JsonProperty("trees_url")
    public String getTreesUrl() {
        return treesUrl;
    }

    @JsonProperty("trees_url")
    public void setTreesUrl(String treesUrl) {
        this.treesUrl = treesUrl;
    }

    @JsonProperty("statuses_url")
    public String getStatusesUrl() {
        return statusesUrl;
    }

    @JsonProperty("statuses_url")
    public void setStatusesUrl(String statusesUrl) {
        this.statusesUrl = statusesUrl;
    }

    @JsonProperty("languages_url")
    public String getLanguagesUrl() {
        return languagesUrl;
    }

    @JsonProperty("languages_url")
    public void setLanguagesUrl(String languagesUrl) {
        this.languagesUrl = languagesUrl;
    }

    @JsonProperty("stargazers_url")
    public String getStargazersUrl() {
        return stargazersUrl;
    }

    @JsonProperty("stargazers_url")
    public void setStargazersUrl(String stargazersUrl) {
        this.stargazersUrl = stargazersUrl;
    }

    @JsonProperty("contributors_url")
    public String getContributorsUrl() {
        return contributorsUrl;
    }

    @JsonProperty("contributors_url")
    public void setContributorsUrl(String contributorsUrl) {
        this.contributorsUrl = contributorsUrl;
    }

    @JsonProperty("subscribers_url")
    public String getSubscribersUrl() {
        return subscribersUrl;
    }

    @JsonProperty("subscribers_url")
    public void setSubscribersUrl(String subscribersUrl) {
        this.subscribersUrl = subscribersUrl;
    }

    @JsonProperty("subscription_url")
    public String getSubscriptionUrl() {
        return subscriptionUrl;
    }

    @JsonProperty("subscription_url")
    public void setSubscriptionUrl(String subscriptionUrl) {
        this.subscriptionUrl = subscriptionUrl;
    }

    @JsonProperty("commits_url")
    public String getCommitsUrl() {
        return commitsUrl;
    }

    @JsonProperty("commits_url")
    public void setCommitsUrl(String commitsUrl) {
        this.commitsUrl = commitsUrl;
    }

    @JsonProperty("git_commits_url")
    public String getGitCommitsUrl() {
        return gitCommitsUrl;
    }

    @JsonProperty("git_commits_url")
    public void setGitCommitsUrl(String gitCommitsUrl) {
        this.gitCommitsUrl = gitCommitsUrl;
    }

    @JsonProperty("comments_url")
    public String getCommentsUrl() {
        return commentsUrl;
    }

    @JsonProperty("comments_url")
    public void setCommentsUrl(String commentsUrl) {
        this.commentsUrl = commentsUrl;
    }

    @JsonProperty("issue_comment_url")
    public String getIssueCommentUrl() {
        return issueCommentUrl;
    }

    @JsonProperty("issue_comment_url")
    public void setIssueCommentUrl(String issueCommentUrl) {
        this.issueCommentUrl = issueCommentUrl;
    }

    @JsonProperty("contents_url")
    public String getContentsUrl() {
        return contentsUrl;
    }

    @JsonProperty("contents_url")
    public void setContentsUrl(String contentsUrl) {
        this.contentsUrl = contentsUrl;
    }

    @JsonProperty("compare_url")
    public String getCompareUrl() {
        return compareUrl;
    }

    @JsonProperty("compare_url")
    public void setCompareUrl(String compareUrl) {
        this.compareUrl = compareUrl;
    }

    @JsonProperty("merges_url")
    public String getMergesUrl() {
        return mergesUrl;
    }

    @JsonProperty("merges_url")
    public void setMergesUrl(String mergesUrl) {
        this.mergesUrl = mergesUrl;
    }

    @JsonProperty("archive_url")
    public String getArchiveUrl() {
        return archiveUrl;
    }

    @JsonProperty("archive_url")
    public void setArchiveUrl(String archiveUrl) {
        this.archiveUrl = archiveUrl;
    }

    @JsonProperty("downloads_url")
    public String getDownloadsUrl() {
        return downloadsUrl;
    }

    @JsonProperty("downloads_url")
    public void setDownloadsUrl(String downloadsUrl) {
        this.downloadsUrl = downloadsUrl;
    }

    @JsonProperty("issues_url")
    public String getIssuesUrl() {
        return issuesUrl;
    }

    @JsonProperty("issues_url")
    public void setIssuesUrl(String issuesUrl) {
        this.issuesUrl = issuesUrl;
    }

    @JsonProperty("pulls_url")
    public String getPullsUrl() {
        return pullsUrl;
    }

    @JsonProperty("pulls_url")
    public void setPullsUrl(String pullsUrl) {
        this.pullsUrl = pullsUrl;
    }

    @JsonProperty("milestones_url")
    public String getMilestonesUrl() {
        return milestonesUrl;
    }

    @JsonProperty("milestones_url")
    public void setMilestonesUrl(String milestonesUrl) {
        this.milestonesUrl = milestonesUrl;
    }

    @JsonProperty("notifications_url")
    public String getNotificationsUrl() {
        return notificationsUrl;
    }

    @JsonProperty("notifications_url")
    public void setNotificationsUrl(String notificationsUrl) {
        this.notificationsUrl = notificationsUrl;
    }

    @JsonProperty("labels_url")
    public String getLabelsUrl() {
        return labelsUrl;
    }

    @JsonProperty("labels_url")
    public void setLabelsUrl(String labelsUrl) {
        this.labelsUrl = labelsUrl;
    }

    @JsonProperty("releases_url")
    public String getReleasesUrl() {
        return releasesUrl;
    }

    @JsonProperty("releases_url")
    public void setReleasesUrl(String releasesUrl) {
        this.releasesUrl = releasesUrl;
    }

    @JsonProperty("deployments_url")
    public String getDeploymentsUrl() {
        return deploymentsUrl;
    }

    @JsonProperty("deployments_url")
    public void setDeploymentsUrl(String deploymentsUrl) {
        this.deploymentsUrl = deploymentsUrl;
    }

    @JsonProperty("created_at")
    public Integer getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("updated_at")
    public String getUpdatedAt() {
        return updatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonProperty("pushed_at")
    public Integer getPushedAt() {
        return pushedAt;
    }

    @JsonProperty("pushed_at")
    public void setPushedAt(Integer pushedAt) {
        this.pushedAt = pushedAt;
    }

    @JsonProperty("git_url")
    public String getGitUrl() {
        return gitUrl;
    }

    @JsonProperty("git_url")
    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    @JsonProperty("ssh_url")
    public String getSshUrl() {
        return sshUrl;
    }

    @JsonProperty("ssh_url")
    public void setSshUrl(String sshUrl) {
        this.sshUrl = sshUrl;
    }

    @JsonProperty("clone_url")
    public String getCloneUrl() {
        return cloneUrl;
    }

    @JsonProperty("clone_url")
    public void setCloneUrl(String cloneUrl) {
        this.cloneUrl = cloneUrl;
    }

    @JsonProperty("svn_url")
    public String getSvnUrl() {
        return svnUrl;
    }

    @JsonProperty("svn_url")
    public void setSvnUrl(String svnUrl) {
        this.svnUrl = svnUrl;
    }

    @JsonProperty("homepage")
    public Object getHomepage() {
        return homepage;
    }

    @JsonProperty("homepage")
    public void setHomepage(Object homepage) {
        this.homepage = homepage;
    }

    @JsonProperty("size")
    public Integer getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(Integer size) {
        this.size = size;
    }

    @JsonProperty("stargazers_count")
    public Integer getStargazersCount() {
        return stargazersCount;
    }

    @JsonProperty("stargazers_count")
    public void setStargazersCount(Integer stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    @JsonProperty("watchers_count")
    public Integer getWatchersCount() {
        return watchersCount;
    }

    @JsonProperty("watchers_count")
    public void setWatchersCount(Integer watchersCount) {
        this.watchersCount = watchersCount;
    }

    @JsonProperty("language")
    public Object getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(Object language) {
        this.language = language;
    }

    @JsonProperty("has_issues")
    public Boolean getHasIssues() {
        return hasIssues;
    }

    @JsonProperty("has_issues")
    public void setHasIssues(Boolean hasIssues) {
        this.hasIssues = hasIssues;
    }

    @JsonProperty("has_projects")
    public Boolean getHasProjects() {
        return hasProjects;
    }

    @JsonProperty("has_projects")
    public void setHasProjects(Boolean hasProjects) {
        this.hasProjects = hasProjects;
    }

    @JsonProperty("has_downloads")
    public Boolean getHasDownloads() {
        return hasDownloads;
    }

    @JsonProperty("has_downloads")
    public void setHasDownloads(Boolean hasDownloads) {
        this.hasDownloads = hasDownloads;
    }

    @JsonProperty("has_wiki")
    public Boolean getHasWiki() {
        return hasWiki;
    }

    @JsonProperty("has_wiki")
    public void setHasWiki(Boolean hasWiki) {
        this.hasWiki = hasWiki;
    }

    @JsonProperty("has_pages")
    public Boolean getHasPages() {
        return hasPages;
    }

    @JsonProperty("has_pages")
    public void setHasPages(Boolean hasPages) {
        this.hasPages = hasPages;
    }

    @JsonProperty("has_discussions")
    public Boolean getHasDiscussions() {
        return hasDiscussions;
    }

    @JsonProperty("has_discussions")
    public void setHasDiscussions(Boolean hasDiscussions) {
        this.hasDiscussions = hasDiscussions;
    }

    @JsonProperty("forks_count")
    public Integer getForksCount() {
        return forksCount;
    }

    @JsonProperty("forks_count")
    public void setForksCount(Integer forksCount) {
        this.forksCount = forksCount;
    }

    @JsonProperty("mirror_url")
    public Object getMirrorUrl() {
        return mirrorUrl;
    }

    @JsonProperty("mirror_url")
    public void setMirrorUrl(Object mirrorUrl) {
        this.mirrorUrl = mirrorUrl;
    }

    @JsonProperty("archived")
    public Boolean getArchived() {
        return archived;
    }

    @JsonProperty("archived")
    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    @JsonProperty("disabled")
    public Boolean getDisabled() {
        return disabled;
    }

    @JsonProperty("disabled")
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    @JsonProperty("open_issues_count")
    public Integer getOpenIssuesCount() {
        return openIssuesCount;
    }

    @JsonProperty("open_issues_count")
    public void setOpenIssuesCount(Integer openIssuesCount) {
        this.openIssuesCount = openIssuesCount;
    }

    @JsonProperty("license")
    public Object getLicense() {
        return license;
    }

    @JsonProperty("license")
    public void setLicense(Object license) {
        this.license = license;
    }

    @JsonProperty("allow_forking")
    public Boolean getAllowForking() {
        return allowForking;
    }

    @JsonProperty("allow_forking")
    public void setAllowForking(Boolean allowForking) {
        this.allowForking = allowForking;
    }

    @JsonProperty("is_template")
    public Boolean getIsTemplate() {
        return isTemplate;
    }

    @JsonProperty("is_template")
    public void setIsTemplate(Boolean isTemplate) {
        this.isTemplate = isTemplate;
    }

    @JsonProperty("web_commit_signoff_required")
    public Boolean getWebCommitSignoffRequired() {
        return webCommitSignoffRequired;
    }

    @JsonProperty("web_commit_signoff_required")
    public void setWebCommitSignoffRequired(Boolean webCommitSignoffRequired) {
        this.webCommitSignoffRequired = webCommitSignoffRequired;
    }

    @JsonProperty("topics")
    public List<Object> getTopics() {
        return topics;
    }

    @JsonProperty("topics")
    public void setTopics(List<Object> topics) {
        this.topics = topics;
    }

    @JsonProperty("visibility")
    public String getVisibility() {
        return visibility;
    }

    @JsonProperty("visibility")
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    @JsonProperty("forks")
    public Integer getForks() {
        return forks;
    }

    @JsonProperty("forks")
    public void setForks(Integer forks) {
        this.forks = forks;
    }

    @JsonProperty("open_issues")
    public Integer getOpenIssues() {
        return openIssues;
    }

    @JsonProperty("open_issues")
    public void setOpenIssues(Integer openIssues) {
        this.openIssues = openIssues;
    }

    @JsonProperty("watchers")
    public Integer getWatchers() {
        return watchers;
    }

    @JsonProperty("watchers")
    public void setWatchers(Integer watchers) {
        this.watchers = watchers;
    }

    @JsonProperty("default_branch")
    public String getDefaultBranch() {
        return defaultBranch;
    }

    @JsonProperty("default_branch")
    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }

    @JsonProperty("stargazers")
    public Integer getStargazers() {
        return stargazers;
    }

    @JsonProperty("stargazers")
    public void setStargazers(Integer stargazers) {
        this.stargazers = stargazers;
    }

    @JsonProperty("master_branch")
    public String getMasterBranch() {
        return masterBranch;
    }

    @JsonProperty("master_branch")
    public void setMasterBranch(String masterBranch) {
        this.masterBranch = masterBranch;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(RepositoryDTO.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("nodeId");
        sb.append('=');
        sb.append(((this.nodeId == null)?"<null>":this.nodeId));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("fullName");
        sb.append('=');
        sb.append(((this.fullName == null)?"<null>":this.fullName));
        sb.append(',');
        sb.append("_private");
        sb.append('=');
        sb.append(((this._private == null)?"<null>":this._private));
        sb.append(',');
        sb.append("owner");
        sb.append('=');
        sb.append(((this.ownerDTO == null)?"<null>":this.ownerDTO));
        sb.append(',');
        sb.append("htmlUrl");
        sb.append('=');
        sb.append(((this.htmlUrl == null)?"<null>":this.htmlUrl));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("fork");
        sb.append('=');
        sb.append(((this.fork == null)?"<null>":this.fork));
        sb.append(',');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null)?"<null>":this.url));
        sb.append(',');
        sb.append("forksUrl");
        sb.append('=');
        sb.append(((this.forksUrl == null)?"<null>":this.forksUrl));
        sb.append(',');
        sb.append("keysUrl");
        sb.append('=');
        sb.append(((this.keysUrl == null)?"<null>":this.keysUrl));
        sb.append(',');
        sb.append("collaboratorsUrl");
        sb.append('=');
        sb.append(((this.collaboratorsUrl == null)?"<null>":this.collaboratorsUrl));
        sb.append(',');
        sb.append("teamsUrl");
        sb.append('=');
        sb.append(((this.teamsUrl == null)?"<null>":this.teamsUrl));
        sb.append(',');
        sb.append("hooksUrl");
        sb.append('=');
        sb.append(((this.hooksUrl == null)?"<null>":this.hooksUrl));
        sb.append(',');
        sb.append("issueEventsUrl");
        sb.append('=');
        sb.append(((this.issueEventsUrl == null)?"<null>":this.issueEventsUrl));
        sb.append(',');
        sb.append("eventsUrl");
        sb.append('=');
        sb.append(((this.eventsUrl == null)?"<null>":this.eventsUrl));
        sb.append(',');
        sb.append("assigneesUrl");
        sb.append('=');
        sb.append(((this.assigneesUrl == null)?"<null>":this.assigneesUrl));
        sb.append(',');
        sb.append("branchesUrl");
        sb.append('=');
        sb.append(((this.branchesUrl == null)?"<null>":this.branchesUrl));
        sb.append(',');
        sb.append("tagsUrl");
        sb.append('=');
        sb.append(((this.tagsUrl == null)?"<null>":this.tagsUrl));
        sb.append(',');
        sb.append("blobsUrl");
        sb.append('=');
        sb.append(((this.blobsUrl == null)?"<null>":this.blobsUrl));
        sb.append(',');
        sb.append("gitTagsUrl");
        sb.append('=');
        sb.append(((this.gitTagsUrl == null)?"<null>":this.gitTagsUrl));
        sb.append(',');
        sb.append("gitRefsUrl");
        sb.append('=');
        sb.append(((this.gitRefsUrl == null)?"<null>":this.gitRefsUrl));
        sb.append(',');
        sb.append("treesUrl");
        sb.append('=');
        sb.append(((this.treesUrl == null)?"<null>":this.treesUrl));
        sb.append(',');
        sb.append("statusesUrl");
        sb.append('=');
        sb.append(((this.statusesUrl == null)?"<null>":this.statusesUrl));
        sb.append(',');
        sb.append("languagesUrl");
        sb.append('=');
        sb.append(((this.languagesUrl == null)?"<null>":this.languagesUrl));
        sb.append(',');
        sb.append("stargazersUrl");
        sb.append('=');
        sb.append(((this.stargazersUrl == null)?"<null>":this.stargazersUrl));
        sb.append(',');
        sb.append("contributorsUrl");
        sb.append('=');
        sb.append(((this.contributorsUrl == null)?"<null>":this.contributorsUrl));
        sb.append(',');
        sb.append("subscribersUrl");
        sb.append('=');
        sb.append(((this.subscribersUrl == null)?"<null>":this.subscribersUrl));
        sb.append(',');
        sb.append("subscriptionUrl");
        sb.append('=');
        sb.append(((this.subscriptionUrl == null)?"<null>":this.subscriptionUrl));
        sb.append(',');
        sb.append("commitsUrl");
        sb.append('=');
        sb.append(((this.commitsUrl == null)?"<null>":this.commitsUrl));
        sb.append(',');
        sb.append("gitCommitsUrl");
        sb.append('=');
        sb.append(((this.gitCommitsUrl == null)?"<null>":this.gitCommitsUrl));
        sb.append(',');
        sb.append("commentsUrl");
        sb.append('=');
        sb.append(((this.commentsUrl == null)?"<null>":this.commentsUrl));
        sb.append(',');
        sb.append("issueCommentUrl");
        sb.append('=');
        sb.append(((this.issueCommentUrl == null)?"<null>":this.issueCommentUrl));
        sb.append(',');
        sb.append("contentsUrl");
        sb.append('=');
        sb.append(((this.contentsUrl == null)?"<null>":this.contentsUrl));
        sb.append(',');
        sb.append("compareUrl");
        sb.append('=');
        sb.append(((this.compareUrl == null)?"<null>":this.compareUrl));
        sb.append(',');
        sb.append("mergesUrl");
        sb.append('=');
        sb.append(((this.mergesUrl == null)?"<null>":this.mergesUrl));
        sb.append(',');
        sb.append("archiveUrl");
        sb.append('=');
        sb.append(((this.archiveUrl == null)?"<null>":this.archiveUrl));
        sb.append(',');
        sb.append("downloadsUrl");
        sb.append('=');
        sb.append(((this.downloadsUrl == null)?"<null>":this.downloadsUrl));
        sb.append(',');
        sb.append("issuesUrl");
        sb.append('=');
        sb.append(((this.issuesUrl == null)?"<null>":this.issuesUrl));
        sb.append(',');
        sb.append("pullsUrl");
        sb.append('=');
        sb.append(((this.pullsUrl == null)?"<null>":this.pullsUrl));
        sb.append(',');
        sb.append("milestonesUrl");
        sb.append('=');
        sb.append(((this.milestonesUrl == null)?"<null>":this.milestonesUrl));
        sb.append(',');
        sb.append("notificationsUrl");
        sb.append('=');
        sb.append(((this.notificationsUrl == null)?"<null>":this.notificationsUrl));
        sb.append(',');
        sb.append("labelsUrl");
        sb.append('=');
        sb.append(((this.labelsUrl == null)?"<null>":this.labelsUrl));
        sb.append(',');
        sb.append("releasesUrl");
        sb.append('=');
        sb.append(((this.releasesUrl == null)?"<null>":this.releasesUrl));
        sb.append(',');
        sb.append("deploymentsUrl");
        sb.append('=');
        sb.append(((this.deploymentsUrl == null)?"<null>":this.deploymentsUrl));
        sb.append(',');
        sb.append("createdAt");
        sb.append('=');
        sb.append(((this.createdAt == null)?"<null>":this.createdAt));
        sb.append(',');
        sb.append("updatedAt");
        sb.append('=');
        sb.append(((this.updatedAt == null)?"<null>":this.updatedAt));
        sb.append(',');
        sb.append("pushedAt");
        sb.append('=');
        sb.append(((this.pushedAt == null)?"<null>":this.pushedAt));
        sb.append(',');
        sb.append("gitUrl");
        sb.append('=');
        sb.append(((this.gitUrl == null)?"<null>":this.gitUrl));
        sb.append(',');
        sb.append("sshUrl");
        sb.append('=');
        sb.append(((this.sshUrl == null)?"<null>":this.sshUrl));
        sb.append(',');
        sb.append("cloneUrl");
        sb.append('=');
        sb.append(((this.cloneUrl == null)?"<null>":this.cloneUrl));
        sb.append(',');
        sb.append("svnUrl");
        sb.append('=');
        sb.append(((this.svnUrl == null)?"<null>":this.svnUrl));
        sb.append(',');
        sb.append("homepage");
        sb.append('=');
        sb.append(((this.homepage == null)?"<null>":this.homepage));
        sb.append(',');
        sb.append("size");
        sb.append('=');
        sb.append(((this.size == null)?"<null>":this.size));
        sb.append(',');
        sb.append("stargazersCount");
        sb.append('=');
        sb.append(((this.stargazersCount == null)?"<null>":this.stargazersCount));
        sb.append(',');
        sb.append("watchersCount");
        sb.append('=');
        sb.append(((this.watchersCount == null)?"<null>":this.watchersCount));
        sb.append(',');
        sb.append("language");
        sb.append('=');
        sb.append(((this.language == null)?"<null>":this.language));
        sb.append(',');
        sb.append("hasIssues");
        sb.append('=');
        sb.append(((this.hasIssues == null)?"<null>":this.hasIssues));
        sb.append(',');
        sb.append("hasProjects");
        sb.append('=');
        sb.append(((this.hasProjects == null)?"<null>":this.hasProjects));
        sb.append(',');
        sb.append("hasDownloads");
        sb.append('=');
        sb.append(((this.hasDownloads == null)?"<null>":this.hasDownloads));
        sb.append(',');
        sb.append("hasWiki");
        sb.append('=');
        sb.append(((this.hasWiki == null)?"<null>":this.hasWiki));
        sb.append(',');
        sb.append("hasPages");
        sb.append('=');
        sb.append(((this.hasPages == null)?"<null>":this.hasPages));
        sb.append(',');
        sb.append("hasDiscussions");
        sb.append('=');
        sb.append(((this.hasDiscussions == null)?"<null>":this.hasDiscussions));
        sb.append(',');
        sb.append("forksCount");
        sb.append('=');
        sb.append(((this.forksCount == null)?"<null>":this.forksCount));
        sb.append(',');
        sb.append("mirrorUrl");
        sb.append('=');
        sb.append(((this.mirrorUrl == null)?"<null>":this.mirrorUrl));
        sb.append(',');
        sb.append("archived");
        sb.append('=');
        sb.append(((this.archived == null)?"<null>":this.archived));
        sb.append(',');
        sb.append("disabled");
        sb.append('=');
        sb.append(((this.disabled == null)?"<null>":this.disabled));
        sb.append(',');
        sb.append("openIssuesCount");
        sb.append('=');
        sb.append(((this.openIssuesCount == null)?"<null>":this.openIssuesCount));
        sb.append(',');
        sb.append("license");
        sb.append('=');
        sb.append(((this.license == null)?"<null>":this.license));
        sb.append(',');
        sb.append("allowForking");
        sb.append('=');
        sb.append(((this.allowForking == null)?"<null>":this.allowForking));
        sb.append(',');
        sb.append("isTemplate");
        sb.append('=');
        sb.append(((this.isTemplate == null)?"<null>":this.isTemplate));
        sb.append(',');
        sb.append("webCommitSignoffRequired");
        sb.append('=');
        sb.append(((this.webCommitSignoffRequired == null)?"<null>":this.webCommitSignoffRequired));
        sb.append(',');
        sb.append("topics");
        sb.append('=');
        sb.append(((this.topics == null)?"<null>":this.topics));
        sb.append(',');
        sb.append("visibility");
        sb.append('=');
        sb.append(((this.visibility == null)?"<null>":this.visibility));
        sb.append(',');
        sb.append("forks");
        sb.append('=');
        sb.append(((this.forks == null)?"<null>":this.forks));
        sb.append(',');
        sb.append("openIssues");
        sb.append('=');
        sb.append(((this.openIssues == null)?"<null>":this.openIssues));
        sb.append(',');
        sb.append("watchers");
        sb.append('=');
        sb.append(((this.watchers == null)?"<null>":this.watchers));
        sb.append(',');
        sb.append("defaultBranch");
        sb.append('=');
        sb.append(((this.defaultBranch == null)?"<null>":this.defaultBranch));
        sb.append(',');
        sb.append("stargazers");
        sb.append('=');
        sb.append(((this.stargazers == null)?"<null>":this.stargazers));
        sb.append(',');
        sb.append("masterBranch");
        sb.append('=');
        sb.append(((this.masterBranch == null)?"<null>":this.masterBranch));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.sshUrl == null)? 0 :this.sshUrl.hashCode()));
        result = ((result* 31)+((this.archiveUrl == null)? 0 :this.archiveUrl.hashCode()));
        result = ((result* 31)+((this.languagesUrl == null)? 0 :this.languagesUrl.hashCode()));
        result = ((result* 31)+((this.language == null)? 0 :this.language.hashCode()));
        result = ((result* 31)+((this.assigneesUrl == null)? 0 :this.assigneesUrl.hashCode()));
        result = ((result* 31)+((this.commitsUrl == null)? 0 :this.commitsUrl.hashCode()));
        result = ((result* 31)+((this.openIssues == null)? 0 :this.openIssues.hashCode()));
        result = ((result* 31)+((this.cloneUrl == null)? 0 :this.cloneUrl.hashCode()));
        result = ((result* 31)+((this.forksCount == null)? 0 :this.forksCount.hashCode()));
        result = ((result* 31)+((this.subscribersUrl == null)? 0 :this.subscribersUrl.hashCode()));
        result = ((result* 31)+((this.createdAt == null)? 0 :this.createdAt.hashCode()));
        result = ((result* 31)+((this.forksUrl == null)? 0 :this.forksUrl.hashCode()));
        result = ((result* 31)+((this.watchersCount == null)? 0 :this.watchersCount.hashCode()));
        result = ((result* 31)+((this._private == null)? 0 :this._private.hashCode()));
        result = ((result* 31)+((this.masterBranch == null)? 0 :this.masterBranch.hashCode()));
        result = ((result* 31)+((this.issueCommentUrl == null)? 0 :this.issueCommentUrl.hashCode()));
        result = ((result* 31)+((this.statusesUrl == null)? 0 :this.statusesUrl.hashCode()));
        result = ((result* 31)+((this.id == null)? 0 :this.id.hashCode()));
        result = ((result* 31)+((this.collaboratorsUrl == null)? 0 :this.collaboratorsUrl.hashCode()));
        result = ((result* 31)+((this.updatedAt == null)? 0 :this.updatedAt.hashCode()));
        result = ((result* 31)+((this.forks == null)? 0 :this.forks.hashCode()));
        result = ((result* 31)+((this.allowForking == null)? 0 :this.allowForking.hashCode()));
        result = ((result* 31)+((this.labelsUrl == null)? 0 :this.labelsUrl.hashCode()));
        result = ((result* 31)+((this.visibility == null)? 0 :this.visibility.hashCode()));
        result = ((result* 31)+((this.defaultBranch == null)? 0 :this.defaultBranch.hashCode()));
        result = ((result* 31)+((this.keysUrl == null)? 0 :this.keysUrl.hashCode()));
        result = ((result* 31)+((this.downloadsUrl == null)? 0 :this.downloadsUrl.hashCode()));
        result = ((result* 31)+((this.contentsUrl == null)? 0 :this.contentsUrl.hashCode()));
        result = ((result* 31)+((this.pushedAt == null)? 0 :this.pushedAt.hashCode()));
        result = ((result* 31)+((this.tagsUrl == null)? 0 :this.tagsUrl.hashCode()));
        result = ((result* 31)+((this.license == null)? 0 :this.license.hashCode()));
        result = ((result* 31)+((this.commentsUrl == null)? 0 :this.commentsUrl.hashCode()));
        result = ((result* 31)+((this.size == null)? 0 :this.size.hashCode()));
        result = ((result* 31)+((this.isTemplate == null)? 0 :this.isTemplate.hashCode()));
        result = ((result* 31)+((this.treesUrl == null)? 0 :this.treesUrl.hashCode()));
        result = ((result* 31)+((this.name == null)? 0 :this.name.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.mergesUrl == null)? 0 :this.mergesUrl.hashCode()));
        result = ((result* 31)+((this.nodeId == null)? 0 :this.nodeId.hashCode()));
        result = ((result* 31)+((this.teamsUrl == null)? 0 :this.teamsUrl.hashCode()));
        result = ((result* 31)+((this.blobsUrl == null)? 0 :this.blobsUrl.hashCode()));
        result = ((result* 31)+((this.issueEventsUrl == null)? 0 :this.issueEventsUrl.hashCode()));
        result = ((result* 31)+((this.hasPages == null)? 0 :this.hasPages.hashCode()));
        result = ((result* 31)+((this.milestonesUrl == null)? 0 :this.milestonesUrl.hashCode()));
        result = ((result* 31)+((this.issuesUrl == null)? 0 :this.issuesUrl.hashCode()));
        result = ((result* 31)+((this.webCommitSignoffRequired == null)? 0 :this.webCommitSignoffRequired.hashCode()));
        result = ((result* 31)+((this.releasesUrl == null)? 0 :this.releasesUrl.hashCode()));
        result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
        result = ((result* 31)+((this.watchers == null)? 0 :this.watchers.hashCode()));
        result = ((result* 31)+((this.branchesUrl == null)? 0 :this.branchesUrl.hashCode()));
        result = ((result* 31)+((this.contributorsUrl == null)? 0 :this.contributorsUrl.hashCode()));
        result = ((result* 31)+((this.gitRefsUrl == null)? 0 :this.gitRefsUrl.hashCode()));
        result = ((result* 31)+((this.hooksUrl == null)? 0 :this.hooksUrl.hashCode()));
        result = ((result* 31)+((this.openIssuesCount == null)? 0 :this.openIssuesCount.hashCode()));
        result = ((result* 31)+((this.archived == null)? 0 :this.archived.hashCode()));
        result = ((result* 31)+((this.stargazersCount == null)? 0 :this.stargazersCount.hashCode()));
        result = ((result* 31)+((this.disabled == null)? 0 :this.disabled.hashCode()));
        result = ((result* 31)+((this.hasIssues == null)? 0 :this.hasIssues.hashCode()));
        result = ((result* 31)+((this.ownerDTO == null)? 0 :this.ownerDTO.hashCode()));
        result = ((result* 31)+((this.hasWiki == null)? 0 :this.hasWiki.hashCode()));
        result = ((result* 31)+((this.compareUrl == null)? 0 :this.compareUrl.hashCode()));
        result = ((result* 31)+((this.gitCommitsUrl == null)? 0 :this.gitCommitsUrl.hashCode()));
        result = ((result* 31)+((this.topics == null)? 0 :this.topics.hashCode()));
        result = ((result* 31)+((this.htmlUrl == null)? 0 :this.htmlUrl.hashCode()));
        result = ((result* 31)+((this.stargazersUrl == null)? 0 :this.stargazersUrl.hashCode()));
        result = ((result* 31)+((this.fullName == null)? 0 :this.fullName.hashCode()));
        result = ((result* 31)+((this.svnUrl == null)? 0 :this.svnUrl.hashCode()));
        result = ((result* 31)+((this.hasDiscussions == null)? 0 :this.hasDiscussions.hashCode()));
        result = ((result* 31)+((this.url == null)? 0 :this.url.hashCode()));
        result = ((result* 31)+((this.pullsUrl == null)? 0 :this.pullsUrl.hashCode()));
        result = ((result* 31)+((this.mirrorUrl == null)? 0 :this.mirrorUrl.hashCode()));
        result = ((result* 31)+((this.hasDownloads == null)? 0 :this.hasDownloads.hashCode()));
        result = ((result* 31)+((this.fork == null)? 0 :this.fork.hashCode()));
        result = ((result* 31)+((this.stargazers == null)? 0 :this.stargazers.hashCode()));
        result = ((result* 31)+((this.hasProjects == null)? 0 :this.hasProjects.hashCode()));
        result = ((result* 31)+((this.deploymentsUrl == null)? 0 :this.deploymentsUrl.hashCode()));
        result = ((result* 31)+((this.eventsUrl == null)? 0 :this.eventsUrl.hashCode()));
        result = ((result* 31)+((this.gitTagsUrl == null)? 0 :this.gitTagsUrl.hashCode()));
        result = ((result* 31)+((this.notificationsUrl == null)? 0 :this.notificationsUrl.hashCode()));
        result = ((result* 31)+((this.gitUrl == null)? 0 :this.gitUrl.hashCode()));
        result = ((result* 31)+((this.subscriptionUrl == null)? 0 :this.subscriptionUrl.hashCode()));
        result = ((result* 31)+((this.homepage == null)? 0 :this.homepage.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof RepositoryDTO) == false) {
            return false;
        }
        RepositoryDTO rhs = ((RepositoryDTO) other);
        return (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.sshUrl == rhs.sshUrl)||((this.sshUrl!= null)&&this.sshUrl.equals(rhs.sshUrl)))&&((this.archiveUrl == rhs.archiveUrl)||((this.archiveUrl!= null)&&this.archiveUrl.equals(rhs.archiveUrl))))&&((this.languagesUrl == rhs.languagesUrl)||((this.languagesUrl!= null)&&this.languagesUrl.equals(rhs.languagesUrl))))&&((this.language == rhs.language)||((this.language!= null)&&this.language.equals(rhs.language))))&&((this.assigneesUrl == rhs.assigneesUrl)||((this.assigneesUrl!= null)&&this.assigneesUrl.equals(rhs.assigneesUrl))))&&((this.commitsUrl == rhs.commitsUrl)||((this.commitsUrl!= null)&&this.commitsUrl.equals(rhs.commitsUrl))))&&((this.openIssues == rhs.openIssues)||((this.openIssues!= null)&&this.openIssues.equals(rhs.openIssues))))&&((this.cloneUrl == rhs.cloneUrl)||((this.cloneUrl!= null)&&this.cloneUrl.equals(rhs.cloneUrl))))&&((this.forksCount == rhs.forksCount)||((this.forksCount!= null)&&this.forksCount.equals(rhs.forksCount))))&&((this.subscribersUrl == rhs.subscribersUrl)||((this.subscribersUrl!= null)&&this.subscribersUrl.equals(rhs.subscribersUrl))))&&((this.createdAt == rhs.createdAt)||((this.createdAt!= null)&&this.createdAt.equals(rhs.createdAt))))&&((this.forksUrl == rhs.forksUrl)||((this.forksUrl!= null)&&this.forksUrl.equals(rhs.forksUrl))))&&((this.watchersCount == rhs.watchersCount)||((this.watchersCount!= null)&&this.watchersCount.equals(rhs.watchersCount))))&&((this._private == rhs._private)||((this._private!= null)&&this._private.equals(rhs._private))))&&((this.masterBranch == rhs.masterBranch)||((this.masterBranch!= null)&&this.masterBranch.equals(rhs.masterBranch))))&&((this.issueCommentUrl == rhs.issueCommentUrl)||((this.issueCommentUrl!= null)&&this.issueCommentUrl.equals(rhs.issueCommentUrl))))&&((this.statusesUrl == rhs.statusesUrl)||((this.statusesUrl!= null)&&this.statusesUrl.equals(rhs.statusesUrl))))&&((this.id == rhs.id)||((this.id!= null)&&this.id.equals(rhs.id))))&&((this.collaboratorsUrl == rhs.collaboratorsUrl)||((this.collaboratorsUrl!= null)&&this.collaboratorsUrl.equals(rhs.collaboratorsUrl))))&&((this.updatedAt == rhs.updatedAt)||((this.updatedAt!= null)&&this.updatedAt.equals(rhs.updatedAt))))&&((this.forks == rhs.forks)||((this.forks!= null)&&this.forks.equals(rhs.forks))))&&((this.allowForking == rhs.allowForking)||((this.allowForking!= null)&&this.allowForking.equals(rhs.allowForking))))&&((this.labelsUrl == rhs.labelsUrl)||((this.labelsUrl!= null)&&this.labelsUrl.equals(rhs.labelsUrl))))&&((this.visibility == rhs.visibility)||((this.visibility!= null)&&this.visibility.equals(rhs.visibility))))&&((this.defaultBranch == rhs.defaultBranch)||((this.defaultBranch!= null)&&this.defaultBranch.equals(rhs.defaultBranch))))&&((this.keysUrl == rhs.keysUrl)||((this.keysUrl!= null)&&this.keysUrl.equals(rhs.keysUrl))))&&((this.downloadsUrl == rhs.downloadsUrl)||((this.downloadsUrl!= null)&&this.downloadsUrl.equals(rhs.downloadsUrl))))&&((this.contentsUrl == rhs.contentsUrl)||((this.contentsUrl!= null)&&this.contentsUrl.equals(rhs.contentsUrl))))&&((this.pushedAt == rhs.pushedAt)||((this.pushedAt!= null)&&this.pushedAt.equals(rhs.pushedAt))))&&((this.tagsUrl == rhs.tagsUrl)||((this.tagsUrl!= null)&&this.tagsUrl.equals(rhs.tagsUrl))))&&((this.license == rhs.license)||((this.license!= null)&&this.license.equals(rhs.license))))&&((this.commentsUrl == rhs.commentsUrl)||((this.commentsUrl!= null)&&this.commentsUrl.equals(rhs.commentsUrl))))&&((this.size == rhs.size)||((this.size!= null)&&this.size.equals(rhs.size))))&&((this.isTemplate == rhs.isTemplate)||((this.isTemplate!= null)&&this.isTemplate.equals(rhs.isTemplate))))&&((this.treesUrl == rhs.treesUrl)||((this.treesUrl!= null)&&this.treesUrl.equals(rhs.treesUrl))))&&((this.name == rhs.name)||((this.name!= null)&&this.name.equals(rhs.name))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.mergesUrl == rhs.mergesUrl)||((this.mergesUrl!= null)&&this.mergesUrl.equals(rhs.mergesUrl))))&&((this.nodeId == rhs.nodeId)||((this.nodeId!= null)&&this.nodeId.equals(rhs.nodeId))))&&((this.teamsUrl == rhs.teamsUrl)||((this.teamsUrl!= null)&&this.teamsUrl.equals(rhs.teamsUrl))))&&((this.blobsUrl == rhs.blobsUrl)||((this.blobsUrl!= null)&&this.blobsUrl.equals(rhs.blobsUrl))))&&((this.issueEventsUrl == rhs.issueEventsUrl)||((this.issueEventsUrl!= null)&&this.issueEventsUrl.equals(rhs.issueEventsUrl))))&&((this.hasPages == rhs.hasPages)||((this.hasPages!= null)&&this.hasPages.equals(rhs.hasPages))))&&((this.milestonesUrl == rhs.milestonesUrl)||((this.milestonesUrl!= null)&&this.milestonesUrl.equals(rhs.milestonesUrl))))&&((this.issuesUrl == rhs.issuesUrl)||((this.issuesUrl!= null)&&this.issuesUrl.equals(rhs.issuesUrl))))&&((this.webCommitSignoffRequired == rhs.webCommitSignoffRequired)||((this.webCommitSignoffRequired!= null)&&this.webCommitSignoffRequired.equals(rhs.webCommitSignoffRequired))))&&((this.releasesUrl == rhs.releasesUrl)||((this.releasesUrl!= null)&&this.releasesUrl.equals(rhs.releasesUrl))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.watchers == rhs.watchers)||((this.watchers!= null)&&this.watchers.equals(rhs.watchers))))&&((this.branchesUrl == rhs.branchesUrl)||((this.branchesUrl!= null)&&this.branchesUrl.equals(rhs.branchesUrl))))&&((this.contributorsUrl == rhs.contributorsUrl)||((this.contributorsUrl!= null)&&this.contributorsUrl.equals(rhs.contributorsUrl))))&&((this.gitRefsUrl == rhs.gitRefsUrl)||((this.gitRefsUrl!= null)&&this.gitRefsUrl.equals(rhs.gitRefsUrl))))&&((this.hooksUrl == rhs.hooksUrl)||((this.hooksUrl!= null)&&this.hooksUrl.equals(rhs.hooksUrl))))&&((this.openIssuesCount == rhs.openIssuesCount)||((this.openIssuesCount!= null)&&this.openIssuesCount.equals(rhs.openIssuesCount))))&&((this.archived == rhs.archived)||((this.archived!= null)&&this.archived.equals(rhs.archived))))&&((this.stargazersCount == rhs.stargazersCount)||((this.stargazersCount!= null)&&this.stargazersCount.equals(rhs.stargazersCount))))&&((this.disabled == rhs.disabled)||((this.disabled!= null)&&this.disabled.equals(rhs.disabled))))&&((this.hasIssues == rhs.hasIssues)||((this.hasIssues!= null)&&this.hasIssues.equals(rhs.hasIssues))))&&((this.ownerDTO == rhs.ownerDTO)||((this.ownerDTO != null)&&this.ownerDTO.equals(rhs.ownerDTO))))&&((this.hasWiki == rhs.hasWiki)||((this.hasWiki!= null)&&this.hasWiki.equals(rhs.hasWiki))))&&((this.compareUrl == rhs.compareUrl)||((this.compareUrl!= null)&&this.compareUrl.equals(rhs.compareUrl))))&&((this.gitCommitsUrl == rhs.gitCommitsUrl)||((this.gitCommitsUrl!= null)&&this.gitCommitsUrl.equals(rhs.gitCommitsUrl))))&&((this.topics == rhs.topics)||((this.topics!= null)&&this.topics.equals(rhs.topics))))&&((this.htmlUrl == rhs.htmlUrl)||((this.htmlUrl!= null)&&this.htmlUrl.equals(rhs.htmlUrl))))&&((this.stargazersUrl == rhs.stargazersUrl)||((this.stargazersUrl!= null)&&this.stargazersUrl.equals(rhs.stargazersUrl))))&&((this.fullName == rhs.fullName)||((this.fullName!= null)&&this.fullName.equals(rhs.fullName))))&&((this.svnUrl == rhs.svnUrl)||((this.svnUrl!= null)&&this.svnUrl.equals(rhs.svnUrl))))&&((this.hasDiscussions == rhs.hasDiscussions)||((this.hasDiscussions!= null)&&this.hasDiscussions.equals(rhs.hasDiscussions))))&&((this.url == rhs.url)||((this.url!= null)&&this.url.equals(rhs.url))))&&((this.pullsUrl == rhs.pullsUrl)||((this.pullsUrl!= null)&&this.pullsUrl.equals(rhs.pullsUrl))))&&((this.mirrorUrl == rhs.mirrorUrl)||((this.mirrorUrl!= null)&&this.mirrorUrl.equals(rhs.mirrorUrl))))&&((this.hasDownloads == rhs.hasDownloads)||((this.hasDownloads!= null)&&this.hasDownloads.equals(rhs.hasDownloads))))&&((this.fork == rhs.fork)||((this.fork!= null)&&this.fork.equals(rhs.fork))))&&((this.stargazers == rhs.stargazers)||((this.stargazers!= null)&&this.stargazers.equals(rhs.stargazers))))&&((this.hasProjects == rhs.hasProjects)||((this.hasProjects!= null)&&this.hasProjects.equals(rhs.hasProjects))))&&((this.deploymentsUrl == rhs.deploymentsUrl)||((this.deploymentsUrl!= null)&&this.deploymentsUrl.equals(rhs.deploymentsUrl))))&&((this.eventsUrl == rhs.eventsUrl)||((this.eventsUrl!= null)&&this.eventsUrl.equals(rhs.eventsUrl))))&&((this.gitTagsUrl == rhs.gitTagsUrl)||((this.gitTagsUrl!= null)&&this.gitTagsUrl.equals(rhs.gitTagsUrl))))&&((this.notificationsUrl == rhs.notificationsUrl)||((this.notificationsUrl!= null)&&this.notificationsUrl.equals(rhs.notificationsUrl))))&&((this.gitUrl == rhs.gitUrl)||((this.gitUrl!= null)&&this.gitUrl.equals(rhs.gitUrl))))&&((this.subscriptionUrl == rhs.subscriptionUrl)||((this.subscriptionUrl!= null)&&this.subscriptionUrl.equals(rhs.subscriptionUrl))))&&((this.homepage == rhs.homepage)||((this.homepage!= null)&&this.homepage.equals(rhs.homepage))));
    }

}

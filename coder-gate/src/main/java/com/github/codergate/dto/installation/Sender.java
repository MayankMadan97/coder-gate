
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
        "login",
        "id",
        "node_id",
        "avatar_url",
        "gravatar_id",
        "url",
        "html_url",
        "followers_url",
        "following_url",
        "gists_url",
        "starred_url",
        "subscriptions_url",
        "organizations_url",
        "repos_url",
        "events_url",
        "received_events_url",
        "type",
        "site_admin"
})
public class Sender implements Serializable {

    @JsonProperty("login")
    private String login;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("node_id")
    private String nodeId;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("gravatar_id")
    private String gravatarId;
    @JsonProperty("url")
    private String url;
    @JsonProperty("html_url")
    private String htmlUrl;
    @JsonProperty("followers_url")
    private String followersUrl;
    @JsonProperty("following_url")
    private String followingUrl;
    @JsonProperty("gists_url")
    private String gistsUrl;
    @JsonProperty("starred_url")
    private String starredUrl;
    @JsonProperty("subscriptions_url")
    private String subscriptionsUrl;
    @JsonProperty("organizations_url")
    private String organizationsUrl;
    @JsonProperty("repos_url")
    private String reposUrl;
    @JsonProperty("events_url")
    private String eventsUrl;
    @JsonProperty("received_events_url")
    private String receivedEventsUrl;
    @JsonProperty("type")
    private String type;
    @JsonProperty("site_admin")
    private Boolean siteAdmin;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = 6423529284909303006L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Sender() {
    }

    /**
     * 
     * @param receivedEventsUrl
     * @param siteAdmin
     * @param followingUrl
     * @param gistsUrl
     * @param avatarUrl
     * @param organizationsUrl
     * @param reposUrl
     * @param htmlUrl
     * @param subscriptionsUrl
     * @param login
     * @param type
     * @param url
     * @param starredUrl
     * @param gravatarId
     * @param followersUrl
     * @param id
     * @param eventsUrl
     * @param nodeId
     */
    public Sender(String login, Integer id, String nodeId, String avatarUrl, String gravatarId, String url,
            String htmlUrl, String followersUrl, String followingUrl, String gistsUrl, String starredUrl,
            String subscriptionsUrl, String organizationsUrl, String reposUrl, String eventsUrl,
            String receivedEventsUrl, String type, Boolean siteAdmin) {
        super();
        this.login = login;
        this.id = id;
        this.nodeId = nodeId;
        this.avatarUrl = avatarUrl;
        this.gravatarId = gravatarId;
        this.url = url;
        this.htmlUrl = htmlUrl;
        this.followersUrl = followersUrl;
        this.followingUrl = followingUrl;
        this.gistsUrl = gistsUrl;
        this.starredUrl = starredUrl;
        this.subscriptionsUrl = subscriptionsUrl;
        this.organizationsUrl = organizationsUrl;
        this.reposUrl = reposUrl;
        this.eventsUrl = eventsUrl;
        this.receivedEventsUrl = receivedEventsUrl;
        this.type = type;
        this.siteAdmin = siteAdmin;
    }

    @JsonProperty("login")
    public String getLogin() {
        return login;
    }

    @JsonProperty("login")
    public void setLogin(String login) {
        this.login = login;
    }

    public Sender withLogin(String login) {
        this.login = login;
        return this;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public Sender withId(Integer id) {
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

    public Sender withNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    @JsonProperty("avatar_url")
    public String getAvatarUrl() {
        return avatarUrl;
    }

    @JsonProperty("avatar_url")
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Sender withAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    @JsonProperty("gravatar_id")
    public String getGravatarId() {
        return gravatarId;
    }

    @JsonProperty("gravatar_id")
    public void setGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
    }

    public Sender withGravatarId(String gravatarId) {
        this.gravatarId = gravatarId;
        return this;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    public Sender withUrl(String url) {
        this.url = url;
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

    public Sender withHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
        return this;
    }

    @JsonProperty("followers_url")
    public String getFollowersUrl() {
        return followersUrl;
    }

    @JsonProperty("followers_url")
    public void setFollowersUrl(String followersUrl) {
        this.followersUrl = followersUrl;
    }

    public Sender withFollowersUrl(String followersUrl) {
        this.followersUrl = followersUrl;
        return this;
    }

    @JsonProperty("following_url")
    public String getFollowingUrl() {
        return followingUrl;
    }

    @JsonProperty("following_url")
    public void setFollowingUrl(String followingUrl) {
        this.followingUrl = followingUrl;
    }

    public Sender withFollowingUrl(String followingUrl) {
        this.followingUrl = followingUrl;
        return this;
    }

    @JsonProperty("gists_url")
    public String getGistsUrl() {
        return gistsUrl;
    }

    @JsonProperty("gists_url")
    public void setGistsUrl(String gistsUrl) {
        this.gistsUrl = gistsUrl;
    }

    public Sender withGistsUrl(String gistsUrl) {
        this.gistsUrl = gistsUrl;
        return this;
    }

    @JsonProperty("starred_url")
    public String getStarredUrl() {
        return starredUrl;
    }

    @JsonProperty("starred_url")
    public void setStarredUrl(String starredUrl) {
        this.starredUrl = starredUrl;
    }

    public Sender withStarredUrl(String starredUrl) {
        this.starredUrl = starredUrl;
        return this;
    }

    @JsonProperty("subscriptions_url")
    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    @JsonProperty("subscriptions_url")
    public void setSubscriptionsUrl(String subscriptionsUrl) {
        this.subscriptionsUrl = subscriptionsUrl;
    }

    public Sender withSubscriptionsUrl(String subscriptionsUrl) {
        this.subscriptionsUrl = subscriptionsUrl;
        return this;
    }

    @JsonProperty("organizations_url")
    public String getOrganizationsUrl() {
        return organizationsUrl;
    }

    @JsonProperty("organizations_url")
    public void setOrganizationsUrl(String organizationsUrl) {
        this.organizationsUrl = organizationsUrl;
    }

    public Sender withOrganizationsUrl(String organizationsUrl) {
        this.organizationsUrl = organizationsUrl;
        return this;
    }

    @JsonProperty("repos_url")
    public String getReposUrl() {
        return reposUrl;
    }

    @JsonProperty("repos_url")
    public void setReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
    }

    public Sender withReposUrl(String reposUrl) {
        this.reposUrl = reposUrl;
        return this;
    }

    @JsonProperty("events_url")
    public String getEventsUrl() {
        return eventsUrl;
    }

    @JsonProperty("events_url")
    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public Sender withEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
        return this;
    }

    @JsonProperty("received_events_url")
    public String getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    @JsonProperty("received_events_url")
    public void setReceivedEventsUrl(String receivedEventsUrl) {
        this.receivedEventsUrl = receivedEventsUrl;
    }

    public Sender withReceivedEventsUrl(String receivedEventsUrl) {
        this.receivedEventsUrl = receivedEventsUrl;
        return this;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public Sender withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("site_admin")
    public Boolean getSiteAdmin() {
        return siteAdmin;
    }

    @JsonProperty("site_admin")
    public void setSiteAdmin(Boolean siteAdmin) {
        this.siteAdmin = siteAdmin;
    }

    public Sender withSiteAdmin(Boolean siteAdmin) {
        this.siteAdmin = siteAdmin;
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

    public Sender withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Sender.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
                .append('[');
        sb.append("login");
        sb.append('=');
        sb.append(((this.login == null) ? "<null>" : this.login));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null) ? "<null>" : this.id));
        sb.append(',');
        sb.append("nodeId");
        sb.append('=');
        sb.append(((this.nodeId == null) ? "<null>" : this.nodeId));
        sb.append(',');
        sb.append("avatarUrl");
        sb.append('=');
        sb.append(((this.avatarUrl == null) ? "<null>" : this.avatarUrl));
        sb.append(',');
        sb.append("gravatarId");
        sb.append('=');
        sb.append(((this.gravatarId == null) ? "<null>" : this.gravatarId));
        sb.append(',');
        sb.append("url");
        sb.append('=');
        sb.append(((this.url == null) ? "<null>" : this.url));
        sb.append(',');
        sb.append("htmlUrl");
        sb.append('=');
        sb.append(((this.htmlUrl == null) ? "<null>" : this.htmlUrl));
        sb.append(',');
        sb.append("followersUrl");
        sb.append('=');
        sb.append(((this.followersUrl == null) ? "<null>" : this.followersUrl));
        sb.append(',');
        sb.append("followingUrl");
        sb.append('=');
        sb.append(((this.followingUrl == null) ? "<null>" : this.followingUrl));
        sb.append(',');
        sb.append("gistsUrl");
        sb.append('=');
        sb.append(((this.gistsUrl == null) ? "<null>" : this.gistsUrl));
        sb.append(',');
        sb.append("starredUrl");
        sb.append('=');
        sb.append(((this.starredUrl == null) ? "<null>" : this.starredUrl));
        sb.append(',');
        sb.append("subscriptionsUrl");
        sb.append('=');
        sb.append(((this.subscriptionsUrl == null) ? "<null>" : this.subscriptionsUrl));
        sb.append(',');
        sb.append("organizationsUrl");
        sb.append('=');
        sb.append(((this.organizationsUrl == null) ? "<null>" : this.organizationsUrl));
        sb.append(',');
        sb.append("reposUrl");
        sb.append('=');
        sb.append(((this.reposUrl == null) ? "<null>" : this.reposUrl));
        sb.append(',');
        sb.append("eventsUrl");
        sb.append('=');
        sb.append(((this.eventsUrl == null) ? "<null>" : this.eventsUrl));
        sb.append(',');
        sb.append("receivedEventsUrl");
        sb.append('=');
        sb.append(((this.receivedEventsUrl == null) ? "<null>" : this.receivedEventsUrl));
        sb.append(',');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null) ? "<null>" : this.type));
        sb.append(',');
        sb.append("siteAdmin");
        sb.append('=');
        sb.append(((this.siteAdmin == null) ? "<null>" : this.siteAdmin));
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
        result = ((result * 31) + ((this.receivedEventsUrl == null) ? 0 : this.receivedEventsUrl.hashCode()));
        result = ((result * 31) + ((this.siteAdmin == null) ? 0 : this.siteAdmin.hashCode()));
        result = ((result * 31) + ((this.followingUrl == null) ? 0 : this.followingUrl.hashCode()));
        result = ((result * 31) + ((this.gistsUrl == null) ? 0 : this.gistsUrl.hashCode()));
        result = ((result * 31) + ((this.avatarUrl == null) ? 0 : this.avatarUrl.hashCode()));
        result = ((result * 31) + ((this.organizationsUrl == null) ? 0 : this.organizationsUrl.hashCode()));
        result = ((result * 31) + ((this.reposUrl == null) ? 0 : this.reposUrl.hashCode()));
        result = ((result * 31) + ((this.htmlUrl == null) ? 0 : this.htmlUrl.hashCode()));
        result = ((result * 31) + ((this.subscriptionsUrl == null) ? 0 : this.subscriptionsUrl.hashCode()));
        result = ((result * 31) + ((this.login == null) ? 0 : this.login.hashCode()));
        result = ((result * 31) + ((this.type == null) ? 0 : this.type.hashCode()));
        result = ((result * 31) + ((this.url == null) ? 0 : this.url.hashCode()));
        result = ((result * 31) + ((this.starredUrl == null) ? 0 : this.starredUrl.hashCode()));
        result = ((result * 31) + ((this.gravatarId == null) ? 0 : this.gravatarId.hashCode()));
        result = ((result * 31) + ((this.followersUrl == null) ? 0 : this.followersUrl.hashCode()));
        result = ((result * 31) + ((this.id == null) ? 0 : this.id.hashCode()));
        result = ((result * 31) + ((this.eventsUrl == null) ? 0 : this.eventsUrl.hashCode()));
        result = ((result * 31) + ((this.additionalProperties == null) ? 0 : this.additionalProperties.hashCode()));
        result = ((result * 31) + ((this.nodeId == null) ? 0 : this.nodeId.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Sender) == false) {
            return false;
        }
        Sender rhs = ((Sender) other);
        return ((((((((((((((((((((this.receivedEventsUrl == rhs.receivedEventsUrl)
                || ((this.receivedEventsUrl != null) && this.receivedEventsUrl.equals(rhs.receivedEventsUrl)))
                && ((this.siteAdmin == rhs.siteAdmin)
                        || ((this.siteAdmin != null) && this.siteAdmin.equals(rhs.siteAdmin))))
                && ((this.followingUrl == rhs.followingUrl)
                        || ((this.followingUrl != null) && this.followingUrl.equals(rhs.followingUrl))))
                && ((this.gistsUrl == rhs.gistsUrl) || ((this.gistsUrl != null) && this.gistsUrl.equals(rhs.gistsUrl))))
                && ((this.avatarUrl == rhs.avatarUrl)
                        || ((this.avatarUrl != null) && this.avatarUrl.equals(rhs.avatarUrl))))
                && ((this.organizationsUrl == rhs.organizationsUrl)
                        || ((this.organizationsUrl != null) && this.organizationsUrl.equals(rhs.organizationsUrl))))
                && ((this.reposUrl == rhs.reposUrl) || ((this.reposUrl != null) && this.reposUrl.equals(rhs.reposUrl))))
                && ((this.htmlUrl == rhs.htmlUrl) || ((this.htmlUrl != null) && this.htmlUrl.equals(rhs.htmlUrl))))
                && ((this.subscriptionsUrl == rhs.subscriptionsUrl)
                        || ((this.subscriptionsUrl != null) && this.subscriptionsUrl.equals(rhs.subscriptionsUrl))))
                && ((this.login == rhs.login) || ((this.login != null) && this.login.equals(rhs.login))))
                && ((this.type == rhs.type) || ((this.type != null) && this.type.equals(rhs.type))))
                && ((this.url == rhs.url) || ((this.url != null) && this.url.equals(rhs.url))))
                && ((this.starredUrl == rhs.starredUrl)
                        || ((this.starredUrl != null) && this.starredUrl.equals(rhs.starredUrl))))
                && ((this.gravatarId == rhs.gravatarId)
                        || ((this.gravatarId != null) && this.gravatarId.equals(rhs.gravatarId))))
                && ((this.followersUrl == rhs.followersUrl)
                        || ((this.followersUrl != null) && this.followersUrl.equals(rhs.followersUrl))))
                && ((this.id == rhs.id) || ((this.id != null) && this.id.equals(rhs.id))))
                && ((this.eventsUrl == rhs.eventsUrl)
                        || ((this.eventsUrl != null) && this.eventsUrl.equals(rhs.eventsUrl))))
                && ((this.additionalProperties == rhs.additionalProperties) || ((this.additionalProperties != null)
                        && this.additionalProperties.equals(rhs.additionalProperties))))
                && ((this.nodeId == rhs.nodeId) || ((this.nodeId != null) && this.nodeId.equals(rhs.nodeId))));
    }

}

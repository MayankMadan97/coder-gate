
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
    "tree_id",
    "distinct",
    "message",
    "timestamp",
    "url",
    "author",
    "committer",
    "added",
    "removed",
    "modified"
})
@Generated("jsonschema2pojo")
public class HeadCommitDTO implements Serializable
{

    @JsonProperty("id")
    private String id;
    @JsonProperty("tree_id")
    private String treeId;
    @JsonProperty("distinct")
    private Boolean distinct;
    @JsonProperty("message")
    private String message;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("url")
    private String url;
    @JsonProperty("author")
    private Author__1DTO author;
    @JsonProperty("committer")
    private Committer__1DTO committer;
    @JsonProperty("added")
    private List<Object> added;
    @JsonProperty("removed")
    private List<Object> removed;
    @JsonProperty("modified")
    private List<String> modified;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = 5013414262968641529L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public HeadCommitDTO() {
    }

    /**
     * 
     * @param treeId
     * @param committer
     * @param removed
     * @param added
     * @param author
     * @param distinct
     * @param modified
     * @param id
     * @param message
     * @param url
     * @param timestamp
     */
    public HeadCommitDTO(String id, String treeId, Boolean distinct, String message, String timestamp, String url, Author__1DTO author, Committer__1DTO committer, List<Object> added, List<Object> removed, List<String> modified) {
        super();
        this.id = id;
        this.treeId = treeId;
        this.distinct = distinct;
        this.message = message;
        this.timestamp = timestamp;
        this.url = url;
        this.author = author;
        this.committer = committer;
        this.added = added;
        this.removed = removed;
        this.modified = modified;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("tree_id")
    public String getTreeId() {
        return treeId;
    }

    @JsonProperty("tree_id")
    public void setTreeId(String treeId) {
        this.treeId = treeId;
    }

    @JsonProperty("distinct")
    public Boolean getDistinct() {
        return distinct;
    }

    @JsonProperty("distinct")
    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("author")
    public Author__1DTO getAuthor() {
        return author;
    }

    @JsonProperty("author")
    public void setAuthor(Author__1DTO author) {
        this.author = author;
    }

    @JsonProperty("committer")
    public Committer__1DTO getCommitter() {
        return committer;
    }

    @JsonProperty("committer")
    public void setCommitter(Committer__1DTO committer) {
        this.committer = committer;
    }

    @JsonProperty("added")
    public List<Object> getAdded() {
        return added;
    }

    @JsonProperty("added")
    public void setAdded(List<Object> added) {
        this.added = added;
    }

    @JsonProperty("removed")
    public List<Object> getRemoved() {
        return removed;
    }

    @JsonProperty("removed")
    public void setRemoved(List<Object> removed) {
        this.removed = removed;
    }

    @JsonProperty("modified")
    public List<String> getModified() {
        return modified;
    }

    @JsonProperty("modified")
    public void setModified(List<String> modified) {
        this.modified = modified;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

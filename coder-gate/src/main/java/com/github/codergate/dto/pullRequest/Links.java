
package com.github.codergate.dto.pullRequest;

import java.util.LinkedHashMap;
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
    "self",
    "html",
    "issue",
    "comments",
    "review_comments",
    "review_comment",
    "commits",
    "statuses"
})
@Generated("jsonschema2pojo")
public class Links {

    @JsonProperty("self")
    private Self self;
    @JsonProperty("html")
    private Html html;
    @JsonProperty("issue")
    private Issue issue;
    @JsonProperty("comments")
    private Comments comments;
    @JsonProperty("review_comments")
    private ReviewComments reviewComments;
    @JsonProperty("review_comment")
    private ReviewComment reviewComment;
    @JsonProperty("commits")
    private Commits commits;
    @JsonProperty("statuses")
    private Statuses statuses;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("self")
    public Self getSelf() {
        return self;
    }

    @JsonProperty("self")
    public void setSelf(Self self) {
        this.self = self;
    }

    @JsonProperty("html")
    public Html getHtml() {
        return html;
    }

    @JsonProperty("html")
    public void setHtml(Html html) {
        this.html = html;
    }

    @JsonProperty("issue")
    public Issue getIssue() {
        return issue;
    }

    @JsonProperty("issue")
    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    @JsonProperty("comments")
    public Comments getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(Comments comments) {
        this.comments = comments;
    }

    @JsonProperty("review_comments")
    public ReviewComments getReviewComments() {
        return reviewComments;
    }

    @JsonProperty("review_comments")
    public void setReviewComments(ReviewComments reviewComments) {
        this.reviewComments = reviewComments;
    }

    @JsonProperty("review_comment")
    public ReviewComment getReviewComment() {
        return reviewComment;
    }

    @JsonProperty("review_comment")
    public void setReviewComment(ReviewComment reviewComment) {
        this.reviewComment = reviewComment;
    }

    @JsonProperty("commits")
    public Commits getCommits() {
        return commits;
    }

    @JsonProperty("commits")
    public void setCommits(Commits commits) {
        this.commits = commits;
    }

    @JsonProperty("statuses")
    public Statuses getStatuses() {
        return statuses;
    }

    @JsonProperty("statuses")
    public void setStatuses(Statuses statuses) {
        this.statuses = statuses;
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
        sb.append(Links.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("self");
        sb.append('=');
        sb.append(((this.self == null)?"<null>":this.self));
        sb.append(',');
        sb.append("html");
        sb.append('=');
        sb.append(((this.html == null)?"<null>":this.html));
        sb.append(',');
        sb.append("issue");
        sb.append('=');
        sb.append(((this.issue == null)?"<null>":this.issue));
        sb.append(',');
        sb.append("comments");
        sb.append('=');
        sb.append(((this.comments == null)?"<null>":this.comments));
        sb.append(',');
        sb.append("reviewComments");
        sb.append('=');
        sb.append(((this.reviewComments == null)?"<null>":this.reviewComments));
        sb.append(',');
        sb.append("reviewComment");
        sb.append('=');
        sb.append(((this.reviewComment == null)?"<null>":this.reviewComment));
        sb.append(',');
        sb.append("commits");
        sb.append('=');
        sb.append(((this.commits == null)?"<null>":this.commits));
        sb.append(',');
        sb.append("statuses");
        sb.append('=');
        sb.append(((this.statuses == null)?"<null>":this.statuses));
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

}


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
    "action",
    "number",
    "pull_request",
    "repository",
    "sender",
    "installation"
})
@Generated("jsonschema2pojo")
public class Payload {

    @JsonProperty("action")
    private String action;
    @JsonProperty("number")
    private Integer number;
    @JsonProperty("pull_request")
    private PullRequest pullRequest;
    @JsonProperty("repository")
    private Repository repository;
    @JsonProperty("sender")
    private Sender sender;
    @JsonProperty("installation")
    private Installation installation;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("action")
    public String getAction() {
        return action;
    }

    @JsonProperty("action")
    public void setAction(String action) {
        this.action = action;
    }

    @JsonProperty("number")
    public Integer getNumber() {
        return number;
    }

    @JsonProperty("number")
    public void setNumber(Integer number) {
        this.number = number;
    }

    @JsonProperty("pull_request")
    public PullRequest getPullRequest() {
        return pullRequest;
    }

    @JsonProperty("pull_request")
    public void setPullRequest(PullRequest pullRequest) {
        this.pullRequest = pullRequest;
    }

    @JsonProperty("repository")
    public Repository getRepository() {
        return repository;
    }

    @JsonProperty("repository")
    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    @JsonProperty("sender")
    public Sender getSender() {
        return sender;
    }

    @JsonProperty("sender")
    public void setSender(Sender sender) {
        this.sender = sender;
    }

    @JsonProperty("installation")
    public Installation getInstallation() {
        return installation;
    }

    @JsonProperty("installation")
    public void setInstallation(Installation installation) {
        this.installation = installation;
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
        sb.append(Payload.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("action");
        sb.append('=');
        sb.append(((this.action == null)?"<null>":this.action));
        sb.append(',');
        sb.append("number");
        sb.append('=');
        sb.append(((this.number == null)?"<null>":this.number));
        sb.append(',');
        sb.append("pullRequest");
        sb.append('=');
        sb.append(((this.pullRequest == null)?"<null>":this.pullRequest));
        sb.append(',');
        sb.append("repository");
        sb.append('=');
        sb.append(((this.repository == null)?"<null>":this.repository));
        sb.append(',');
        sb.append("sender");
        sb.append('=');
        sb.append(((this.sender == null)?"<null>":this.sender));
        sb.append(',');
        sb.append("installation");
        sb.append('=');
        sb.append(((this.installation == null)?"<null>":this.installation));
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

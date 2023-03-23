
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
    "ref",
    "before",
    "after",
    "repository",
    "pusher",
    "sender",
    "installation",
    "created",
    "deleted",
    "forced",
    "base_ref",
    "compare",
    "commits",
    "head_commit"
})
@Generated("jsonschema2pojo")
public class PusherPayloadDTO implements Serializable
{

    @JsonProperty("ref")
    private String ref;
    @JsonProperty("before")
    private String before;
    @JsonProperty("after")
    private String after;
    @JsonProperty("repository")
    private RepositoryDTO repositoryDTO;
    @JsonProperty("pusher")
    private PusherDTO pusherDTO;
    @JsonProperty("sender")
    private SenderDTO senderDTO;
    @JsonProperty("installation")
    private InstallationDTO installationDTO;
    @JsonProperty("created")
    private Boolean created;
    @JsonProperty("deleted")
    private Boolean deleted;
    @JsonProperty("forced")
    private Boolean forced;
    @JsonProperty("base_ref")
    private Object baseRef;
    @JsonProperty("compare")
    private String compare;
    @JsonProperty("commits")
    private List<CommitDTO> commitDTOS;
    @JsonProperty("head_commit")
    private HeadCommitDTO headCommitDTO;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = -6003484145973053497L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PusherPayloadDTO() {
    }

    /**
     * 
     * @param pusherDTO
     * @param compare
     * @param before
     * @param created
     * @param forced
     * @param repositoryDTO
     * @param headCommitDTO
     * @param baseRef
     * @param ref
     * @param deleted
     * @param senderDTO
     * @param installationDTO
     * @param commitDTOS
     * @param after
     */
    public PusherPayloadDTO(String ref, String before, String after, RepositoryDTO repositoryDTO, PusherDTO pusherDTO, SenderDTO senderDTO, InstallationDTO installationDTO, Boolean created, Boolean deleted, Boolean forced, Object baseRef, String compare, List<CommitDTO> commitDTOS, HeadCommitDTO headCommitDTO) {
        super();
        this.ref = ref;
        this.before = before;
        this.after = after;
        this.repositoryDTO = repositoryDTO;
        this.pusherDTO = pusherDTO;
        this.senderDTO = senderDTO;
        this.installationDTO = installationDTO;
        this.created = created;
        this.deleted = deleted;
        this.forced = forced;
        this.baseRef = baseRef;
        this.compare = compare;
        this.commitDTOS = commitDTOS;
        this.headCommitDTO = headCommitDTO;
    }

    @JsonProperty("ref")
    public String getRef() {
        return ref;
    }

    @JsonProperty("ref")
    public void setRef(String ref) {
        this.ref = ref;
    }

    @JsonProperty("before")
    public String getBefore() {
        return before;
    }

    @JsonProperty("before")
    public void setBefore(String before) {
        this.before = before;
    }

    @JsonProperty("after")
    public String getAfter() {
        return after;
    }

    @JsonProperty("after")
    public void setAfter(String after) {
        this.after = after;
    }

    @JsonProperty("repository")
    public RepositoryDTO getRepository() {
        return repositoryDTO;
    }

    @JsonProperty("repository")
    public void setRepository(RepositoryDTO repositoryDTO) {
        this.repositoryDTO = repositoryDTO;
    }

    @JsonProperty("pusher")
    public PusherDTO getPusher() {
        return pusherDTO;
    }

    @JsonProperty("pusher")
    public void setPusher(PusherDTO pusherDTO) {
        this.pusherDTO = pusherDTO;
    }

    @JsonProperty("sender")
    public SenderDTO getSender() {
        return senderDTO;
    }

    @JsonProperty("sender")
    public void setSender(SenderDTO senderDTO) {
        this.senderDTO = senderDTO;
    }

    @JsonProperty("installation")
    public InstallationDTO getInstallation() {
        return installationDTO;
    }

    @JsonProperty("installation")
    public void setInstallation(InstallationDTO installationDTO) {
        this.installationDTO = installationDTO;
    }

    @JsonProperty("created")
    public Boolean getCreated() {
        return created;
    }

    @JsonProperty("created")
    public void setCreated(Boolean created) {
        this.created = created;
    }

    @JsonProperty("deleted")
    public Boolean getDeleted() {
        return deleted;
    }

    @JsonProperty("deleted")
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @JsonProperty("forced")
    public Boolean getForced() {
        return forced;
    }

    @JsonProperty("forced")
    public void setForced(Boolean forced) {
        this.forced = forced;
    }

    @JsonProperty("base_ref")
    public Object getBaseRef() {
        return baseRef;
    }

    @JsonProperty("base_ref")
    public void setBaseRef(Object baseRef) {
        this.baseRef = baseRef;
    }

    @JsonProperty("compare")
    public String getCompare() {
        return compare;
    }

    @JsonProperty("compare")
    public void setCompare(String compare) {
        this.compare = compare;
    }

    @JsonProperty("commits")
    public List<CommitDTO> getCommits() {
        return commitDTOS;
    }

    @JsonProperty("commits")
    public void setCommits(List<CommitDTO> commitDTOS) {
        this.commitDTOS = commitDTOS;
    }

    @JsonProperty("head_commit")
    public HeadCommitDTO getHeadCommit() {
        return headCommitDTO;
    }

    @JsonProperty("head_commit")
    public void setHeadCommit(HeadCommitDTO headCommitDTO) {
        this.headCommitDTO = headCommitDTO;
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

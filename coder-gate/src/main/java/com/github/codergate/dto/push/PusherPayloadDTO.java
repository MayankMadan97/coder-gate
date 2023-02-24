
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PusherPayloadDTO.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("ref");
        sb.append('=');
        sb.append(((this.ref == null)?"<null>":this.ref));
        sb.append(',');
        sb.append("before");
        sb.append('=');
        sb.append(((this.before == null)?"<null>":this.before));
        sb.append(',');
        sb.append("after");
        sb.append('=');
        sb.append(((this.after == null)?"<null>":this.after));
        sb.append(',');
        sb.append("repository");
        sb.append('=');
        sb.append(((this.repositoryDTO == null)?"<null>":this.repositoryDTO));
        sb.append(',');
        sb.append("pusher");
        sb.append('=');
        sb.append(((this.pusherDTO == null)?"<null>":this.pusherDTO));
        sb.append(',');
        sb.append("sender");
        sb.append('=');
        sb.append(((this.senderDTO == null)?"<null>":this.senderDTO));
        sb.append(',');
        sb.append("installation");
        sb.append('=');
        sb.append(((this.installationDTO == null)?"<null>":this.installationDTO));
        sb.append(',');
        sb.append("created");
        sb.append('=');
        sb.append(((this.created == null)?"<null>":this.created));
        sb.append(',');
        sb.append("deleted");
        sb.append('=');
        sb.append(((this.deleted == null)?"<null>":this.deleted));
        sb.append(',');
        sb.append("forced");
        sb.append('=');
        sb.append(((this.forced == null)?"<null>":this.forced));
        sb.append(',');
        sb.append("baseRef");
        sb.append('=');
        sb.append(((this.baseRef == null)?"<null>":this.baseRef));
        sb.append(',');
        sb.append("compare");
        sb.append('=');
        sb.append(((this.compare == null)?"<null>":this.compare));
        sb.append(',');
        sb.append("commits");
        sb.append('=');
        sb.append(((this.commitDTOS == null)?"<null>":this.commitDTOS));
        sb.append(',');
        sb.append("headCommit");
        sb.append('=');
        sb.append(((this.headCommitDTO == null)?"<null>":this.headCommitDTO));
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
        result = ((result* 31)+((this.pusherDTO == null)? 0 :this.pusherDTO.hashCode()));
        result = ((result* 31)+((this.compare == null)? 0 :this.compare.hashCode()));
        result = ((result* 31)+((this.before == null)? 0 :this.before.hashCode()));
        result = ((result* 31)+((this.created == null)? 0 :this.created.hashCode()));
        result = ((result* 31)+((this.forced == null)? 0 :this.forced.hashCode()));
        result = ((result* 31)+((this.repositoryDTO == null)? 0 :this.repositoryDTO.hashCode()));
        result = ((result* 31)+((this.headCommitDTO == null)? 0 :this.headCommitDTO.hashCode()));
        result = ((result* 31)+((this.baseRef == null)? 0 :this.baseRef.hashCode()));
        result = ((result* 31)+((this.ref == null)? 0 :this.ref.hashCode()));
        result = ((result* 31)+((this.deleted == null)? 0 :this.deleted.hashCode()));
        result = ((result* 31)+((this.senderDTO == null)? 0 :this.senderDTO.hashCode()));
        result = ((result* 31)+((this.installationDTO == null)? 0 :this.installationDTO.hashCode()));
        result = ((result* 31)+((this.commitDTOS == null)? 0 :this.commitDTOS.hashCode()));
        result = ((result* 31)+((this.after == null)? 0 :this.after.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PusherPayloadDTO) == false) {
            return false;
        }
        PusherPayloadDTO rhs = ((PusherPayloadDTO) other);
        return ((((((((((((((((this.pusherDTO == rhs.pusherDTO)||((this.pusherDTO != null)&&this.pusherDTO.equals(rhs.pusherDTO)))&&((this.compare == rhs.compare)||((this.compare!= null)&&this.compare.equals(rhs.compare))))&&((this.before == rhs.before)||((this.before!= null)&&this.before.equals(rhs.before))))&&((this.created == rhs.created)||((this.created!= null)&&this.created.equals(rhs.created))))&&((this.forced == rhs.forced)||((this.forced!= null)&&this.forced.equals(rhs.forced))))&&((this.repositoryDTO == rhs.repositoryDTO)||((this.repositoryDTO != null)&&this.repositoryDTO.equals(rhs.repositoryDTO))))&&((this.headCommitDTO == rhs.headCommitDTO)||((this.headCommitDTO != null)&&this.headCommitDTO.equals(rhs.headCommitDTO))))&&((this.baseRef == rhs.baseRef)||((this.baseRef!= null)&&this.baseRef.equals(rhs.baseRef))))&&((this.ref == rhs.ref)||((this.ref!= null)&&this.ref.equals(rhs.ref))))&&((this.deleted == rhs.deleted)||((this.deleted!= null)&&this.deleted.equals(rhs.deleted))))&&((this.senderDTO == rhs.senderDTO)||((this.senderDTO != null)&&this.senderDTO.equals(rhs.senderDTO))))&&((this.installationDTO == rhs.installationDTO)||((this.installationDTO != null)&&this.installationDTO.equals(rhs.installationDTO))))&&((this.commitDTOS == rhs.commitDTOS)||((this.commitDTOS != null)&&this.commitDTOS.equals(rhs.commitDTOS))))&&((this.after == rhs.after)||((this.after!= null)&&this.after.equals(rhs.after))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}


package com.github.codergate.dto.push;

import java.io.Serializable;
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
    "event",
    "payload"
})
@Generated("jsonschema2pojo")
public class PushEventPayloadDTO implements Serializable
{

    @JsonProperty("event")
    private String event;
    @JsonProperty("payload")
    private PusherPayloadDTO pusherPayloadDTO;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
    private final static long serialVersionUID = -3940214695871613922L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PushEventPayloadDTO() {
    }

    /**
     * 
     * @param pusherPayloadDTO
     * @param event
     */
    public PushEventPayloadDTO(String event, PusherPayloadDTO pusherPayloadDTO) {
        super();
        this.event = event;
        this.pusherPayloadDTO = pusherPayloadDTO;
    }

    @JsonProperty("event")
    public String getEvent() {
        return event;
    }

    @JsonProperty("event")
    public void setEvent(String event) {
        this.event = event;
    }

    @JsonProperty("payload")
    public PusherPayloadDTO getPayload() {
        return pusherPayloadDTO;
    }

    @JsonProperty("payload")
    public void setPayload(PusherPayloadDTO pusherPayloadDTO) {
        this.pusherPayloadDTO = pusherPayloadDTO;
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
        sb.append(PushEventPayloadDTO.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("event");
        sb.append('=');
        sb.append(((this.event == null)?"<null>":this.event));
        sb.append(',');
        sb.append("payload");
        sb.append('=');
        sb.append(((this.pusherPayloadDTO == null)?"<null>":this.pusherPayloadDTO));
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
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.event == null)? 0 :this.event.hashCode()));
        result = ((result* 31)+((this.pusherPayloadDTO == null)? 0 :this.pusherPayloadDTO.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PushEventPayloadDTO) == false) {
            return false;
        }
        PushEventPayloadDTO rhs = ((PushEventPayloadDTO) other);
        return ((((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))&&((this.event == rhs.event)||((this.event!= null)&&this.event.equals(rhs.event))))&&((this.pusherPayloadDTO == rhs.pusherPayloadDTO)||((this.pusherPayloadDTO != null)&&this.pusherPayloadDTO.equals(rhs.pusherPayloadDTO))));
    }

}

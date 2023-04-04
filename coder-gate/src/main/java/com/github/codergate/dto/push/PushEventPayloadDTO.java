
package com.github.codergate.dto.push;

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
    "event",
    "payload"
})
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

}

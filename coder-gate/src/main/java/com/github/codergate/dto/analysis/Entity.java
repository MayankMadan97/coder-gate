
package com.github.codergate.dto.analysis;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Component",
    "Class"
})
public class Entity implements Serializable
{

    @JsonProperty("Component")
    private String component;
    @JsonProperty("Class")
    private String _class;
    private final static long serialVersionUID = -8756173815302451950L;

    @JsonProperty("Component")
    public String getComponent() {
        return component;
    }

    @JsonProperty("Component")
    public void setComponent(String component) {
        this.component = component;
    }

    @JsonProperty("Class")
    public String getClass_() {
        return _class;
    }

    @JsonProperty("Class")
    public void setClass_(String _class) {
        this._class = _class;
    }

}

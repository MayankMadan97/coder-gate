
package com.github.codergate.dto.analysis;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "DesignSmell"
})
public class DesignSmells implements Serializable
{

    @JsonProperty("DesignSmell")
    private List<DesignSmell> designSmell;
    private final static long serialVersionUID = -2719394590228221177L;

    @JsonProperty("DesignSmell")
    public List<DesignSmell> getDesignSmell() {
        return designSmell;
    }

    @JsonProperty("DesignSmell")
    public void setDesignSmell(List<DesignSmell> designSmell) {
        this.designSmell = designSmell;
    }

}


package com.github.codergate.dto.analysis;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ImplementationSmell"
})
public class ImplementationSmells implements Serializable
{

    @JsonProperty("ImplementationSmell")
    private List<ImplementationSmell> implementationSmell;
    private final static long serialVersionUID = -8267679109978562437L;

    @JsonProperty("ImplementationSmell")
    public List<ImplementationSmell> getImplementationSmell() {
        return implementationSmell;
    }

    @JsonProperty("ImplementationSmell")
    public void setImplementationSmell(List<ImplementationSmell> implementationSmell) {
        this.implementationSmell = implementationSmell;
    }

}

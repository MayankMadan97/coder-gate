
package com.github.codergate.dto.analysis;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ArchSmell"
})
public class ArchSmells implements Serializable
{

    @JsonProperty("ArchSmell")
    private List<ArchSmell> archSmell;
    private final static long serialVersionUID = 2838967659904213053L;

    @JsonProperty("ArchSmell")
    public List<ArchSmell> getArchSmell() {
        return archSmell;
    }

    @JsonProperty("ArchSmell")
    public void setArchSmell(List<ArchSmell> archSmell) {
        this.archSmell = archSmell;
    }

}

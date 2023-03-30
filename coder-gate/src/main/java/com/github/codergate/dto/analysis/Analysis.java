
package com.github.codergate.dto.analysis;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Solution"
})
public class Analysis implements Serializable
{

    @JsonProperty("Solution")
    private Solution solution;
    private final static long serialVersionUID = -4064160691299200697L;

    @JsonProperty("Solution")
    public Solution getSolution() {
        return solution;
    }

    @JsonProperty("Solution")
    public void setSolution(Solution solution) {
        this.solution = solution;
    }

}

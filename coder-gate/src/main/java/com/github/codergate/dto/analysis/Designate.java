
package com.github.codergate.dto.analysis;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Analysis"
})
public class Designate implements Serializable
{

    @JsonProperty("Analysis")
    private Analysis analysis;
    private final static long serialVersionUID = 7361953848338087033L;

    @JsonProperty("Analysis")
    public Analysis getAnalysis() {
        return analysis;
    }

    @JsonProperty("Analysis")
    public void setAnalysis(Analysis analysis) {
        this.analysis = analysis;
    }

}

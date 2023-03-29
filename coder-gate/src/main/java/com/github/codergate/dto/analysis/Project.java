
package com.github.codergate.dto.analysis;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Name",
    "LOC",
    "ComponentCount",
    "TypeCount",
    "MethodCount",
    "SmellDensity",
    "CodeDuplication",
    "MetricVoilations",
    "TotalArchSmellCount",
    "TotalDesignSmellCount",
    "TotalImplSmellCount",
    "DesignSmells",
    "ImplementationSmells",
    "ArchSmells"
})
public class Project implements Serializable
{

    @JsonProperty("Name")
    private String name;
    @JsonProperty("LOC")
    private Integer loc;
    @JsonProperty("ComponentCount")
    private Integer componentCount;
    @JsonProperty("TypeCount")
    private Integer typeCount;
    @JsonProperty("MethodCount")
    private Integer methodCount;
    @JsonProperty("SmellDensity")
    private Double smellDensity;
    @JsonProperty("CodeDuplication")
    private Integer codeDuplication;
    @JsonProperty("MetricVoilations")
    private Integer metricVoilations;
    @JsonProperty("TotalArchSmellCount")
    private Integer totalArchSmellCount;
    @JsonProperty("TotalDesignSmellCount")
    private Integer totalDesignSmellCount;
    @JsonProperty("TotalImplSmellCount")
    private Integer totalImplSmellCount;
    @JsonProperty("DesignSmells")
    private DesignSmells designSmells;
    @JsonProperty("ImplementationSmells")
    private ImplementationSmells implementationSmells;
    @JsonProperty("ArchSmells")
    private ArchSmells archSmells;
    private final static long serialVersionUID = 8751213633971888939L;

    @JsonProperty("Name")
    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("LOC")
    public Integer getLoc() {
        return loc;
    }

    @JsonProperty("LOC")
    public void setLoc(Integer loc) {
        this.loc = loc;
    }

    @JsonProperty("ComponentCount")
    public Integer getComponentCount() {
        return componentCount;
    }

    @JsonProperty("ComponentCount")
    public void setComponentCount(Integer componentCount) {
        this.componentCount = componentCount;
    }

    @JsonProperty("TypeCount")
    public Integer getTypeCount() {
        return typeCount;
    }

    @JsonProperty("TypeCount")
    public void setTypeCount(Integer typeCount) {
        this.typeCount = typeCount;
    }

    @JsonProperty("MethodCount")
    public Integer getMethodCount() {
        return methodCount;
    }

    @JsonProperty("MethodCount")
    public void setMethodCount(Integer methodCount) {
        this.methodCount = methodCount;
    }

    @JsonProperty("SmellDensity")
    public Double getSmellDensity() {
        return smellDensity;
    }

    @JsonProperty("SmellDensity")
    public void setSmellDensity(Double smellDensity) {
        this.smellDensity = smellDensity;
    }

    @JsonProperty("CodeDuplication")
    public Integer getCodeDuplication() {
        return codeDuplication;
    }

    @JsonProperty("CodeDuplication")
    public void setCodeDuplication(Integer codeDuplication) {
        this.codeDuplication = codeDuplication;
    }

    @JsonProperty("MetricVoilations")
    public Integer getMetricVoilations() {
        return metricVoilations;
    }

    @JsonProperty("MetricVoilations")
    public void setMetricVoilations(Integer metricVoilations) {
        this.metricVoilations = metricVoilations;
    }

    @JsonProperty("TotalArchSmellCount")
    public Integer getTotalArchSmellCount() {
        return totalArchSmellCount;
    }

    @JsonProperty("TotalArchSmellCount")
    public void setTotalArchSmellCount(Integer totalArchSmellCount) {
        this.totalArchSmellCount = totalArchSmellCount;
    }

    @JsonProperty("TotalDesignSmellCount")
    public Integer getTotalDesignSmellCount() {
        return totalDesignSmellCount;
    }

    @JsonProperty("TotalDesignSmellCount")
    public void setTotalDesignSmellCount(Integer totalDesignSmellCount) {
        this.totalDesignSmellCount = totalDesignSmellCount;
    }

    @JsonProperty("TotalImplSmellCount")
    public Integer getTotalImplSmellCount() {
        return totalImplSmellCount;
    }

    @JsonProperty("TotalImplSmellCount")
    public void setTotalImplSmellCount(Integer totalImplSmellCount) {
        this.totalImplSmellCount = totalImplSmellCount;
    }

    @JsonProperty("DesignSmells")
    public DesignSmells getDesignSmells() {
        return designSmells;
    }

    @JsonProperty("DesignSmells")
    public void setDesignSmells(DesignSmells designSmells) {
        this.designSmells = designSmells;
    }

    @JsonProperty("ImplementationSmells")
    public ImplementationSmells getImplementationSmells() {
        return implementationSmells;
    }

    @JsonProperty("ImplementationSmells")
    public void setImplementationSmells(ImplementationSmells implementationSmells) {
        this.implementationSmells = implementationSmells;
    }

    @JsonProperty("ArchSmells")
    public ArchSmells getArchSmells() {
        return archSmells;
    }

    @JsonProperty("ArchSmells")
    public void setArchSmells(ArchSmells archSmells) {
        this.archSmells = archSmells;
    }

}

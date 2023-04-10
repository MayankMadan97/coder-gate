package com.github.codergate.dto.insight;

import java.util.LinkedHashMap;
import java.util.Map;

public class DataDTO {
        Map<Long, Double> dataValuesMap = new LinkedHashMap<>();

    public Map<Long, Double> getDataValuesMap() {
        return dataValuesMap;
    }

    public void setDataValuesMap(Map<Long, Double> dataValuesMap) {
        this.dataValuesMap = dataValuesMap;
    }

    @Override
    public String toString() {
        return "DataDTO{" +
                "dataValuesMap=" + dataValuesMap +
                '}';
    }
}

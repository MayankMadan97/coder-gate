package com.github.codergate.dto.insight;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;

public class DataDTO {
        Map<Long, Integer> dataValuesMap = new LinkedHashMap<>();

    public Map<Long, Integer> getDataValuesMap() {
        return dataValuesMap;
    }

    public void setDataValuesMap(Map<Long, Integer> dataValuesMap) {
        this.dataValuesMap = dataValuesMap;
    }

    @Override
    public String toString() {
        return "DataDTO{" +
                "dataValuesMap=" + dataValuesMap +
                '}';
    }
}

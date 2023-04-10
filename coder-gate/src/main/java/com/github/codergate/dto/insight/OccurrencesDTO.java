package com.github.codergate.dto.insight;

import java.util.Map;

public class OccurrencesDTO {
    Map<String, Double> occurrencesSeries;

    @Override
    public String toString() {
        return "OccurrencesDTO{" +
                "occurrencesSeries=" + occurrencesSeries +
                '}';
    }

    public Map<String, Double> getOccurrencesSeries() {
        return occurrencesSeries;
    }

    public void setOccurrencesSeries(Map<String, Double> occurrencesSeries) {
        this.occurrencesSeries = occurrencesSeries;
    }
}

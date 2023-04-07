package com.github.codergate.dto.insight;

import java.util.Map;

public class OccurrencesDTO {
    Map<String, Integer> occurrencesSeries;

    @Override
    public String toString() {
        return "OccurrencesDTO{" +
                "occurrencesSeries=" + occurrencesSeries +
                '}';
    }

    public Map<String, Integer> getOccurrencesSeries() {
        return occurrencesSeries;
    }

    public void setOccurrencesSeries(Map<String, Integer> occurrencesSeries) {
        this.occurrencesSeries = occurrencesSeries;
    }
}

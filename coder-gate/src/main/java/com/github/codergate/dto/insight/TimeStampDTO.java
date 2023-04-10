package com.github.codergate.dto.insight;

import java.util.ArrayList;
import java.util.List;

public class TimeStampDTO {

    List<SeriesDTO> seriesList = new ArrayList<>();

    public List<SeriesDTO> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(List<SeriesDTO> seriesList) {
        this.seriesList = seriesList;
    }

    @Override
    public String toString() {
        return "TimeStampDTO{" +
                "seriesList=" + seriesList +
                '}';
    }
}

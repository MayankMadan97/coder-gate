package com.github.codergate.dto.insight;

import java.util.ArrayList;
import java.util.List;

public class SeriesDTO {
    String name;
    DataDTO data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SeriesDTO{" +
                "name='" + name + '\'' +
                ", data=" + data +
                '}';
    }
}

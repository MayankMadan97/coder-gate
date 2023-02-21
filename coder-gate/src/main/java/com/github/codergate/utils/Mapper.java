package com.github.codergate.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private Mapper() {
    }

    public static ObjectMapper getInstance() {
        if (objectMapper == null)
            objectMapper = new ObjectMapper();

        return objectMapper;
    }
}

package com.github.codergate.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Mapper {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private Mapper() {
    }

    /**
     * singleton instance of object mapper
     * 
     * @return ObjectMapper
     */
    public static ObjectMapper getInstance() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        // objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,
        // false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        return objectMapper;
    }

}

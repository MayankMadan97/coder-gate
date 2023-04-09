package com.github.codergate.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class MapperTest {

    @Test
    public void testGetInstanceNotNull() {
        ObjectMapper objectMapper = Mapper.getInstance();
        assertNotNull(objectMapper);
    }

    @Test
    public void testGetInstanceNull() throws NoSuchFieldException, IllegalAccessException {
        Field field = Mapper.class.getDeclaredField("objectMapper");
        field.setAccessible(true);
        field.set(null, null);
        ObjectMapper objectMapper = Mapper.getInstance();
        assertNotNull(objectMapper);
    }

}
// package com.github.codergate.utils;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import org.junit.Test;
// import static org.junit.Assert.*;

// public class MapperTest {

//     @Test
//     public void testGetInstanceNotNull() {
//         ObjectMapper objectMapper = Mapper.getInstance();
//         assertNotNull(objectMapper);
//     }

//     @Test
//     public void testGetInstanceNull() {
//         ObjectMapper objectMapper = null;
//         try {
//             objectMapper = Mapper.getInstance();
//         } catch (Exception e) {
//             fail("getInstance() should not throw an exception.");
//         }
//         assertNotNull("ObjectMapper instance should not be null", objectMapper);
//     }

//     @Test
//     public void testGetInstanceReturnsSameInstance() {
//         ObjectMapper objectMapper1 = Mapper.getInstance();
//         ObjectMapper objectMapper2 = Mapper.getInstance();
//         assertSame(objectMapper1, objectMapper2);
//     }
// }

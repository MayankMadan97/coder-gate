// package com.github.codergate.utils;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import org.junit.jupiter.api.Test;
// import org.springframework.util.MultiValueMap;

// public class JwtUtilsTest {

//     @Test
//     public void testGetGithubSpecificHeaders() {
//         MultiValueMap<String, String> headers = JwtUtils.getGithubSpecificHeaders();
//         assertNotNull(headers);
//         assertEquals(1, headers.size());
//         assertTrue(headers.containsKey("Accept"));
//         assertEquals("application/vnd.github+json", headers.getFirst("Accept"));
//     }
// }

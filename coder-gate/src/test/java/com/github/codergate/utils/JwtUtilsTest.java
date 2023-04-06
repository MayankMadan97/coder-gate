package com.github.codergate.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.util.MultiValueMap;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class JwtUtilsTest {
    @Test
    public void testGenerateJwtToken() {
        String appid = "296934";
        String expectedToken = JwtUtils.generateJwtToken(appid);
        assertNotNull(expectedToken);
        assertNotEquals(expectedToken, "anonymous");
    }

    @Test
    public void testGetGithubSpecificHeaders() {
        MultiValueMap<String, String> headers = JwtUtils.getGithubSpecificHeaders();
        assertNotNull(headers);
        assertEquals(1, headers.size());
        assertTrue(headers.containsKey("Accept"));
        assertEquals("application/vnd.github+json", headers.getFirst("Accept"));
    }

    @Test
    public void testReadPrivateKey() throws IOException {
        File tmpFile = File.createTempFile("test", ".txt");
        tmpFile.deleteOnExit();
        String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
                "Test private key\n" +
                "-----END RSA PRIVATE KEY-----\n";
        Files.write(tmpFile.toPath(), privateKey.getBytes());
        String expected = "Test private key";
        String actual = JwtUtils.getPrivateKeyContent(tmpFile);
        assertEquals(expected, actual.trim());
    }
}

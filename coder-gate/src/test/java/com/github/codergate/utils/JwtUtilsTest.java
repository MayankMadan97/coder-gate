package com.github.codergate.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;
import org.springframework.util.MultiValueMap;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;



public class JwtUtilsTest {

    @Test
    public void testGenerateJwtToken_ShouldNotBeNull() {
        String appid = "296934";
        String expectedToken = JwtUtils.generateJwtToken(appid);
        assertNotNull(expectedToken);
    }

    @Test
    public void testGetGithubSpecificHeadersNotNull() {
        MultiValueMap<String, String> headers = JwtUtils.getGithubSpecificHeaders();
        assertNotNull(headers);
    }

    @Test
    public void testGetGithubSpecificHeadersSize() {
        MultiValueMap<String, String> headers = JwtUtils.getGithubSpecificHeaders();
        assertEquals(1, headers.size());
    }

    @Test
    public void testGetGithubSpecificHeadersContents() {
        MultiValueMap<String, String> headers = JwtUtils.getGithubSpecificHeaders();
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

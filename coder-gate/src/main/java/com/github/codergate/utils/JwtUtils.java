package com.github.codergate.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils {

        private static final int TEN_MINUTES_EXPIRATION_BUFFER = 600000;
        private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

        /**
         * generated json web token for github APIs using Github app private key and id
         * 
         * @return String
         */
        public static String generateJwtToken(String appId) {
                LOGGER.debug("generateJwtToken : Entering the method");
                String token = null;
                try {
                        Path privateKeyFilePath = Path.of(
                                        System.getProperty("user.dir")
                                                        + "/coder-gate/src/main/resources/private-key.pem");
                        // setting bounty castle provider for private key encryption
                        java.security.Security.addProvider(
                                        new org.bouncycastle.jce.provider.BouncyCastleProvider());
                        // reading private key from resources directory
                        String rsaPrivateKey = readPrivateKey(privateKeyFilePath.toFile());
                        // base64 decoding and re-encoding private key using RSA
                        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(
                                        Base64.getDecoder().decode(rsaPrivateKey));
                        KeyFactory kf = KeyFactory.getInstance("RSA");
                        PrivateKey privKey = kf.generatePrivate(keySpec);
                        // building jwt token
                        token = Jwts.builder()
                                        // issued at current timestamp
                                        .setIssuedAt(new Date())
                                        // expiring in 10 min
                                        .setExpiration(new Date(
                                                        System.currentTimeMillis() + TEN_MINUTES_EXPIRATION_BUFFER))
                                        // issued b y coder gate app
                                        .setIssuer(appId)
                                        // signed using RS 256 algorithm as required by github
                                        .signWith(SignatureAlgorithm.RS256, privKey)
                                        .compact();
                } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
                        LOGGER.error("JwtUtils :: generateJwtToken : Failed to generate jwt token {}",
                                        Arrays.toString(e.getStackTrace()));
                        LOGGER.info("JwtUtils :: generateJwtToken : Setting token as anonymous");
                        token = "anonymous";
                }
                LOGGER.debug("generateJwtToken : Exiting the method with token {}", token);
                return token;
        }

        /**
         * github specific header generator
         * 
         * @return MultiValueMap<String, String>
         */
        public static MultiValueMap<String, String> getGithubSpecificHeaders() {
                MultiValueMap<String, String> githubHeaders = new LinkedMultiValueMap<>();
                githubHeaders.add("Accept", "application/vnd.github+json");
                return githubHeaders;
        }

        /**
         * extracting app private key
         * 
         * @param file
         * @return String
         * @throws IOException
         */
        private static String readPrivateKey(File file) throws IOException {
                String fileContent = new String(Files.readAllBytes(file.toPath()), Charset.defaultCharset());
                String privateKeyStart = "-----BEGIN RSA PRIVATE KEY-----";
                String privateKeyEnd = "-----END RSA PRIVATE KEY-----";
                return fileContent.replace(privateKeyStart, "")
                                .replaceAll(System.lineSeparator(), "")
                                .replace(privateKeyEnd, "");
        }

        private JwtUtils() {
        }
}

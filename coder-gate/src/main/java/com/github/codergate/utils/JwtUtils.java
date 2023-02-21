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

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

    public static String generateJwtToken() {
        String jwtToken = null;
        try {
            String signingKey = readPrivateKey(Path
                    .of(System.getProperty("user.dir")
                            + "/coder-gate/src/main/resources/private-key.pem")
                    .toFile());
            java.security.Security.addProvider(
                    new org.bouncycastle.jce.provider.BouncyCastleProvider());
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(signingKey));
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PrivateKey privKey = kf.generatePrivate(keySpec);
            jwtToken = Jwts.builder()
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 600000))
                    .setIssuer("293667")
                    .signWith(SignatureAlgorithm.RS256, privKey)
                    .compact();
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            LOGGER.error("JwtUtils :: generateJwtToken : Failed to generate jwt token {}",
                    Arrays.toString(e.getStackTrace()));
            LOGGER.info("JwtUtils :: generateJwtToken : Setting token as anonymous");
            jwtToken = "anonymous";
        }
        return jwtToken;
    }

    public static String readPrivateKey(File file)
            throws IOException {
        return new String(Files.readAllBytes(file.toPath()), Charset.defaultCharset())
                .replace("-----BEGIN RSA PRIVATE KEY-----", "")
                .replaceAll(System.lineSeparator(), "")
                .replace("-----END RSA PRIVATE KEY-----", "");
    }

    private JwtUtils() {
    }
}

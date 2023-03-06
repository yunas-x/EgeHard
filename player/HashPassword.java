package org.example.player;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface HashPassword {

    /**
     * Hashes password
     * @param password
     * @return hashed password
     * @throws NoSuchAlgorithmException
     */
     default String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest msg = MessageDigest.getInstance("SHA-256");
        var hash = msg.digest(password.getBytes(StandardCharsets.UTF_8));

        BigInteger num = new BigInteger(1, hash);

        StringBuilder stringBuilder = new StringBuilder(num.toString());

        while (stringBuilder.length() < 32)
            stringBuilder.insert(0, '0');

        return stringBuilder.toString();
    }
}

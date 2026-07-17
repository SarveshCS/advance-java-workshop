package com.workshop.todo.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordUtil {
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private PasswordUtil() {
    }

    public static String createSalt() {
        byte[] saltBytes = new byte[16];
        SECURE_RANDOM.nextBytes(saltBytes);
        return toHex(saltBytes);
    }

    public static String hashPassword(String password, String salt) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = messageDigest.digest((salt + password).getBytes(StandardCharsets.UTF_8));
            return toHex(hashBytes);
        } catch (NoSuchAlgorithmException exception) {
            throw new IllegalStateException("SHA-256 is not available.", exception);
        }
    }

    public static boolean matches(String enteredPassword, String salt, String storedHash) {
        String enteredHash = hashPassword(enteredPassword, salt);
        return enteredHash.equals(storedHash);
    }

    private static String toHex(byte[] bytes) {
        StringBuilder hexBuilder = new StringBuilder();
        for (byte currentByte : bytes) {
            hexBuilder.append(String.format("%02x", currentByte));
        }
        return hexBuilder.toString();
    }
}
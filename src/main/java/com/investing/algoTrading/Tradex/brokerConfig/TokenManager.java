package com.investing.algoTrading.Tradex.brokerConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TokenManager {

    private static final Path ACCESS_TOKEN_FILE = Paths.get("accessToken.txt");

    public static void saveToken(String accessToken) {
        try {
            Files.writeString(ACCESS_TOKEN_FILE, accessToken);
        } catch (IOException e) {
            System.err.println("Failed to save token: " + e.getMessage());
        }
    }

    public static String getToken() {
        try {
            if (Files.exists(ACCESS_TOKEN_FILE)) {
                return Files.readString(ACCESS_TOKEN_FILE).trim();
            }
        } catch (IOException e) {
            System.err.println("Failed to read token: " + e.getMessage());
        }
        return null;
    }

    public static boolean hasToken() {
        return Files.exists(ACCESS_TOKEN_FILE);
    }

    public static void clearToken() {
        try {
            Files.deleteIfExists(ACCESS_TOKEN_FILE);
        } catch (IOException e) {
            System.err.println("Failed to delete token: " + e.getMessage());
        }
    }
}


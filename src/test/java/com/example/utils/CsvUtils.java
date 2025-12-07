package com.example.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CsvUtils {
    public static Map<String, String> getCredentialsByRole(String role) {
        String csvFile = "src/test/resources/data/users.csv";
        String line;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                if (data[0].equalsIgnoreCase(role)) {
                    Map<String, String> creds = new HashMap<>();
                    creds.put("username", data[1].trim());
                    creds.put("password", data[2].trim());
                    return creds;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Role '" + role + "' not found in CSV file.");
    }
}
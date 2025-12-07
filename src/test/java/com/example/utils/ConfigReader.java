package com.example.utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    public static void loadConfig() {
        String env = System.getProperty("env", "qa"); 
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config/" + env + ".properties");
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Could not load config file for environment: " + env);
        }
    }
    public static String getProperty(String key) {
        if (properties == null) loadConfig();
        return properties.getProperty(key);
    }
}
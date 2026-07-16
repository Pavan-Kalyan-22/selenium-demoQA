package com.selenium.practice.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// Loads configuration values from the config.properties file on the classpath.
public class ConfigReader {

    // Holds all configuration properties loaded from the properties file.
    private static final Properties PROPERTIES = new Properties();

    // Reads config.properties once when the class is initialized.
    static {
        try (InputStream inputStream = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (inputStream == null) {
                // Throw an exception if the required properties file is missing.
                throw new IllegalStateException("config.properties not found on classpath");
            }
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            // Wrap any file read problem in a runtime exception.
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    // Returns the trimmed value associated with the given property key.
    public static String getProperty(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null) {
            // Fail fast when a requested property is missing.
            throw new IllegalArgumentException("Property not found: " + key);
        }
        return value.trim();
    }
}

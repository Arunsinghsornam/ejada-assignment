package com.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConfigReader {

    private static final Properties properties = new Properties();
    private static final String CONFIG_PATH = "src/test/resources/config/config.properties";

    static {
        try (InputStream input = new FileInputStream(CONFIG_PATH)) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties from: " + CONFIG_PATH, e);
        }
    }

    private ConfigReader() {
        // Utility class - no instantiation
    }

  
    public static String get(String key) {
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.isEmpty()) {
            return systemValue;
        }
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in config.properties");
        }
        return value.trim();
    }

    public static String getBrowser() {
        return get("browser").toLowerCase();
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(get("headless"));
    }

    public static String getBaseUrl() {
        return get("base.url");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(get("implicit.wait"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(get("explicit.wait"));
    }

    public static int getPageLoadTimeout() {
        return Integer.parseInt(get("page.load.timeout"));
    }

    public static String getApiBaseUrl() {
        return get("api.base.url");
    }

    public static boolean isScreenshotOnFailure() {
        return Boolean.parseBoolean(get("screenshot.on.failure"));
    }
}

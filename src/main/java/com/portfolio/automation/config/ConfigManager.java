package com.portfolio.automation.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static final Properties properties = new Properties();
    private static ConfigManager instance;

    private ConfigManager() {
        loadProperties();
    }

    public static synchronized ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    private void loadProperties() {
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("config/config.properties")) {
            if (stream != null) {
                properties.load(stream);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public String get(String key) {
        String systemProp = System.getProperty(key);
        if (systemProp != null && !systemProp.isBlank()) {
            return systemProp;
        }
        return properties.getProperty(key);
    }

    public String get(String key, String defaultValue) {
        String value = get(key);
        return (value != null && !value.isBlank()) ? value : defaultValue;
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        String value = get(key);
        if (value == null || value.isBlank()) return defaultValue;
        return Boolean.parseBoolean(value);
    }

    public int getInt(String key, int defaultValue) {
        String value = get(key);
        if (value == null || value.isBlank()) return defaultValue;
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public String getBrowser() {
        return get("browser", "chrome");
    }

    public String getBaseUrl() {
        return get("baseUrl", "https://the-internet.herokuapp.com/");
    }

    public int getExplicitWait() {
        return getInt("explicitWait", 10);
    }

    public boolean isHeadless() {
        return getBoolean("headless", false);
    }

    public String getEnvironment() {
        return get("environment", "demo");
    }
}
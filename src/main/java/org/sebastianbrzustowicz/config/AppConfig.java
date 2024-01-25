package org.sebastianbrzustowicz.config;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static final String CONFIG_FILE_PATH = "config.properties";
    @Getter
    private static final AppConfig instance = new AppConfig();
    private final Properties properties;

    private AppConfig() {
        properties = new Properties();
        try (InputStream input = AppConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE_PATH)) {
            if (input != null) {
                properties.load(input);
            } else {
                System.err.println("Unable to find " + CONFIG_FILE_PATH);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getWebSocketUrl() {
        return getProperty("webSocketUrl");
    }

    private String getProperty(String key) {
        return properties.getProperty(key);
    }
}
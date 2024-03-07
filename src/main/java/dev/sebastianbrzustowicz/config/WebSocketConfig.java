package dev.sebastianbrzustowicz.config;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WebSocketConfig {
    private static final String CONFIG_FILE_PATH = "websocket.properties";
    @Getter
    private static final WebSocketConfig instance = new WebSocketConfig();
    private final Properties properties;

    private WebSocketConfig() {
        properties = new Properties();
        try (InputStream input = WebSocketConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE_PATH)) {
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
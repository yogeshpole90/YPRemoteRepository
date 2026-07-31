package com.agat.los.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static Properties props;
    private static final String CONFIG_PATH = "src/test/resources/config.properties";
    private static final String CONFIG_PATH_ABSOLUTE = "C:/Users/Yogesh.Pole/eclipse-workspace/Agat_LOS_automation/src/test/resources/config.properties";

    static {
        try {
            props = new Properties();
            // Try relative path first (Eclipse), then absolute (Jenkins)
            java.io.File configFile = new java.io.File(CONFIG_PATH);
            if (!configFile.exists()) configFile = new java.io.File(CONFIG_PATH_ABSOLUTE);
            if (configFile.exists()) {
                try (FileInputStream fis = new FileInputStream(configFile)) {
                    props.load(fis);
                }
            } else {
                try (InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {
                    if (is == null) throw new RuntimeException("config.properties not found");
                    props.load(is);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }

    public static void set(String key, String value) {
        props.setProperty(key, value);
        java.io.File configFile = new java.io.File(CONFIG_PATH);
        if (!configFile.exists()) configFile = new java.io.File(CONFIG_PATH_ABSOLUTE);
        try (FileOutputStream fos = new FileOutputStream(configFile)) {
            props.store(fos, null);
        } catch (Exception e) {
            System.err.println("Failed to save config: " + e.getMessage());
        }
    }
}

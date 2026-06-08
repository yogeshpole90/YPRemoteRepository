package com.ebid.lcs.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static Properties props;
    private static final String CONFIG_PATH = "src/test/resources/config.properties";

    static {
        try {
            props = new Properties();
            try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
                props.load(fis);
            } catch (Exception e) {
                try (InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties")) {
                    if (is == null) {
                        throw new RuntimeException("Failed to load config.properties from filesystem or classpath", e);
                    }
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
        try (FileOutputStream fos = new FileOutputStream(CONFIG_PATH)) {
            props.store(fos, null);
        } catch (Exception e) {
            System.err.println("Failed to save config: " + e.getMessage());
        }
    }
}

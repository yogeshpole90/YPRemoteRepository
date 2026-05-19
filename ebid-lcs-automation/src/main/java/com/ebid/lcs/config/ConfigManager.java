package com.ebid.lcs.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static Properties props;

    static {
        try {
            props = new Properties();
            try {
                props.load(new FileInputStream("src/test/resources/config.properties"));
            } catch (Exception e) {
                InputStream is = ConfigManager.class.getClassLoader().getResourceAsStream("config.properties");
                if (is == null) {
                    throw new RuntimeException("Failed to load config.properties from filesystem or classpath", e);
                }
                props.load(is);
                is.close();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}

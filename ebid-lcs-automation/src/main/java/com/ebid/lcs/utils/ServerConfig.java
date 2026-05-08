package com.ebid.lcs.utils;

import java.net.HttpURLConnection;
import java.net.URL;
import com.ebid.lcs.config.ConfigManager;

public class ServerConfig {

    public static String getActiveServer() {
        String url = ConfigManager.get("base.url");
        System.out.println("=================================================");
        System.out.println("SERVER HEALTH CHECK - Finding Active Server...");
        System.out.println("=================================================");
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            int code = conn.getResponseCode();
            System.out.println("  Server UP: " + url + " (Response: " + code + ")");
            System.out.println("  >> Using Server: " + url);
        } catch (Exception e) {
            System.out.println("  Server DOWN: " + url);
        }
        System.out.println("=================================================");
        return url;
    }
}

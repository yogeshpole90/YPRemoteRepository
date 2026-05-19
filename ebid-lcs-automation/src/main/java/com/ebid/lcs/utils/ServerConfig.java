package com.ebid.lcs.utils;

import java.net.HttpURLConnection;
import java.net.URI;

import com.ebid.lcs.config.ConfigManager;

public class ServerConfig {

    private static final String[] SERVERS = {
            "http://10.10.230.16:8181/lcs-finairoLending-1.0.1",
            "http://10.10.230.14:8181/lcs-finairoLending-1.0.1"
    };

    private static final int TIMEOUT = 5000;
    private static String activeServer = null;

    public static String getActiveServer() {
        if (activeServer != null)
            return activeServer;

        System.out.println("=================================================");
        System.out.println("SERVER HEALTH CHECK - Finding Active Server...");
        System.out.println("=================================================");

        for (String server : SERVERS) {
            try {
                HttpURLConnection conn = (HttpURLConnection) URI.create(server).toURL().openConnection();
                conn.setConnectTimeout(TIMEOUT);
                conn.setReadTimeout(TIMEOUT);
                conn.setRequestMethod("GET");
                int code = conn.getResponseCode();
                conn.disconnect();

                if (code == 200) {
                    activeServer = server;
                    System.out.println("  \u2705 Server UP   : " + server + " (Response: " + code + ")");
                    System.out.println("  >> Using Server: " + server);
                    System.out.println("=================================================");
                    return activeServer;
                } else {
                    System.out.println("  \u274c Server DOWN : " + server + " (Response: " + code + ")");
                }
            } catch (Exception e) {
                System.out.println("  \u274c Server DOWN : " + server + " (Error: " + e.getMessage() + ")");
            }
        }

        // Fallback to config
        activeServer = ConfigManager.get("base.url");
        System.out.println("  \u26a0\ufe0f No server responded - Using default: " + activeServer);
        System.out.println("=================================================");
        return activeServer;
    }
}

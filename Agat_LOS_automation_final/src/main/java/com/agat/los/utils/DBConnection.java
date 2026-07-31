package com.agat.los.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.agat.los.config.ConfigManager;

public class DBConnection {

    private static final String DB_URL = ConfigManager.get("db.url");
    private static final String DB_USER = ConfigManager.get("db.user");
    private static final String DB_PASS = ConfigManager.get("db.password");

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    public static ResultSet executeQuery(String query) throws Exception {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }

    public static void close(AutoCloseable... resources) {
        for (AutoCloseable r : resources) {
            try { if (r != null) r.close(); } catch (Exception e) {}
        }
    }
}

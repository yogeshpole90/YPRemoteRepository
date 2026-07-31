package com.ebid.lcs.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.ebid.lcs.config.ConfigManager;

public class DBConnection {

    private static final String DB_URL = ConfigManager.get("db.url") != null
            ? ConfigManager.get("db.url")
            : "jdbc:sqlserver://10.10.230.12:1433;databaseName=MIGRATION_TEST_LCS;encrypt=false";
    private static final String DB_USER = ConfigManager.get("db.user") != null
            ? ConfigManager.get("db.user")
            : "sa";
    private static final String DB_PASS = ConfigManager.get("db.password") != null
            ? ConfigManager.get("db.password")
            : "Ebidadmin@123";

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
            try {
                if (r != null) r.close();
            } catch (Exception e) {
                // ignore
            }
        }
    }
}

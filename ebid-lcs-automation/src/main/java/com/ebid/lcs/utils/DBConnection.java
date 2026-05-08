package com.ebid.lcs.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {

    private static final String DB_URL = "jdbc:sqlserver://10.10.230.16:1433;databaseName=ABORABORAEBID;encrypt=false";
    private static final String DB_USER = "sa";
    private static final String DB_PASS = "sa@12345";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    public static ResultSet executeQuery(String query) throws Exception {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }
}

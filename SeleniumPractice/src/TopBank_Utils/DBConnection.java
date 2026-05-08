package TopBank_Utils;



import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    // Database connection details
    private static final String URL =
            "jdbc:sqlserver://172.21.0.90:1439;instanceName=MSSQL_2022;databaseName=TOP_BANK_AUTOMATION_QC;encrypt=false";

    private static final String USER = "TOP_QC";
    private static final String PASS = "sqlserver#123";

    public static Connection getConnection() {

        Connection connection = null;

        try {
            // Load SQL Server JDBC Driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Create database connection
            connection = DriverManager.getConnection(URL, USER, PASS);

            System.out.println("Database connection established successfully.");

        } catch (Exception e) {
            System.out.println("Error while connecting to database.");
            e.printStackTrace();
        }

        return connection;
    }
}

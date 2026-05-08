package Utility_Package;

import java.sql.*;
import java.util.Scanner;

public class DBUtil {

	static String url = "jdbc:sqlserver://10.10.230.12:1433;databaseName=MIG_DE_LCS;encrypt=false";
	static String user = "sa";
	static String password = "Ebidadmin@123";

	public static void fetchData(String tableName, String moduleName) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Customer Code for " + moduleName + ": ");
		String custCode = sc.nextLine();

		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("✅ DB Connected! | " + moduleName + " | Table: " + tableName + " | CustomerCode: " + custCode);

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " WHERE customerCode = '" + custCode + "'");

			int colCount = rs.getMetaData().getColumnCount();
			int rowNum = 1;
			while (rs.next()) {
				System.out.println("---------- Row " + rowNum + " ----------");
				for (int i = 1; i <= colCount; i++)
					System.out.printf("  %-30s : %s%n", rs.getMetaData().getColumnName(i), rs.getString(i));
				System.out.println();
				rowNum++;
			}
			System.out.println("Total Rows: " + (rowNum - 1));
			rs.close(); stmt.close(); conn.close();
			System.out.println("✅ DB Closed!");
		} catch (Exception e) {
			System.out.println("❌ DB Error: " + e.getMessage());
		}
		sc.close();
	}
}

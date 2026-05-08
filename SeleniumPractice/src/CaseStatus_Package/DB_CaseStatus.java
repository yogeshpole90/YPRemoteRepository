package CaseStatus_Package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.testng.annotations.Test;
public class DB_CaseStatus {

	@Test
	public static class DB_Test {

		static String url = "jdbc:sqlserver://10.10.230.12:1433;databaseName=MIG_DE_LCS;encrypt=false";
		static String id = "sa";
		static String pwd = "Ebidadmin@123";

		//entered values
		String caseNo1 = "00000002";
		String authStatus1 ="A";
		String isActive1 = "1";
		String loanAcNo1 = "002102000271" ;
		String remarks1 ="na";
		String reminderType1 = "CALL";

		public void test() throws SQLException
		{
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter Query Here:- ");
			String query = sc.nextLine();
			//"SELECT * FROM d310039 where reminderNo ='RM00000133'";

			Connection conn = DriverManager.getConnection(url, id ,  pwd);

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while(rs.next())
			{
				//columns values
				String caseN =rs.getString("caseNo");
				String authS = rs.getString("authStatus");
				String loanA = rs.getString("loanAcNo");
				String Rem = rs.getString("remarks");
				String remT = rs.getString("reminderType");

				if(caseNo1.equals(caseN) && authStatus1.equals(authS) &&
						loanAcNo1.equals(loanA) && reminderType1.equals(remT) &&
						remarks1.equals(Rem))
				{
					System.out.println("DB validated Succesfully.");
					System.out.println("Data is matched.");
				}
				else
				{
					System.out.println("Data is NOT Matched. ");
				}



			}

		}
	}


}

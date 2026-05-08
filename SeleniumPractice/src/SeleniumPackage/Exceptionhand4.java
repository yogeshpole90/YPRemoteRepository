package SeleniumPackage;

public class Exceptionhand4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try 
		{
		int a= 1/0;
		}
		catch(Exception e)
		{
			System.out.println("Exception is :- "+e);
			System.out.println("Exception handled...");
		}
		finally {
			System.out.println("Exception Finnaly block Executed...");
		}

	}

}

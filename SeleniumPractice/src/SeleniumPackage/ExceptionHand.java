package SeleniumPackage;

public class ExceptionHand {
	public static void main(String[] args) {
		try 
		{
			int i = 10 / 0;
		} 
		catch (Exception e) 
		{
			System.out.println("e :-"+ e);

		}
		finally 
		{
			System.out.println("It will execute always...");

		}

	}

}

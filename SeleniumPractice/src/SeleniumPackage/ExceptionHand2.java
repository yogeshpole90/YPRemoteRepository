package SeleniumPackage;

public class ExceptionHand2 {
	public static void main(String[] args) {

		try 
		{
			int i=20/2;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			System.out.println("i will executed...");
			try 
			{
				int a=5/0;

			}
			catch(Exception f)
			{
				System.out.println("====");
				f.printStackTrace();

			}
		}
		System.out.println("finally execute at closing");
		
		
	}

}

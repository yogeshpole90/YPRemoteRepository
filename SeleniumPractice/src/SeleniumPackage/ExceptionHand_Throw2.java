package SeleniumPackage;

public class ExceptionHand_Throw2 {


	public static void eligible(int age) {

		try {
			if(age<18)
			{
				throw new ArithmeticException("Not Valid");

			}
			else
			{
				System.out.println("Eligible for Voting...");
			}
		} 
		catch (ArithmeticException e) 
		{

			e.printStackTrace();
		}

	}	


	public static void main(String[] args) throws InterruptedException,Exception,ArithmeticException {
		Thread.sleep(3000);
		eligible(15); //call mtd and pass value
		eligible(19);//if try/catch not use then it will not execute 2nd line of code
		
		
		
	}

}









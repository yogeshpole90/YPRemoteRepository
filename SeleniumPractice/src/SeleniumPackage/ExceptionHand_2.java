package SeleniumPackage;

public class ExceptionHand_2 {



	public static void eligible(int age) 
	{
		if(age<18)
		{
			System.out.println("not eligible for voting");

		}
		else
		{
			System.out.println("Eligible for Voting...");
		}
	} 





	public static void main(String[] args) {
		//Thread.sleep(3000);
		eligible(15); //call mtd and pass value
		//eligible(19);//if try/catch not use then it will not execute 2nd line of code



	}

}


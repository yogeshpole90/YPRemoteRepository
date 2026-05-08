package seleniumClasses;

public class Execption_hand1 {

	public static void main(String[] args) {

		try
		{
			int data = 50/0; //arithmetic exception
		}
		catch(ArithmeticException b)
		{
			System.out.println(b);
		}

		catch(Exception a)
		{
			System.out.println("Error is :- " + a);
		}
		
		try {
			int b =10/0;
		}
		catch(Exception a)
		{
			System.out.println(a);
		}
		finally {
			System.out.println("finally always executes");
		}






	}

}

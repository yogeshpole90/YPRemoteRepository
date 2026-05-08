package seleniumClasses;

public class Exception_hand2 {

	public static void main(String[] args) {

		try
		{
			int c = 5/0;
		}
		catch(ArithmeticException a)
		{
			System.out.println(a);
		}
		System.out.println("Exception gets handled and proceeds with next line without Disturb");
		
		for(int i=0;i<=5 ;i++)
		{
			System.out.println(i);
		}
		



	}

}

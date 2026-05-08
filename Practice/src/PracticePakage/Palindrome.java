package PracticePakage;

public class Palindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		long a =717;
		long rev=0;
		long temp=a;
		
		while(a!=0)
		{
			rev=rev*10 + a%10;
			a=a/10;
//div by 10 always gives u last digit as Reminder
		
//div by 10 always gives you quotient by removing last digit
			
		}
		if(temp==rev)
		{
			System.out.println("Palindrome");
		}
		else
		{
			System.out.println("Not a Palindrome");
		}
		
		
	}

}

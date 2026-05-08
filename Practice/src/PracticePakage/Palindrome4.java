package PracticePakage;

public class Palindrome4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int a = 121121;
		int temp=a;
		int rev=0;
		
		while(a!=0)
		{
			rev = rev*10 + a%10;
			a=a/10;
		}
				
		if(temp == rev)
		{
			System.out.println(rev+ " is Palindrome number");
		}
		else
		{
			System.out.println(rev+ "  is not a Palindrome number");
		}
		
		
		
		
	}

}

package PracticePakage;

public class Palindrome2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int a = 1771;
		int temp=a;
		int rev=0;
//temp == rev
		while(a!=0)
		{
			
		  rev = rev*10 + a%10;
		  a=a/10;
		}
		if(temp == rev)
		{
			System.out.println("Palindrome");
		}
		else
		{
			System.out.println("Not a Palindrome");
		}
		
		
		
	}

}

package PracticePakage;

public class Palindrome5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//121=rev
		int a=151;
		int temp=a;
		int rev=0;
		
		while(a!=0)
		{
			int c=a%10;
			rev=rev*10 + c;
			a=a/10;
			
		}
		if(temp == rev)
		{
			System.out.println(rev + " is Palindrome No.");
		}
		else
		{
			System.out.println(rev + " is not a Plaindrome No.");
		}

	}

}

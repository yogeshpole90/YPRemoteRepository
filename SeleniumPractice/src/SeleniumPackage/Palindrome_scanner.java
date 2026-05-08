package SeleniumPackage;

import java.util.Scanner;

public class Palindrome_scanner {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Plz Enter Any Number:- ");
		int no = sc.nextInt();
		int temp = no;
		int rev = 0;
		while(temp != 0)
		{
			rev  = rev*10 + temp%10;
			temp = temp/10;
			
		}
			
		if(rev == no)
		{
			System.out.println("Yes, it is a Palindrome Number.  ");
		}
		else
		{
			System.out.println("No, It's Not a Palindrome Number. ");
		}
	}

}

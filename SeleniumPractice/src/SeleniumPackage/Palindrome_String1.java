package SeleniumPackage;

import java.util.Scanner;

public class Palindrome_String1 {
	public static void main(String[] args) {


		Scanner sc = new Scanner(System.in);
		System.out.print("Enter String Here:- ");

		String name = sc.nextLine();
		StringBuilder sb = new StringBuilder(name);
		System.out.println(name);
		String rev = sb.reverse().toString();

		if(name.equals(rev))
		{
			System.out.println("Yes, P. ");

		}
		else 
		{
			System.out.println("No, P. "); 
		}






	}
}
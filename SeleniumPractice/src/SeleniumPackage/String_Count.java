package SeleniumPackage;

import java.util.Scanner;

public class String_Count {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter String Here:- ");
		String name=sc.nextLine();
		int count=0;
		for(int i=0;i<name.length();i++)
		{
			count++;
			
		}
		System.out.println("Total words:- " +count);
		
		
	}

}

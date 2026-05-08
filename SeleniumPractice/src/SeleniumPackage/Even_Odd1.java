package SeleniumPackage;

import java.util.Scanner;

public class Even_Odd1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number Here:- ");
		int no = sc.nextInt();
		
		if(no%2==0)
		{
			System.out.println("Yes, it is Even Number.");
		}
		else {
			System.out.println("Odd number. ");
		}
	}

}

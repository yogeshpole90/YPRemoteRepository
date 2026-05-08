package SeleniumPackage;

import java.util.Scanner;

public class Largest_three {
	public static void main(String[] args) {

		int a,b,c;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter 1st Number:- ");
		a=sc.nextInt();
		
		System.out.println("Enter 2nd Number:- ");
		b=sc.nextInt();
		
		System.out.println("Enter 3rd Number:- ");
		c=sc.nextInt();

		String largest = (a>b && a>c) ? "a is largest" : (b>a && b>c) ? "b is largest" :
			(c>a && c>b) ? "c is largest" : "Equal numbers";

		System.out.println(largest);








	}
}
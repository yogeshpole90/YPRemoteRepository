package SeleniumPackage;

import java.util.Scanner;

public class Sum_Of_Digit {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Digit Here:- ");
		int no = sc.nextInt();
		int temp = no;
		int sum=0;
		while(temp != 0)
		{
			int rem = temp%10;
			sum= sum + rem;
			temp = temp/10;
			
		}
		System.out.println(sum);
	}

}

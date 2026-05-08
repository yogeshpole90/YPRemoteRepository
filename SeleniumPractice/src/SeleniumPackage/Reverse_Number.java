package SeleniumPackage;

import java.util.Scanner;

public class Reverse_Number {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Number here:- ");
		int no=sc.nextInt();
		int temp=no;
		int rev=0;
		while(temp != 0)
		{
			rev=rev*10+ temp%10;
			temp=temp/10;

		}
		System.out.println(rev);



	}

}

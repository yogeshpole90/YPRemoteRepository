package SeleniumPackage;

import java.util.Scanner;

public class Count_Digits {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number here:- ");
		int no = sc.nextInt();
		int count=0;
		int temp=no;
		while(temp !=0)
		{
			temp=temp/10;
			count++;
			
		}
		System.out.println(count);
		
		
	}

}

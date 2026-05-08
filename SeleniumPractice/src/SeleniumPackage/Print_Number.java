package SeleniumPackage;

import java.util.Scanner;

public class Print_Number {
	public static void main(String[] args) {
		Scanner sc = new  Scanner(System.in);
		System.out.print("Enter Number");
		int no=sc.nextInt();
		
		for(int i =1;i<=no;i++)
		{
			System.out.print(i+" , ");
			
		}
		
	}

}

package SeleniumPackage;

import java.util.Scanner;

public class Multi_1 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Number here:- ");
		int no = sc.nextInt();
		
		for(int i=1;i<=10;i++)
		{
			int table= no*i;
			System.out.println(table);
		}
	
	
	}

}

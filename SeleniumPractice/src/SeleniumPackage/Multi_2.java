package SeleniumPackage;

import java.util.Scanner;

public class Multi_2 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Number here:- ");
		int no=sc.nextInt();
		for(int i=1;i<=10;i++)
		{
			int mul=no*i;
			System.out.println(mul);

			
		}
		
	}

}

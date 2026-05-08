package SeleniumPackage;

import java.util.Scanner;

public class Factorial_2 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number here :- ");
		int no = sc.nextInt();//7
		int fact=1;
		for(int i=1;i<=no;i++)
		{
		     fact = fact*i;
		    
			
		}
		System.out.print("Factorial is :- ");
		System.out.print(fact);

		
		
	}

}

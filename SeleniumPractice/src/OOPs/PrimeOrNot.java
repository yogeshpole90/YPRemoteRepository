package OOPs;

import java.util.Scanner;

public class PrimeOrNot {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Number Here:- ");
		int no = sc.nextInt();
		
		boolean isPrime = true;
		
		for(int i=2;i <= no/2;i++)
		{
			if(no%i==0)
			{
				isPrime=false;
				break;
			}
			else 
			{
				isPrime=true;
				break;
			}
			
		}
		
		if(isPrime)
		{
			System.out.println(no+" > Yes.");
		}
		else
		{
			System.out.println(no+" > No.");
		}
		
		
		
	}

}

package SeleniumPackage;

import java.util.Scanner;

public class isPrime1 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Number here:- ");
		int no = sc.nextInt();
		
		boolean isPrime = true;
		
		if(no<=1)
		{
			isPrime=false;
		}
		else
		{
			for(int i=2;i<=no/2;i++)
			{
				if(no%i==0 )
				{
					isPrime=false;
					//break;
				}
			}
			
		}
		
		if(isPrime)
		{
			System.out.println("It is.");
		}
		else {
			System.out.println("It is Not. ");
		}
		
		
	}

}

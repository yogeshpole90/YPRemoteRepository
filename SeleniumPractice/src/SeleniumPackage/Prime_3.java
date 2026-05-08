package SeleniumPackage;

import java.util.Scanner;

public class Prime_3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number Here:- ");
		int no = sc.nextInt();
		
		boolean isPrime = true;//consider it is true
		if(no <= 1)
		{
			isPrime=false;
		}
		else if(no >=2 && no<=100 )
		{
			//no=2
			for(int i=2;i<=no/2;i++) //1, self number = divide
				
			{
				if(no%i==0)
				{
					isPrime=false;
				}
				
			}
		}
		if(isPrime)
		{
			System.out.println("Yes, P. ");
		}
		else
		{
			System.out.println("Not a P. ");
		}
		
	}

}

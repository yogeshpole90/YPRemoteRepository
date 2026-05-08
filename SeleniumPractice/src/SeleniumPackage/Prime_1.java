package SeleniumPackage;

import java.util.Scanner;

public class Prime_1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int no;
		try 
		{
			System.out.println("Enter Number here:- ");
			no = sc.nextInt();
		} 
		catch(Exception e) 
		{
			System.out.println("Invalid Input ❌");
			sc.close();
			return; // program yahi stop
		}
		boolean isPrime=true;


		if(no<=1)
		{
			isPrime=false;

		}
		else
		{
			for(int j=2;j<=no/2;j++)
			{
				if(no%j==0)
				{
					isPrime =false;
					break;
				}

			}
		}


		if(isPrime)
		{
			System.out.println("Yes. \u2714");
		}
		else {
			System.out.println("No. ");
		}

	}
}
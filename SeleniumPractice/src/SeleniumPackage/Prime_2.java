package SeleniumPackage;

import java.util.Scanner;

public class Prime_2 {
	
	public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter Number Here:- ");
	int no = sc.nextInt();
	
	boolean isPrime = true;
	
	if(no<=1)
	{
		isPrime =false;
	}
	else if(no>1 && no <=100)
	{
		for(int i=2;i<= no/2;i++)
		{
			if(no%i==0)
			{
				isPrime=false;
			}
		}
	}
	
	
	
	if(isPrime)
	{
		System.out.println("Yes. ");
	}
	else {
		System.out.println("NO. ");
	}
	

}
}
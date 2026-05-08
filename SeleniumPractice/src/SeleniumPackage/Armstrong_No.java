package SeleniumPackage;

import java.util.Scanner;

public class Armstrong_No {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Plz Enter Armstrong number :- ");
		int armno = sc.nextInt();
		int temp = armno;
		int sum=0;
		int rem =0;
		while(temp != 0 )
		{
			rem = temp%10;
			sum = sum +rem*rem*rem; 
			temp = temp/10;
			
		}
		
		System.out.println(sum);
		if(sum==armno)
		{
			System.out.println("Yes, It is Armstrong Number. ");
		}
		else
		{
			System.out.println("No, It is Not. ");
		}
		
		
		
		
		
		
	}

}

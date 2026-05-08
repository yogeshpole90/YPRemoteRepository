package PracticePakage;

import java.util.Scanner;

public class Scanner3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc =new Scanner(System.in);
		
		System.out.println("Enter 1st Int value : ");
		int a = sc.nextInt();
		
		System.out.println("Enter 2nd Int Value : ");
		int b =sc.nextInt();
		
		System.out.println("Multiplication of Two Values : ");
		int multi = a*b;
		System.out.println("Answer is : " + multi);
		
	}

}

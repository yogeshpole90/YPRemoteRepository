package PracticePakage;

import java.util.Scanner;

public class Scanner2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Your Name : ");
		String a = sc.nextLine();
		
		System.out.println("Enter Your Surname : ");
		String b = sc.nextLine();
		
		System.out.println("Plz Check Spelling : " + a +" "+ b);
		
	}

}

package SeleniumPackage;

import java.util.Scanner;

public class Fizz_Buzz {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Number here:- ");
		int no = sc.nextInt();
		for(int i = 1; i <= 100; i++) {

			if(i % 3 == 0 && i % 5 == 0) {
				System.out.println(" = FizzBuzz");
			}
			else if(i % 3 == 0) {
				System.out.println(i+" = Fizz");
			}
			else if(i % 5 == 0) {
				System.out.println(i+" = Buzz");
			}
			else {
				System.out.println(i+" = Niether buzz/fizz");
			}
		}
	}

}

package SeleniumPackage;

import java.util.Scanner;

public class Factorial_1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Plz Enter Number :- ");
		long no = sc.nextInt();
		int factno = 1;
		
		for(int i=1;i<=no;i++)
		{
			factno = factno * i ;//
			//7*6*5*4*3*2*1
		}
		
	    System.out.println(factno);
	}

}

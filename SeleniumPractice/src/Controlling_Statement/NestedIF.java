package Controlling_Statement;

import java.util.Scanner;

public class NestedIF {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Lift Number Here > ");
		int liftNO = sc.nextInt();

		switch(liftNO)
		{
		case 1:
			System.out.println("Floor No 1 arrived.");
			break;
		case 2:
			System.out.println("Floor No 2 arrived.");
			break;
		case 3:
			System.out.println("Floor No 3 arrived.");
			break;
		case 4:
			System.out.println("Floor No 4 arrived.");
			break;
		case 5:
			System.out.println("Floor No 5 arrived.");
			break;
		default:
			System.out.println(liftNO +" is invalid lift number.\nplz enter lift number <=5");
			break;


		}


	}

}

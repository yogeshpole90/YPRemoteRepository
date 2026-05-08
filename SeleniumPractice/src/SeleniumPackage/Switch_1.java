package SeleniumPackage;

import java.util.Scanner;

public class Switch_1 {
	public static void main(String[] args) {

		int liftno=0;
		try {
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter Lift Number here:- ");
			liftno=sc.nextInt();

		}
		catch(Exception e)
		{
			System.out.println("Invalid Input. ");
		}
		switch(liftno)

		{
		case 1 :
			System.out.println("At Floor 1. ");
			break;
		case 2:
			System.out.println("At Floor 2. ");
			break;
		case 3:
			System.out.println("At Floor 3. ");
			break;
		default:
			System.out.println("No Floor Exists");

		}
	}

}

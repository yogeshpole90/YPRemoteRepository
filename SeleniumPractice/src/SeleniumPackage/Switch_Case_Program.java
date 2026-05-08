package SeleniumPackage;

public class Switch_Case_Program {

	public static void main(String[] args) {

		//range / more than 1 condition = unable to check.
		//if-else(check all condition) - slow / complex condition
		//only matching check. 
		//same as if-else
		int day =2;

		switch(day) // which day should match and checks code for that code.
		{
		case 1:
			System.out.println("Day 1 is Monday");
			break; //if break is not there,all executes from matching day.

		case 2:
			System.out.println("Day 2 is Tuesday");
			//break;

		case 3:
			System.out.println("Day 3 is Wednesday");
			//break;

		case 4:
			System.out.println("Day 4 is thursday");
			break;

		case 5:
			System.out.println("Day 5 is Friday");
			break;

		default :
			System.out.println(" Day  = '" + day + "' Not Found. Plz enter only Valid");
			break;
		}

	}

}

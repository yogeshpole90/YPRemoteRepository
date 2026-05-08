package Controlling_Statement;

public class switchLoop {
	public static void main(String[] args) {


		int weekNo = 40;

		switch (weekNo)
		{

		case 1:
			System.out.println("monday");
			break;
		case 2:
			System.out.println("tue");
			break;
		case 3:
			System.out.println("wed");
			break;
		case 4:
			System.out.println("thurs");
			break;
		case 5:
			System.out.println("fri");
			break;
		case 6:
			System.out.println("sat");
			break;
		case 7:
			System.out.println("sun");
			break;
		default:
			System.out.println(weekNo +" is invalid number.\nPlz enter no <= 7.");
			break;
		}

	}
}
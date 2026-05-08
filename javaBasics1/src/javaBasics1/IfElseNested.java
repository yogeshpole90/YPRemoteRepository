package javaBasics1;

public class IfElseNested {

	public static void main(String[] args) {

		
		int age =25;
		int weight =51;
		
		//outer if block
		if (age > 18)
		{
			//inner if block
			if(weight > 50)
			{
				System.out.println("Eligible for blood donation");
			}
			else
			{
				System.out.println("Not Eligible");
			}
		}
		else
		{
			System.out.println("Age must be greater than 18");
		}
	}

}

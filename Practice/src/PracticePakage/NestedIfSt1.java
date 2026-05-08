package PracticePakage;

public class NestedIfSt1 {

	public static void main(String[] args) {

		int a =100;
		int b=200;
		
		if (a==400)
		{
			if(b==200)
			{
				System.out.println("Both condition are True");
			}
			else
			{
				System.out.println("Inner condition is wrong");
			}
		}
		else
		{
			System.out.println("Outer condition are Wrong");
		}
	}

}

package Controlling_Statement;

public class MultipleStatement {
	public static void main(String[] args) {

		if(1==1)
		{
			if(false)
			{
		     	System.out.println("if block executed.1");
			}
			else {
				System.out.println("else 1");
			}
		}

		else
		{
			System.out.println("else block executed.2");
		}

		if(true)
		{
			System.out.println("if block 2");
		}
		if(true)
		{
			System.out.println("if 3");
		}
	}

}

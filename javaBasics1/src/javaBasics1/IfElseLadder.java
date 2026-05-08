package javaBasics1;

public class IfElseLadder {

	public static void main(String[] args) {

		int q=400;
		int w=300;
		
		//only one condition is exceuted which is true
		//if 2 condition is true,first one is executed
		if(q < w)
		{
			System.out.println("Q is less than w");
		}
		else if(q == w)
		{
			System.out.println("q Equal to w");
		}
		else if (q > w)
		{
			System.out.println("Q is greater than w");
		}
		else if (w < q)
		{
			System.out.println("w is less than q");
		}
		else
		{
			System.out.println("Else stam executed");
		}
	}

}

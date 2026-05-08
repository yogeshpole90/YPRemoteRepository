package javaBasics1;

public class RelationalOperator {

	public static void main(String[] args) {

		
		int a=300;
		int b=300;
		
		if(a !=b)
		{
			System.out.println("a is not equal to b");
		}
		else
		{
			System.out.println("a equals to b ");
		}
		
		String p ="YOGESH";//upper
		String q="yogesh";//lower
		
		
		//1st way use for number comparison and case sensitive
		//not appropriate to use for String comparison
		if (p == q)
		{
			System.out.println("PQ are equal for 1st way");
		}
		else
		{
			System.out.println("PQ are Not equal for 1st way");
		}
		
		//2nd way is also case sensitive
		if (p.equals(q))
		{
			System.out.println("PQ are equal for 2nd way");
		}
		else
		{
			System.out.println("PQ are not equal for 2nd way");
		}
		
		//3rd way is appropriate
		//not case sensitive
		if (p.equalsIgnoreCase(q))

		{
			System.out.println("PQ are equal for 3rd case");
		}
		else 
		{
			System.out.println("PQ are not Equal for 3rd Case");
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}

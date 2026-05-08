package seleniumClasses;

public class Exception_hand {
	public static void main(String[] args) {

		String ref[] = new String [4];
		ref[0] = "anshu";
		ref[1] = "supesh";
		ref[2] = "yogesh";
		System.out.println("array values of index 3 is :- "+ref[3]);

		for(int i =0;i<ref.length;i++)
		{
			System.out.println("Array element of "+i+ " is " + ref[i]);

		}
		try 
		{
			System.out.println("Array value of index 4 is : " + ref[4]);
		}
		catch(Exception e)
		{
			System.out.println("Handled Exception is :- " + e);
		}
		finally
		{
			System.out.println("Handled Exception completelyy...");
		}
	}

}

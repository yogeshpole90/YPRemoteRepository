package javaBasics1;

public class EvenNO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count=0;

		for(int i=0;i<=100;i++)
		{
			if(i %2 == 0)
			{
				System.out.println(i+ " is Even No");
				count++;
			}
		
			
		}

		System.out.println("Total = " + count);
		

	}

}

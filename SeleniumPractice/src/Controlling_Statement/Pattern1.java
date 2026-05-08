package Controlling_Statement;

public class Pattern1 {
	public static void main(String[] args) {
		
		int line = 4;
		int star = 1;
		int space= 3;
		
		for(int i=1;i <= line;i++)
		{
			for(int j=1;j<=space;j++)
			{
				System.out.print(" ");
			}
			for(int j=1;j<=star;j++)
			{
				System.out.print("* ");
			}
			System.out.println();
			star++;
			space--;
		}
		
		int line1 = 3;
		int star1 = 3;
		int space1= 1;
		
		for(int i=1;i <= line1;i++)
		{
			for(int j=1;j<=space1;j++)
			{
				System.out.print(" ");
			}
			for(int j=1;j<=star1;j++)
			{
				System.out.print("* ");
			}
			System.out.println();
			star1--;
			space1++;
		}
		
		
		
	}

}

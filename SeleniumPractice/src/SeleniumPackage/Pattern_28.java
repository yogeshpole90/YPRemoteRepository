package SeleniumPackage;

public class Pattern_28 {
	
	public static void main(String[] args) {
		int line =6;
		int space=5;
		int star=1;
		for(int i=1;i<=line;i++)
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
		int line1 =5;
		int space1=1;
		int star1=5;
		for(int i=1;i<=line;i++)
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

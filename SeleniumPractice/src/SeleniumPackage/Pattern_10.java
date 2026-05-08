package SeleniumPackage;

public class Pattern_10 {
	public static void main(String[] args) {
		int star =4;
		int line =4;
		
		for(int i=1;i<=line;i++)
		{
			for(int j=1;j<=star;j++)
			{
				System.out.print("* ");
			}
			System.out.println();
			star--;
			
		}
		
	}

}

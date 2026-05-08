package SeleniumPackage;

public class Pattern_24 {

	public static void main(String[] args) {
		
		int line =6;
		int star=6;
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

package SeleniumPackage;

public class Pattern_11 {
	
	public static void main(String[] args) {
		
		int space=5;
		int star=1;
		int line=6;
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
	}

}

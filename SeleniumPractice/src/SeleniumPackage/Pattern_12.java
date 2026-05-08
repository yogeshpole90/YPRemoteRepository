package SeleniumPackage;

public class Pattern_12 {

	public static void main(String[] args) {
		
		int line =4;
		int space=3;
		int star = 1;
		for(int i=1;i<=line;i++) 
		{
			for(int j=1;j<=space;j++)
			{
				System.out.print(" ");
			}
			for(int j = 1;j<=2*star-1;j++)
			{
				System.out.print("* ");
			}
			System.out.println();
			space--;
			star++;
		}
		
	}
	
}

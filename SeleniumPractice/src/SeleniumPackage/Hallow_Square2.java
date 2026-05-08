package SeleniumPackage;

public class Hallow_Square2 {
	public static void main(String[] args) {
		int row=8;
		int col=8;
		
		for(int i=1;i<=row;i++)
		{
			for(int j=1;j<=col;j++)
			{
				//skip all condition if 1st one is true
				if(i==1 || i==row)
				{
					System.out.print("*");
				}
				else if(j==1 || j==col)
				{
					
					System.out.print("*");
				}
				else
				{
					System.out.print(" ");
				}
				
			}
			System.out.println();
		}
	}

}

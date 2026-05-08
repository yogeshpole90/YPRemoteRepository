package SeleniumPackage;

public class Pattern_45 {
	public static void main(String[] args) {
		int line =7;
		int star=1;
		int space=6;
		for(int i=1;i<=line;i++)
		{
			for(int k=1;k<=space;k++)
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



		int line1 =6;
		int star1=6;
		int space1=1;
		for(int i=1;i<=line1;i++)
		{
			for(int k=1;k<=space1;k++)
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

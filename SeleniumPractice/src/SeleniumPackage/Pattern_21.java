package SeleniumPackage;

public class Pattern_21 {

	public static void main(String[] args) {


		int line=8;
		int star=1;
		int space=7;

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
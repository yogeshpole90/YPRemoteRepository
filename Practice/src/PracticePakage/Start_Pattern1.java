package PracticePakage;

public class Start_Pattern1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int row=5;
		for(int i=0;i<=row;i++)//row
		{
			for(int j=1;j<=i;j++)//column
			{
				System.out.print("* ");

			}
			System.out.println();
		}


		for(int y=6;y<=0;y--)
		{
			for(int z=6;z<=y;z--)
			{
				System.out.print("*");
			}
			System.out.println();
		}

	}
}


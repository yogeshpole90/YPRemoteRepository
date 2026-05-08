package SeleniumPackage;

public class Pattern_27 {
	
	public static void main(String[] args) {
		int line =6;
		int star=1;
		int space=5;
		for(int i=1;i<=line;i++)
		{
			for(int j=1;j<=space;j++)
			{
				System.out.print(" ");
			}
			for(int j=1;j<=star;j++)
			{
				System.out.println("* ");
			}
		}
		
	}

}

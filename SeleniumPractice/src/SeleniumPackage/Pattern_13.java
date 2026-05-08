package SeleniumPackage;

public class Pattern_13 {
	public static void main(String[] args) {

		int line =5;
		int chr = 1;

		for(int i=1;i<=line;i++)
		{
			char ch='a';
			
			for(int j=1;j<=chr;j++)
			{
				System.out.print(ch);
				ch++;
			}
			System.out.println();
			
			chr++;
		}
	}

}

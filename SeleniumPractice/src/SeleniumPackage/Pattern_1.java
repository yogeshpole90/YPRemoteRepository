package SeleniumPackage;

public class Pattern_1 {
	
	/*
	 * Pattern : row/col 
	 * 1.Outer Loop (i) = rows = total lines 
	 * 2.Inner Loop (j) = columns = columns 
	 * 3.j<= : Only Change
	 */
	public static void main(String[] args) {
		//print square of 5 - *
		for(int i=1; i<=5;i++) //outer - line 5
		{
			for(int j=1 ; j<=i;j++) //inner
			{
				System.out.print("* ");
			}
			System.out.println();
		}
		
		
		
	}

}

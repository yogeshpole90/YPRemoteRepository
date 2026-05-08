package SeleniumPackage;

public class Pattern_2 {
	
	public static void main(String[] args) {
		
		//print 5 line - right angle
		
		for(int i =5;i>=0;i--) //condition : 4>=0
		{
			
			for(int j=1; j<=i;j++) //condition : 1 <= 5
			{
			
				System.out.print("* ");
			}
			
			System.out.println();//next line
		}
		
		
	}

}

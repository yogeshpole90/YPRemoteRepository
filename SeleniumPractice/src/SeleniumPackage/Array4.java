package SeleniumPackage;

public class Array4 {

	public static void main(String[] args) {

		//declare and init of multi dimen array
		int a[][] = 
			{
					{1,2,3,7},
					{4,5,6,0},
					{7,8,9,6}
			};

		//tot rows
		int row = a.length;
		System.out.println("tot rows :- "+ row);

		int col = a[0].length;
		System.out.println("tot col :- "+ col);
		
		//print all elements of multi dimen array
		for(int i=0;i<row;i++)
		{
			// Inner Loop
			for(int j = 0;j<col;j++)
			{
				System.out.print(a[i][j]+ " ");
				
				
			}
			System.out.println();//for next line
			
			
		}
		
		
		
		
		
		
		
		
		
		
		






	}

}

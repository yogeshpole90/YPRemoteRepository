package seleniumClasses;

public class array_2 {
	public static void main(String[] args) {

		Object a[][]= 
			{
					{"yogesh","supesh","anshu"},
					{"pole","thakre","deshmukh"},
					{"7th","8th","9th"},
			};
		System.out.println("tot rows:- "+ a.length);
		System.out.println("tot col :- "+ a[0].length);
		System.out.println("-----------------------------------");
		for(int i = 0;i<a.length;i++)
		{
			for(int j=0;j<a[0].length;j++)
			{
				System.out.print(a[i][j]+" ");
				
				
			}
			System.out.println();
		}


	}

}

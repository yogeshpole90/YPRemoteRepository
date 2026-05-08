package seleniumClasses;

public class Array_5 {

	public static void main(String[] args) {
		
		Object a[][]= { 
				{"yp1","st1","ad1"},
				{"yp2","st2","ad2"},
				{"yp3","st3","ad3"},
				};
		
		System.out.println("Total row :- " + a.length);
		System.out.println("Totak col :- " + a[0].length);
		System.out.println("--------------------------------");

		
		for(int i=0;i<a.length;i++)
		{
			for(int j=0;j<a[0].length;j++)
			{
				System.out.print(a[i][j]+ " ");
			}
			System.out.println();
		}

	}

}

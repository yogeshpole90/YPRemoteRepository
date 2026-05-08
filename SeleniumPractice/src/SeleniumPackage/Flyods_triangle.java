package SeleniumPackage;

public class Flyods_triangle {
	public static void main(String[] args) {
		int line=5;
		int start=1;
		int num=1;
		//line+start+num 
		for(int i=1;i<=line;i++)
		{
			for(int j=1;j<=num;j++)
			{
				
				System.out.print(start+" ");
				start++; //different

			}
			System.out.println();
			//start++; same
			num++;

			
		}
		
		
		
		
	}

}

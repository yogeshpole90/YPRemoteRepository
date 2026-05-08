package javaBasics1;

public class BreakSt3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//outer
		for(int i=1;i<3;i++)
		{
			//inner loop executes untill false
			for(int j=1;j<3;j++)
			{
			
				if(i==2 && j==2)
				{
					break;
				}
				System.out.println(i+" "+ j);
			}
			
		}

	}

}

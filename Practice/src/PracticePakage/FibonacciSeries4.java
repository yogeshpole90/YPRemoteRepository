package PracticePakage;

public class FibonacciSeries4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int p1=0,p2=1;
		
		for(int i=0;i<=20;i++)
		{
			int p3=p1+p2;
			System.out.print(p3+ " , ");
			p1=p2;
			p2=p3;
			
			
		}

	}

}

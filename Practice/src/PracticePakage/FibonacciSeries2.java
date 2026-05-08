package PracticePakage;

public class FibonacciSeries2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n1=0,n2=1,n3;
		
		for(int i=2;i<=50;i++)
		{
			n3=n1+n2;
			System.out.println(n3);
			n1=n2;
			n2=n3;
			
			
		}

	}

}

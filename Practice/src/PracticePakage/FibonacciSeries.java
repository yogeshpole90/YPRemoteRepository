package PracticePakage;

public class FibonacciSeries {
//0,1,1,2,3,5,8,13,21,32,53
	//sum of previous 2 digits .
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n1 = 0 , n2=1, count = 10;
		System.out.println(n1);
		System.out.println(n2);
		//0th position,1st
		for(int i = 2;i<=10;i++)
		{
			int n3=n1+n2;
			System.out.println(n3);//0,1,1
			//n1,n2,n3,n4,n5?
			n1=n2;
			n2=n3;
			
		}
		System.out.println("Ctrl is Out From Loop");
		
	}

}

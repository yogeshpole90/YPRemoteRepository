package PracticePakage;

public class Swapping2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a=10;
		int b=20;
		
		//sum > sum-2=2>sum-2=1
		
		System.out.println("Old Value of a is " +a);
		System.out.println("Old Value of b is " +b);
		
		System.out.println("==========================");

		a=a*b;
		b=a/b;
		a=a/b;
		

		System.out.println("New Value of a is " +a);
		System.out.println("New Value of b is " +b);
		


	}

}

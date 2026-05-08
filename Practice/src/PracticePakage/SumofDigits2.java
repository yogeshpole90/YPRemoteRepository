package PracticePakage;

public class SumofDigits2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int a =12345;
		int sum=0;
		while(a!=0)
		{
			int t = a%10;
			sum += t;
			a=a/10;
		}
		System.out.println("Total is " + sum);
		
		
	}

}

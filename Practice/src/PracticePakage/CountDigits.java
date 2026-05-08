package PracticePakage;

public class CountDigits {

	public static void main(String[] args) {


		int a=123456789;
		int count=0;
		
		while(a!=0)
		{
			a = a/10;//last digit remove
			count++;
		}
		System.out.println(count);
		
	}

}

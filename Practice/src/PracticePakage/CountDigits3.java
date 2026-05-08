package PracticePakage;

public class CountDigits3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long a= 12345434;
		int count=0;
		
		while(a!=0)
		{
			a=a/10;
			count ++;
		}
		System.out.println(count);
		
		
		

	}

}

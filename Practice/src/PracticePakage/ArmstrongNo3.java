package PracticePakage;

public class ArmstrongNo3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int a =153;
		int temp=a;
		int c=0;
		int rev=0;
		
		while(a!=0)
		{
			c=a%10;
			 rev += c*c*c;
			a=a/10;
			
		}
		if(temp == rev)
		{
			System.out.println(rev + " is Armstrong");
		}
		else
		{
			System.out.println("Not Armstong");
		}
		
		
		
		
	}

}

package PracticePakage;

public class ArmstrongNo4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//temp=cube
		int a =153;
		int temp=a;
		int sum=0;
		
		while(a!=0)
		{
			int c = a%10;
			sum += c*c*c;
			a=a/10;
			
		}
		if(temp == sum)
		{
			System.out.println(temp +" is a Armstrong No.");
		}
		else
		{
			System.out.println(temp + " is not a Armstrong No.");
		}
	

	}


}

package PracticePakage;

public class ArmstrongNo5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//no=+cube
		int a=153;
		int temp=a;
		int c;
		int sum=0;
		
		while(a!=0)
		{
			c=a%10;
			sum += c*c*c;
			
			a=a/10;
		}
		System.out.println("sum of Cube is "+sum);
		if(temp == sum)
		{
			System.out.println(sum + " is Armstrong");
		}

		else {
			System.out.println(sum + " is Not Armstrong");
		}
	}

}

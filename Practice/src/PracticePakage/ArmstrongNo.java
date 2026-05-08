package PracticePakage;

public class ArmstrongNo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int a =153; 
		int sum =0;
		int temp=a;
		while(a!=0)
			
		{
			int c = a%10;//last digit
			sum += c*c*c;//cube of last digit
			a=a/10;//remove last digit
					
		}
		if (temp == sum) //original= sum of Cube
		{
			System.out.println("Armstrong");
		}
		else
		{
			System.out.println("Not a Armstrong");
		}
	}

}

//sum of cubes of each digit = original 

//int s = temp



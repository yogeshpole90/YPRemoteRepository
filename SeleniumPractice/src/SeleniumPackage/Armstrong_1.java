package SeleniumPackage;

public class Armstrong_1 {
	
	public static void main(String[] args) {
		int orgno = 250;
		int temp = orgno;
		int sum=0;
		while(temp != 0)
		{
			int rem = temp%10;
			sum =sum + rem*rem*rem;
			temp= temp/10;
			
		}
		System.out.println(sum);
		
	}

}

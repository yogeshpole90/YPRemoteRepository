package SeleniumPackage;

public class Armstrong_Number {
	
	public static void main(String[] args) {
		
		int orgno = 153;
		int temp = orgno;
		int armno=0;
		int rem=0;
		while(temp != 0)
		{
			rem =temp%10; //153 = 3 - 
			armno = armno + rem* rem * rem;
			temp=temp/10;
			
		}
		
		if(armno==orgno)
		{
			System.out.println("'"+orgno +"'"+" Is a Armstrong No. ");
		}
		else
		{
		System.out.println("'"+orgno+"'"+" is Not a Armstrong No. ");	
		}
		
	}

}

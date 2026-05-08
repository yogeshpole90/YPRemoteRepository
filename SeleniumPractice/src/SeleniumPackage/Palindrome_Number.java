package SeleniumPackage;

public class Palindrome_Number {
	
	public static void main(String[] args) {
		
		//revNO = revNO*10 + temp%10;
		
		int orgNO = 1221;
		int temp = orgNO;
		int rev=0;
		while(temp!=0)
		{
			rev= rev*10 + temp%10;
			temp = temp/10;
		}
		
		if(orgNO==rev)
		{
			System.out.println("'"+ orgNO +"'"+" Is a Palindrome .");
		}
		else
		{
			System.out.println("'"+ orgNO +"'"+" Not a Palindrome Number.");
		}
	}

}

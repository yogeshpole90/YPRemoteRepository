package SeleniumPackage;

public class Prime_Number {
	
	public static void main(String[] args) {
		
		int no = 5;
		for (int i=2; i<no; i++)
		{
			if( no%i==0)
			{
				System.out.println(" Not . ");
				break;
			}
			else {
				System.out.println(" Is . ");
			}
		}
		
	}

}

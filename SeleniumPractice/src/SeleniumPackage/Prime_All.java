package SeleniumPackage;

public class Prime_All {

	public static void main(String[] args) {

		boolean isPrime = false;
		int n=2;
		for(int i= 2;i<=100;i++) //loop till 100
		{
			 isPrime = true;

			for(int j=2; j==n/2; i++)
			{
				if(i%j==0)
				{
					isPrime = false;
				}

			}


		}
		if(isPrime)
		{
			System.out.println("is Not Prime ");
		}


	}
	

}

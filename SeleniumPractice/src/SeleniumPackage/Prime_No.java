package SeleniumPackage;

public class Prime_No {
	public static void main(String[] args) {

		int no=2;

		for(int i=2;i<=no/2;i++){
			if(no%i==0)
			{
				System.out.println("Not");
			}
			else
			{
				System.out.println("Yes");
			}

		}


	}

}

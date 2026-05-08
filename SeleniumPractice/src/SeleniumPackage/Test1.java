package SeleniumPackage;

public class Test1 {

	public static void main(String[] args) {

		int [] a = {3,4,5,2,3,4,5,-1,8,99};

		int sml = a[0];
		for(int i=1;i<a.length;i++)
		{
			if(sml > a[i])
			{
				sml=a[i];
				
			}
		}

		System.out.println(sml);

	}

}

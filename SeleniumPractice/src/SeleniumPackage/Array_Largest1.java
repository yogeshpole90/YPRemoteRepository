package SeleniumPackage;

public class Array_Largest1 {
	public static void main(String[] args) {
		int a[] = {4,5,33,66,5};
		int largest = a[0];
		for(int i=1;i<a.length;i++)
		{
			if(largest <a[i])
			{
				largest=a[i];
			}

		}
		System.out.println("Largest number is:- "+largest);


	}

}

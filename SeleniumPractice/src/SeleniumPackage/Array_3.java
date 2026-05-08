package SeleniumPackage;

public class Array_3 {

	public static void main(String[] args) {
		int a[] = {22,2,44,5};//declare and initialize
		passingarr(a);

	}

	//create a mtd which receives an array as an argument/parameter
	public static void passingarr(int a[])
	{
		int b= a[0];
		for(int i=0;i<a.length;i++)
		{
			if(b >= a[i]) //22 >=22 
			{
				System.out.println(b);
			}
			else {
				System.out.println("abcd");
			}

		}//0<4/1<4/2<4/3<4
		//loop runs for 4 times , 3rd time if not run



	}

}

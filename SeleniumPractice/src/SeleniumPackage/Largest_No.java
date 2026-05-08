package SeleniumPackage;

public class Largest_No {

	public static void main(String[] args) {

		int a=10;
		int b=10;
		int c=10;

		String large = 
				(a>b && a>c) ? "a is largest": 
					(b>a && b>c)?"b is largest":
						(c>a && c>b)? "c is largest": "Values are Equal . "; 

		System.out.println(large);

	}

}

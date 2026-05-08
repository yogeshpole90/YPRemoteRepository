package SeleniumPackage;

public class Number_check {

	public static void main(String[] args) {

		int a= +0;

		String result = 
				(a<0)? " is Negative ":
			    (a>0)? " is Positive ":
				(a==0)?" is Zero ":     " Invalid ";

		System.out.println(result);


	}

}

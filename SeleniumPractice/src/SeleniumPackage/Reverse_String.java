package SeleniumPackage;

public class Reverse_String {

	public static void main(String[] args) {

		String str = "yogesh";
		String rev="";
		for(int i=str.length()-1; i>=0; i--)//6-1 > 0
		{
			rev = rev + str.charAt(i);
		}
		System.out.println(rev);



	}

}

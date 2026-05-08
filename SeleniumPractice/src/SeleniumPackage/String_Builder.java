package SeleniumPackage;

public class String_Builder {
	public static void main(String[] args) {
		
		String y = "YesNo";
		StringBuilder sb = new StringBuilder(y);
		String rev = sb.reverse().toString();
		
		System.out.println(rev);
		
	}

}

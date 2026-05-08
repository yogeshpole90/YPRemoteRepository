package SeleniumPackage;

public class Palindrome_String {
	
	public static void main(String[] args) {
		
		String str = "madam";
		String rev="";
		
		for(int i=str.length()-1;i>=0;i--)
		{
			rev=rev+ str.charAt(i); //error if rev not written
		}
		
		if(str.equalsIgnoreCase(rev))
		{
			System.out.println("'"+str+"'" + " IS a Palindrome .");
		}
		else
		{
			System.out.println("'"+str+"'" +" Is NOT a Palindrome.");
		}
	}

}

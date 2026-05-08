package PracticePakage;

public class String_2 {

	public static void main(String[] args) {
		
		String str = " Welcome to Pune ";
		
		//length
		System.out.println("Lenght: " + str.length());
		
		//upper
		System.out.println("Upper: " + str.toUpperCase());
		//lower
		System.out.println("Lower: "+ str.toLowerCase());
		//at 0
		System.out.println("At 0: "+str.charAt(2));
		//
		System.out.println("replace: "+str.replace(" ", ""));
		//
		System.out.println("Trim: "+"'"+str.trim()+"'");
		

	}

}

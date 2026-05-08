package PracticePakage;

public class String_1 {

	public static void main(String[] args) {
		String str= "Yogesh";
		
		//length
		System.out.println("Length :- " + str.length());
		
		//lower case
		System.out.println("Lower Case :- " +str.toLowerCase());
		
		//upper case
		System.out.println("Upper Case :- "+str.toUpperCase());
		
		//char at
		System.out.println("At 7 :- "+str.charAt(2));
		
		//contains t/f
		System.out.println("Contains o :- "+str.contains("o"));
		
		//t/f
		System.out.println("Ends With le :- "+str.endsWith("le"));
		
		//
		System.out.println("Index of l :- "+str.indexOf('l'));
	
		//
		System.out.println("Index of P :- "+str.indexOf("Pole"));
		
		//
		System.out.println("Starts with Y :- "+str.startsWith("Y"));
		
		//
		System.out.println("Before Trim :- " +"'"+str+"'" + "\n" + "Aft Trim :-"+"'"+str.trim()+"'");
		
		//
		System.out.println("Remove Space :- "+str.replace(" ", ""));
		
		//
		System.out.println("isEmpty :- "+str.isEmpty());
		
		//
		System.out.println("SubString from 8:- "+ str.substring(2));
		//
		System.out.println("SubString :- "+ str.substring(3, 5));//start to end-1 always..
		
		//
		
	}

}

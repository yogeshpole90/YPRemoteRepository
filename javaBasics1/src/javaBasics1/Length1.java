package javaBasics1;

public class Length1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//to calc string length
		String s= "Yogesh Pole is Learning Java";
		int Length= s.length(); //return Integer -10/30
		System.out.println(Length); //28 space also counts
		length();//call mtd name from main mtd
		
	}
	public static void length(){
		
		String zz="Yogesh";
		int yy=zz.length();//return int
		System.out.println(yy);
	}

}

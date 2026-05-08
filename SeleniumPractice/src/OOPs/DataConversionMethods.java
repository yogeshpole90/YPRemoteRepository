package OOPs;

public class DataConversionMethods {

	public static void main(String[] args) {
		
		//1. String  >> int
		//String s = "Yogesh"; //cannot convert
		String s = "123456";
		int a = Integer.parseInt(s);
		System.out.println(a);
		
		//2
		String s1 ="10";
		String s2 = "20";
		System.out.println(s1+s2);
		int b1 = Integer.parseInt(s1);
		int b2 = Integer.parseInt(s2);
		System.out.println(b1+b2);
		
		
		//3 int >> decimal
		String c1 = "10.2";
		String c2 ="20.2";
		System.out.println(Double.parseDouble(c1)+Double.parseDouble(c2));
		
		//4 String >> boolean
		String b3 = "Yogesh";//cannot convert into boolean
		String b4 = "true";
		boolean b5 = Boolean.parseBoolean(b3);
		System.out.println(b5);
		
		//5 int/float/boolean >>>>> String
		int b9 = 12;
		double b6 = 10.5;
		char  b7= 'c';
		boolean b8 = true;
		System.out.println(String.valueOf(b9));
		System.out.println(String.valueOf(b8));
		System.out.println(String.valueOf(b6));
		System.out.println(String.valueOf(b7));
		//int >>> string
		System.out.println(String.valueOf(b8));
		
		
	}

}

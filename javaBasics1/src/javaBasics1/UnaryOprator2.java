package javaBasics1;

public class UnaryOprator2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		int a=10;
		int b=10;
		System.out.println(a);//10
		System.out.println(a++ + ++a);//consider 1 st value of both expression
		//10,11,   12,12 - first values consider for addition
		//10  +    12=22  //expression values not a's values
		System.out.println(a);//12
		
		System.out.println("-------");
		System.out.println(b);//10
		System.out.println(b-- + --b);//10,9,8,8
		//10 + 8 =18
		System.out.println(b);//8
		
		
		
		
		
	}

}

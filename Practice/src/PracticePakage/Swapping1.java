package PracticePakage;

public class Swapping1 {

	public static void main(String[] args) {


		int a=50;
		int b=60;

		System.out.println("Old value of a is "+ a);
		System.out.println("Old value of b is "+ b);
		
		
		a=a+b;
	//	System.out.println("Totoal Value is " +a);
		System.out.println("====================");

		b=a-b;//110-60
		a=a-b;//110-50
		System.out.println("new value of a is "+ a);
		System.out.println("new value of b is "+ b);
		
		
	}

}

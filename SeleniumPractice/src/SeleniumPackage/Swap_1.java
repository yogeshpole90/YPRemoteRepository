package SeleniumPackage;

public class Swap_1 {
	public static void main(String[] args) {
		int a,b;
		a=40;
		b=50;
		System.out.println("Before:- "+"\n" + "a = "+ a + " , " +" b = "+b);

		a=a*b;
		b=a/b;
		a=a/b;
		
		System.out.println("After:- "+"\n" + "a = "+ a + " , " +" b = "+b);
		System.out.println("\u2714");
		
	}

}

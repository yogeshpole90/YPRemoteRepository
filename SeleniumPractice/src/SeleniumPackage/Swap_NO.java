package SeleniumPackage;

public class Swap_NO {
	public static void main(String[] args) {
		//a= total
		//total-a=b
		//total-b=a
		
		int a,b;
		a=100;
		b=200;
		System.out.println("before:- " + "\n"  +"a = " +a +","+"b = "+b);
		a= a+b;
		b=a-b;
		a=a-b;
		System.out.println("After:- " + "\n"  +"a = " +a +","+"b = "+b);
		
	
	}

}

package SeleniumPackage;

public class Swap_No2 {
	public static void main(String[] args) {
		//jodo = ghatao = ghatao
		int a,b;
		a=100;
		b=200;
		System.out.println("Before:- " + "\n"  +"a = " +a +","+"b = "+b);

		a=a*b;//a=total 20000 = 100*200
		b=a/b;//20000/200 = 100
		a=a/b;//20000/100= 200

		System.out.println("After:- " + "\n"  +"a = " +a +","+"b = "+b);
		//1*2=2
		//2=2/1
		//1=2/2

	}

}

package OOPs;

public class StaticMain {


	public static void main(String[] args) {

		Staticdemo.m1(); // gives error > unable to Recognize
		System.out.println(Staticdemo.a);
		Staticdemo st1 = new Staticdemo(); 
		st1.m2();
		System.out.println(st1.b);

		//to call non-static we also required object
		System.out.println("-----------");
		st1.m();


	}
}

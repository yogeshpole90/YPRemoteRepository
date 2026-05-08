package OOPs;

public class Staticdemo {
	
	static int a =10;//static var
	int b=20;//non-static var
	
	
	static void m1()//static method
	{
	System.out.println("m1 static method");	
		
	}
	void m2()//non-static method
	{
		
		System.out.println("m2 non-static method");
	}
	

	void m()//non-static - can access anything
	{
		System.out.println(a);
		System.out.println(b);
		m2();
		m1();
		
	}
	
	/*public static void main(String[] args) {
		
		m1();// direct access without obect creation.
		System.out.println(a);//direct access
		//m2();//cannot access directly
		//System.out.println(b);
		//non-static > different details for all objects
		Static st1 = new Static(); 
		
		st1.m2();
		System.out.println(st1.b);
		
		
	
		
		//to call non-static we also required object
		System.out.println("-----------");
		st1.m();
		
*/
		
		
		
	}
	



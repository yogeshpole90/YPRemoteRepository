package OOPs;

class abc{
	
	void m1(int a)
	{
		System.out.println(a);
	}
	
	void m2(int b)
	{
		System.out.println(b);
		
	}
}

class xyz extends abc
{
	@Override
	void m1(int a) //1 mtd
	{
		System.out.println("Changed logic: " + a*a);
	}
	//Overload
	void m2(int a,int b) //2nd mtd > diff from m2 at parent 
	{
		System.out.println(a + b);
	}
	void m2(int b)
	{
		System.out.println(b*b);
		
	}
}



public class OverloadingVsOverriding {

	public static void main(String[] args) {
		
		xyz xobj = new xyz();
		xobj.m1(10);
		xobj.m2(3);
		xobj.m2(4, 5);
		xobj.m2(5);
		
		
	}
	
}

package OOPs;


class Parent{
	String name="Yogesh";
	void m1()
	{
		System.out.println("m1 from parent");
	}



}
class Child extends Parent{

	int id=100;
	void m2()
	{
		System.out.println("m2 from child class");
	}



}


class TypeCastingConcept {


	public static void main(String[] args) {
		//Upcasting = automatic process
		int intvalue=100;
		long longvalue = intvalue;
		System.out.println(longvalue);

		float floatvalue=10.5f ;  //f - literals
		double doublevalue = floatvalue; 

		//downcast = manual process
		long longvalue1 = 200;
		int intvalue1 = (int)longvalue1;

		double doublevalue1 = 122.5;
		float floatvalue1 = (float) doublevalue1;

		//upcast
		int i =100;
		double d=i;
		System.out.println(d);

		double d1 = 100.5;
		int i1=(int) d1;
		System.out.println(i1);

		System.out.println("============");
		Child  c = new Child();
		c.m1();
		System.out.println(c.name);
		c.m2();
		System.out.println(c.id);
		
		//parent class variable can always hold child class object.
		//upcating
		Parent p = new Child();
		p.m1();
		System.out.println(p.name);
		//p.m2(); wrongg.
		
		
		//downcasting
		Parent p1 = new Parent(); // higher storing into lower
		Child c1 = (Child) p1;
		c1.m1();
		c1.m2();
		System.out.println(c1.name);
		System.out.println(c1.id);
		
		//
		Child c2= new Child();//no issue
		Parent p2 = new Parent();//no issue
		Child c3 = (Child) p1; // issue in this

		
		
		
		
		
	}
	

}
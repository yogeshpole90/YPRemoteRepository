package OOPs;

//single inheritance
class A{
	int a=100;
	void display()
	{
		System.out.println(a);
	}
	
}

class B extends A{
	
	int b=200;
	void show(){
		System.out.println(b);
		System.out.println(a);
		
	}
	
}

//one class should be public
public class Inheritancetypes {
	
	public static void main(String[] args) {
		
		B objb = new B();
		objb.display();
		System.out.println(objb.a);
		objb.show();
		System.out.println(objb.b);
	}

}

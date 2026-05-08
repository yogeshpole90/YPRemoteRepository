package OOPs;

class D{
	int a=100;
	void display()
	{
		System.out.println(a);
	}

}

class E extends D{

	int b=200;
	void show(){
		System.out.println(b);
		System.out.println(a);

	}

}

class F extends E {
	int c = 300;
	void cshow()
	{
		System.out.println(c);
	}


}



public class MultilevelInheritance {


	public static void main(String[] args) {
		F fobj = new F();
		System.out.println(fobj.a);
		fobj.a=300;
		System.out.println(fobj.a);
		fobj.display();
		fobj.show();
		fobj.cshow();



	}

}

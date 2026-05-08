package OOPs;

class parent
{
	void display(int a)
	{
		System.out.println(a);
	}

}

class child1 extends parent{
	
	void show(int b)
	{
		System.out.println(b);
	}


}
class child2 extends parent{

	void print(int c)
	{
		System.out.println(c);
	}

}
//common method = display


public class hierarchicalInheritance {
	public static void main(String[] args) {
		
		child1 c1 = new child1();
		c1.display(100);//parent
		c1.show(200);//child
		
		child2 c2 = new child2();
		c2.display(300);//parent
		c2.print(400);//child
		


	}

}

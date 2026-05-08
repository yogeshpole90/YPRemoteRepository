package OOPs;

 class Animal {
	String color ="white";
	void eater()
	{
		
		System.out.println("Eating. ");
	}


}

class Dog extends Animal{
	String color = "black";
	
	void displaycolor()
	{
		System.out.println(super.color);
		//super takes parent class value not implemented value
	}
	@Override
	void eater()
	{
		
		System.out.println("Eating Bread.");//print child logic
		super.eater();//print parent logic.
	}
}

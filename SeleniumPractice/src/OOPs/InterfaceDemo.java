package OOPs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

interface shape
{

	int length =20;//final /static by default
	//abs/def/public method can create
	int width =30;

	//for abstract method curly braces not allowed.
	default void square() 
	{
		System.out.println("This is a default Method. ");
	}
	void circle();
	//no implementation

	static void rectangle() 
	{
		//there is implementation
		System.out.println("This is a static method");
	}



}
public class InterfaceDemo implements shape {
	//3 methods
	@Override
	public void circle() {
		//public (intf) >> default (class)
		//cannot reduce the access/scope
		System.out.println("This is a circle Abstract method.");
		
	}
	public static void main(String[] args) {
		InterfaceDemo idobj= new InterfaceDemo();
		idobj.circle();//abstract
		idobj.square();//default
		//rectangle();//wrong as it belongs to interface
		shape.rectangle();//static
		idobj.triangle();
		
		//-------------------------------------------------------//
		//shape s= new shape();
		shape sh = new InterfaceDemo();
		sh.circle();//abstract
		sh.square();//default
		shape.rectangle();//static
		
		
	}

	void triangle()//not available in sh.triangle object.
	//as it belongs to class not interface.
	{
		System.out.println("Access triangle");
	}


}

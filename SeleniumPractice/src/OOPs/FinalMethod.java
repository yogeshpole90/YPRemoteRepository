package OOPs;

 class Test1{
	final void m()// cannot override if final specified
	{


	}
}
class Test2 extends Test1{
	@Override
	void m() //incorrect cuz m() is a final KW
	{
		
	}
	
}

public class FinalMethod {

	public static void main(String[] args) {


	}

}

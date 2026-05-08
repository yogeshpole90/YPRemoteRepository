package OOPs;

class Test{
	
	final int x=100;//“isse aage change allowed nahi”
}


public class FinalKeyword {
	
	public static void main(String[] args) {
		Test t= new Test();
		//t.x=200; //incorrect
		
		System.out.println(t.x);
		
		
	}

}

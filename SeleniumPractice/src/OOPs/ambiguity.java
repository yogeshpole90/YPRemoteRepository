package OOPs;

class X{

	//no method created
	//by default method inherited from OBJ class
}
class Y{
	//no method created
	//by default methods inherited from OBJ class

}
public class ambiguity {
	public static void main(String[] args) {
		X obj = new X();
		obj.notify();//notify() coming from object class.

		Y yobj = new Y();
		yobj.notify();//same method notify coming from object class.

		
		/*ambiguity problem - as jvm gets confused about which method to 
		call as duplicate method exists in multiple classes.
		default method exists which comes from object.
		*/
	}

}

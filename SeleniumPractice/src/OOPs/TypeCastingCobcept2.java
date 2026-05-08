package OOPs;


class Anima{}

class Dogg extends Anima{}

class Cat extends Anima{}


public class TypeCastingCobcept2 {
	public static void main(String[] args) {
		//1 >> there should be relationship
		//Anima an = new Dogg();
		//cat ct = (cat) an;
		//a  b     c    d
		
		//2 >> no relation
		//Dogg dg = new Dogg();
		//cat ct1 =  dg;
		
		//3 >> no relation
		//Anima an = new Dogg();
		//cat ct = (Dogg) an; // dog is going to store in cat
		
		//4 >>
		Anima an = new Dogg();
		Cat ct = (Cat) an;
		
		//
		
		
		
	}

}
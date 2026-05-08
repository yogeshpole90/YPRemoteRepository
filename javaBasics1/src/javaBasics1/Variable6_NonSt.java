package javaBasics1;

public class Variable6_NonSt {
	int b=10;//GV
	static int c=5;//SV

	public static void main(String[] args) {

		int a=6;//LV
		Variable6_NonSt v=new Variable6_NonSt();//obj ref
		
		System.out.println(c);
		System.out.println(v.b);//non statis var calling
		v.a();	//non static calling
		
	}
	
	public void a() {
		System.out.println("Yogesh Pole");
	}

}

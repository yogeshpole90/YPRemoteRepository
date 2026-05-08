package OOPs;
//I1,I2,C2 - parent
//C1 - Child
//multilevel Inheritance / / Hybrid inheritance
public class C1 extends C2 implements I1,I2{
	


	@Override
	public void m1() {

		System.out.println(x);

	}
	@Override
	public void m2() {
		System.out.println(y);

	}

	public static void main(String[] args) {
		C1 C1obj = new C1();
		C1obj.m1();
		C1obj.m2();
		C1obj.m3();


	}


}

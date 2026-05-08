package PracticePakage;

public class NoSpace2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String y = "AA BB";
		System.out.println("String with space " + y);
		int u=y.length();
		System.out.println("Length with Space "+ u);
		
		System.out.println("=======================");
		System.out.println(" Remove space and calculate");
		System.out.println("=======================");

		String z = y.replace(" ", "");
		System.out.println("String w/o Space " +z);
		
		int l = z.length();
		System.out.println("Length w/o space "+ l);
		
		
		
		
		
	}

}

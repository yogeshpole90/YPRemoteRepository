package PracticePakage;

public class SpaceRemove2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "AA BB CC DD";
		int l = s.length();
		System.out.println("Original Lenght " + l);
		
		String t = s.replace(" ", "");
		l=t.length();
		
		System.out.println("Length w/o Space " +l);
	}

}

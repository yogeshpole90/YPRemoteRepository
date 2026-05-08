package seleniumClasses;

public class Parsing_Exception_hand3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String x = "hello";
		try {
			int i = Integer.parseInt(x);
		}
		catch (Exception e) {
			System.out.println(e);
		}

		System.out.println("Handled");

	}

}

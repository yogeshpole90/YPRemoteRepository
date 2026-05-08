package PracticePakage;

public class ForEachLoop4 {

	//as array is object not primitive DT
	//cannot directly print its value
	//use foreach loop to print value
	
	public static void main(String[] args) {

		String s[]= {"ss","tt","uu","vv"};
		//System.out.println(s); print ref not actual value
		
		for(String t:s)
		{
			System.out.println(t);
		}
		System.out.println("Ctrl is Out of Loop");

	}

}

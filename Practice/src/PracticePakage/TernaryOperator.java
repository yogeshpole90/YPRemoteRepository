package PracticePakage;

public class TernaryOperator {
	public static void main(String[] args) {
		
		int a= 20,b=30,c=5;
		
		//take String as DT to print Largest Var name
		//Take below DT as Int to print Largest value
		String large = (a>b && a>c)? "a" : (b>c)? "b": "c";
		System.out.println(large);
	}

}

// ? = if true then print value next to it
// : = if false then check next condition

package javaBasics1;

public class LogicalOperator2 {

	public static void main(String[] args) {


		int a=10;
		int b=5;
		int c=20;
		
		//logical
		System.out.println(a<b && a++ < c);//1st false, 2nd is not executed
		System.out.println(a);//a's value remains same as 2nd cond. is not executed
		
		
		//bitwise
		System.out.println(a<b & a++ < c);//both executed,a's value increases
		System.out.println(a);//both executed,a's value increases
		
	}

}

package javaBasics1;

public class LogicalOperator1 {

	public static void main(String[] args) {

		int a=10;
		int b=5;
		int c=20;
		
		//logical opr
		//If one false ,not check 2nd 
		System.out.println(a<b && a<c);//false
		
		
		//bitwise opr
		//both condition checked
		System.out.println(a<b & a<c);//false,true=False
		//check both condition
		//true + true=True
		//F+F=F,,T+F=F,F+T=F
		
	
	}

}

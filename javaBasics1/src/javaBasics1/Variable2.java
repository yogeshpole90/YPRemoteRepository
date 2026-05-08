package javaBasics1;

public class Variable2 {
	int data = 50; //GV
	static int f =5; //SV -no warning as can used everywhere 

	public static void main(String[] args) {	
	
		int n=5;//LV-
		  //showing warning for LV ONLY as not used in mtd body-syso- as mtd body ends so system
		 //thinks it still not used as mtd body limit ended
		 
		System.out.println(n);
	}

	
	public void mtd() 
	{
		int d =10;//LV - inside mtd body
		
	}
	
	static int y=10;  //SV
	
	
}

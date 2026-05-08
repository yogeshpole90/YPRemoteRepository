package javaBasics1;

public class DownCasting {

	public static void main(String[] args) {
		long i=300700009;
		int  j=(int) i;//converting/reducing larger value and storing into smaller
		
		
		System.out.println(j);
		
		
		//2nd
		double d=1232343434.433434343;
		float f=(float) d;//converting double into float.
		System.out.println(f);//missed values
		
		//3rd decimal part terminated
		double dd=10.55;
		int ii=(int) dd;//convert double into interger and then storing
		System.out.println(ii);//.55 is loosed.

	}

}

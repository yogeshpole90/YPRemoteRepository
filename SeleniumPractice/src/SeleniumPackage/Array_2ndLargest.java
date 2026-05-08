package SeleniumPackage;

public class Array_2ndLargest {
	
	public static void main(String[] args) {
		int [] a = {3,55,33,99,104,999,444,888};
		//int [] b = {};
		int first=a[0];
		int second=0;
		for(int i=1;i<a.length;i++)
		{
			if(first< a[i])
			{
				second=first;
				first = a[i];	
			}
		}
		System.out.println("1st Largest : " + first);
		System.out.println("2nd Largest : "+ second);
	}

}

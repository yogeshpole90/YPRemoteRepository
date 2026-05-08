package Controlling_Statement;

public class LargestOfThree {
		public static void main(String[] args) {
			int a,b,c;
			a=30;
			b=20;
			c=20;
			
			if(a>b && a>c)
			{
				System.out.println(a+" is largest");
			}
			else if(b>a && b>c)
			{
				System.out.println(b+" is largest");
			}
			else if(c>a && c>b)
			{
				System.out.println(c+" is largest");
			}
			else
			{
				System.out.println("All is equal");
			}
			
			
		}

}

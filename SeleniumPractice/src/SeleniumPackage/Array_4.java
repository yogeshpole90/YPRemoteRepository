package SeleniumPackage;

public class Array_4 {
	public static void main(String[] args) {
		
		int [] a= {4,5,3,6,0,34,-1};
		int first=a[0];
		int second=0;
		
		for(int i=1; i < a.length; i++)
		{
			if(first >  a[i])
			{
				second = first;
				first=a[i];		
					
			}
		
		}
		System.out.println("1st Smallest : " + first);
		System.out.println("2nd Smallest : "+ second);
		
		
		
	}

}

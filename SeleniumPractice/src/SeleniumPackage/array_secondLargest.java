package SeleniumPackage;

public class array_secondLargest {
	
	public static void main(String[] args) {
		
		int [] arr = {1,4,3,8,3,9,12};
		int first = arr[0];
		int second = 0;
		
		for(int i=1;i < arr.length; i++)
		{
			if(arr[i] > first)
			{
				
				second=first;
				first = arr[i];
			}
		}
		System.out.println("Largest : " + first);
		System.out.println("2nd Largest :"+second);
	}

}

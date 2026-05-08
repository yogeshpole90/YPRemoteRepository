package SeleniumPackage;

public class Array_secondSmallest {
	
	public static void main(String[] args) {
		int [] arr = {3,5,2,1,8,99,104};
		
		int first = arr[0];
		int second=0;
		int third=0;
		for(int i=1; i<arr.length; i++)
		{
			if(arr[i] < first)
			{
			third=second;	
			second = first;
			first = arr[i];
			
			}
		}
		
		System.out.println("1st Smallest value : " + first);
		System.out.println("2nd Smallest value is : " + second);
		System.out.println("3rd Smallest value is : " + third);
	}

}

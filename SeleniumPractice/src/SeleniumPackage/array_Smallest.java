package SeleniumPackage;

public class array_Smallest {
	
	public static void main(String[] args) {
		int[] arr = {7,2,4,1,5,8};
		
		int min = arr[0];
		
		for(int i=1; i<arr.length; i++)
		{
			if(min>arr[i])
			{
				min=arr[i];
			}
		}
		
		System.out.println(" Min value in Array is : "+min);
		
		
	}

}

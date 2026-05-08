package SeleniumPackage;

public class Array_Largest {
	
	public static void main(String[] args) {
		int[] arr = {4,6,1,8,9,4,10,3};
		int max=arr[0];
		for(int i=1; i< arr.length; i++)
		{
			if(max < arr[i])
			{
				max=arr[i];
			}

		}
		System.out.println("Max value of Array is : "+ max);
		}
	
	

}

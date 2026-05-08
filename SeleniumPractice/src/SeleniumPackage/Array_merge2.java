package SeleniumPackage;

public class Array_merge2 {
	public static void main(String[] args) {


		int [] a = {4,5,6};
		int [] b = {12,23,45};
		int k=0;
		
		int[] merge = new int[a.length + b.length];
		for(int i =0; i<a.length ;i++) 
		{
			merge[k] = a[i];
			k++;
		}
		
		for(int i=0;i<b.length;i++)
		{
			merge[k] = b[i];
			k++;
			
		}
		//Print Array
		System.out.print("merge[i] : ");
		for(int i=0;i< merge.length;i++)
		{
			System.out.print(merge[i]+" , ");
		}

	}
}

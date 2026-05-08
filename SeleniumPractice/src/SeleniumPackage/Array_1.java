package SeleniumPackage;

public class Array_1 {

	public static void main(String[] args) {
		int [] a = {1,3,5,6};
		int [] b = {5,22,4,66};
		int k=0;
		int [] merge = new int[a.length+b.length];
		System.out.println();
		for(int i=0;i< a.length;i++)
		{
			merge[k] = a[i];
			k++;
		}
		for(int j=0;j<b.length;j++)
		{
			merge[k]= b[j] ;
			k++;

		}

		for(int i=0;i<merge.length;i++)
		{
			System.out.println(merge[i]);
		}
	}

}


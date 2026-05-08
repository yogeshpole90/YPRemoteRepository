package SeleniumPackage;

public class Array_merge {

	public static void main(String[] args) {

		int [] a = {1,2,3};
		int [] b = {4,5,6};
		int k=0;
		int [] merge = new int[a.length + b.length];

		for(int i=0;i<a.length;i++)
		{
			merge[k] = a[i];
			k++;
		}
		for(int i=0; i<b.length;i++)//from where to pickup value.
		{
			merge[k] = b[i];
			k++;//where to put
			//k tells where to put the values.
		}
		for(int j = 0;j<merge.length;j++)
		{
			System.out.println(merge[j]);}
	}

}

package seleniumClasses;

public class Single_arr2 {

	public static void main(String[] args) {

		String ref[] = new String[3];
		ref[0]= "ray0";
		ref[1]= "ray1";
		ref[2]="ray2";

		System.out.println("Leght is : - "+ ref.length);
		for(int i=0; i<ref.length;i++)
		{
			System.out.println("Array values of Index " + i + " is : " + ref[i]);
		}
		
	}

}

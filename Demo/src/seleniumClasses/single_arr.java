package seleniumClasses;

public class single_arr {

	public static void main(String[] args) {
		
		int ref[] = new int[5];

		ref[0]=5;
		ref[1]=10;
		ref[2]=15;
		ref[3]=20;
		ref[4]=25;
		System.out.println("Total Lenght of Array is :- " + ref.length );//total lenght
		System.out.println("Array vaue for Index 0 is : - "+ref[0]);//print single ele
		
		//print all elements of array
		for(int i=0;i<ref.length;i++)
		{
			
			System.out.println("Array value for "+ i +" is :- " + ref[i]);
		}
		
		
		
		
		
		
		
	}

}

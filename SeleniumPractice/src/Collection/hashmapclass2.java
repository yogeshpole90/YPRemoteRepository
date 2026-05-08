package Collection;

import java.util.HashMap;

public class hashmapclass2 {
	public static void main(String[] args) {


		HashMap<Integer, String> hm = new HashMap<Integer, String>();
		hm.put(1, "Yogesh");
		hm.put(2, "remi");
		hm.put(3, "anshuman");
		hm.put(4, "duplicate");
		hm.put(4, "supesh");//overrite
		hm.put(5, "thakre");
		//print all
		System.out.println(hm);

		//for-each
		System.out.println("=============");
		for(int k:hm.keySet())
		{
			System.out.print(k+" , ");
		}
		System.out.println();
		System.out.println("=============");
		for(String j : hm.values())
		{
			System.out.print(j+" , ");
		}
		System.out.println();
		
		//both will print
		System.out.println("=============");
		System.out.print("entrySet > "+hm.entrySet());
		
		//
		
		
		
	}


}

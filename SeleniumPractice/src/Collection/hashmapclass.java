package Collection;

import java.util.HashMap;
import java.util.Map;

public class hashmapclass {

	public static void main(String[] args) {


		//empid:data >> Key:value
		HashMap <Integer, String> hm = new HashMap<Integer,String>();
		hm.put(101, "Yogesh");
		hm.put(102, "Remi");
		hm.put(103, "jhon");//duplicate will skipped
		hm.put(103, "skot");
		hm.put(104, "skot");

		//print all
		System.out.println(hm);
		
		//remove by key
		hm.remove(104);
		System.out.println("after remove > "+hm);
		
		//access value by key
		System.out.println("at 101 > "+hm.get(101));
		
		//retrieve all keys
		System.out.println("all keys > "+hm.keySet());
		
		//retrieve all values
		System.out.println("all values > "+hm.values());
		
		//retrieve both
		System.out.println("both > "+hm.entrySet());
		
		//print keys
		for( int k : hm.keySet())
		{
			System.out.print(k+",");
			
		}
		System.out.println();
		//print values
		for(String j : hm.values())
		{
			System.out.print(j+",");
		}
		System.out.println();
		//using iterator >> complex
		//clear all
		hm.clear();
		System.out.println("after cleared > "+hm.entrySet());
		//
		
	}
}
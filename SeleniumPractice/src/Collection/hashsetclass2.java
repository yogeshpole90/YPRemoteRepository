package Collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class hashsetclass2 {
	
	public static void main(String[] args) {
		HashSet myset = new HashSet();
		myset.add("Yogesh");
		myset.add(10);
		myset.add('p');
		myset.add(10.5);
		myset.add(true);
		myset.add(true);
		myset.add("Yogesh");
		
		System.out.println("created list > "+myset);
		
		//size
		System.out.println("size > "+myset.size());
		
		//isEmpty
		System.out.println("isEmpty > "+myset.isEmpty());
		
		//add
		myset.add("Last0");
		myset.add("Last1");
		myset.add("Last2");
		System.out.println(myset);
		
		//convert Set >> List
		ArrayList al = new ArrayList(myset);
		System.out.println("at 3 > "+al.get(3));
		
		//at 4
		al.add(4, "Newat4");
		System.out.println(al);
		
		//iterator
		System.out.println("iterator > ");
		Iterator it = al.iterator();
		while(it.hasNext())
		{
			System.out.print(it.next()+" , ");
			
		}
		System.out.println();
		
		//for-each loop
		for(int i=0;i<al.size();i++)
		{
			System.out.print(al.get(i)+",");
			
		}
		
		
		
		
		
		
	}

}

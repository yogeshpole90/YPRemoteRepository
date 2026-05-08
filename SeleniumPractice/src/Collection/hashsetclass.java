package Collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class hashsetclass {
	public static void main(String[] args) {
		//all kinds of data
		HashSet myset = new HashSet();

		//Homogeneous data
		//HashSet<String> myset = new HashSet<String>();
		//hashset = hashing
		//Auto-remove duplicates
		myset.add(10.5);
		myset.add(false);
		myset.add(null);
		myset.add('a');
		myset.add("Yogesh");
		myset.add(null);
		myset.add(10.5);
		
		
		System.out.println(myset);
		
		//Remove specific element
		myset.remove(10.5);
		System.out.println("after object removed > "+myset);
		
		//insert
		myset.add("python");
		System.out.println("after object added > "+myset);
		
		//convert set >> array
		ArrayList al = new ArrayList(myset);
		System.out.println("converted List > "+al);
		
		System.out.println("at 2 > "+al.get(2));
		
		System.out.println("================");
		System.out.println("Enhanced > ");
		for(Object set :myset)
		{
			System.out.print(set +" , ");
		}
		System.out.println();
		System.out.println("===============");
		System.out.println("while > ");
		Iterator it = myset.iterator();
		while(it.hasNext())
		{
			System.out.print(it.next()+" , ");
			
		}
		System.out.println();
		System.out.println("===========");
		System.out.println("size > "+myset.size());
		
		
		
		
		
		
		
		
		
		
	}

}

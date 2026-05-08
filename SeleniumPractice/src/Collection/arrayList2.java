package Collection;

import java.util.ArrayList;
import java.util.Iterator;

public class arrayList2 {
	public static void main(String[] args) {
		
		ArrayList mylist = new ArrayList();
		mylist.add("Yogesh");
		mylist.add(10.6);
		mylist.add(22);
		mylist.add('Y');
		mylist.add(false);
		mylist.add(true);
		
		Iterator it = mylist.iterator();
		while(it.hasNext())
		{
			System.out.print(it.next()+" , ");
		}
		System.out.println();
		//============add
		mylist.add("Remi");
		mylist.add(3,"Yogesh");
		mylist.add(6, "Remi");
		System.out.println("after add > "+mylist);
		
		//=============remove
		mylist.remove(0);
		System.out.println("after remove > "+mylist);
		
		//=============clear specific elements
		ArrayList mylist2 = new ArrayList();
		mylist2.add("Yogesh");
		mylist2.add("Remi");
		mylist.removeAll(mylist2);
		System.out.println("after remove > "+mylist);
		
		//isEmpty
		System.out.println("is empty > "+mylist.isEmpty());
		
		//size
		System.out.println("size > "+mylist.size());
		
		//for-each
		System.out.println("Enhanced ========");
		for(Object list : mylist)
		{
			System.out.print(list+" , ");
			
		}
		System.out.println();
		
		
		
		
		
	}

}

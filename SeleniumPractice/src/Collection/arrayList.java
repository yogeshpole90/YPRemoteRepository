package Collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class arrayList {

	public static void main(String[] args) {

		//child obj = child ref - heterogeneous Object
		ArrayList mylist = new ArrayList();

		//child obj hold in parent ref
		//List mylist = new ArrayList();

		//store Homogeneous data then use -
		//ArrayList <Integer> mylist = new ArrayList<Integer>();

		//string data only
		//ArrayList<String > mylist =new ArrayList<String>();

		//Adding data into ArrayList
		mylist.add(1);
		mylist.add(10.5);
		mylist.add("Yogesh");
		mylist.add('a');
		mylist.add(true);
		mylist.add("Yogesh");
		mylist.add(null);

		System.out.println("size > "+mylist.size());

		//printing arraylist
		System.out.println("after created > "+mylist);

		//remove Elements 
		mylist.remove(5);
		System.out.println("after remove > "+mylist);

		//insert- anywhere vs added - at end
		mylist.add(5, "Remi");
		System.out.println("after inserted > "+mylist);

		// added - at end
		mylist.add(null);
		System.out.println("after added > "+mylist);

		//
		mylist.add(4, null);
		System.out.println("after insert > "+mylist);

		//modify / replace / change
		mylist.set(4, "python");
		System.out.println("after modify > "+mylist);

		//access specific object
		System.out.println("at 6 > "+mylist.get(6));
		System.out.println("at first > "+mylist.getFirst());
		System.out.println("last > "+mylist.getLast());

		//Reading all Elements > For Loop
		System.out.println("For Loop > ");
		for(int i=0;i<=mylist.size()-1;i++)
		{
			System.out.print(mylist.get(i)+" , ");
		}
		System.out.println();

		//Reading all Elements > enhanced
		System.out.println("Enhanced Loop > ");
		for(Object list : mylist)
		{
			System.out.print(list+" , ");
		}

		System.out.println();
		//for loop
		/* for(int j=0;j<=mylist.size()-1;j++)
		{
			System.out.println(mylist.get(j));

		}
		 */
		//iterator > all kind of collection
		System.out.println("iterator > ");

		Iterator it = mylist.iterator();
		System.out.println(it.next()); //1st element
		System.out.println(it.next()); //2nd element
		

		/*while(it.hasNext())
		{

			System.out.print(it.next()+" , ");

		}
		 */
		
		//Empty or Not
		System.out.println("isEmpty > "+mylist.isEmpty());
		
		//Remove Elements
		mylist.remove(5);
		System.out.println("after remove> "+mylist);
		
		//Remove All elements from one list.
		//mylist.removeAll(mylist);
		ArrayList mylist2 = new ArrayList();
		mylist2.add("Yogesh");
		mylist2.add(null);
		mylist2.add('x');
		mylist.removeAll(mylist2);
		System.out.println("after AllRemove> "+mylist);
		
		//remove multiple elements from mylist
		mylist.clear();
		System.out.println("after clear > "+mylist);

		
		

	}


}


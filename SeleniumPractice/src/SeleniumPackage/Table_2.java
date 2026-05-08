package SeleniumPackage;

import java.util.Scanner;

public class Table_2 {
	public static void main(String[] args) {
		 
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Number here :- ");
		int no = sc.nextInt();
		int start =1;
		
		System.out.println("find Table below :- ") ;
		for(int i =1;i<=10;i++)
		{
			int tab = no*start;
			System.out.println(tab);
			start++;
			
		}
		
		
		
		
		
		
		
	}

}

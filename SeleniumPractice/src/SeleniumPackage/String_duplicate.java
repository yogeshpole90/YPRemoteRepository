package SeleniumPackage;

import java.util.Scanner;

public class String_duplicate {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter String Here:- ");
		String name = sc.nextLine();
		int count=0;
		for(int i=0;i<name.length();i++)
		{
			for(int j=i+1;j<name.length();j++)
			{
				if(name.charAt(i) == name.charAt(j))
				{
					System.out.println(name.charAt(i));
					count++;
					break;
				}
				
				
				
			}
		}
		System.out.println(count);
		
		
	}

}

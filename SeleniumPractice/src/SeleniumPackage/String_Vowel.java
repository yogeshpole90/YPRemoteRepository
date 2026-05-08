package SeleniumPackage;

import java.util.Scanner;

public class String_Vowel {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter String Here:- ");
		String name = sc.nextLine();
		int count1=0;
		int count2=0;
		name.toLowerCase();
		for(int i=0;i< name.length();i++)
		{

			char ch = name.charAt(i);
			if(ch == 'a' ||ch ==  'e' ||ch == 'i' ||ch ==  'o' ||ch == 'u')
			{
				System.out.println("yes , V. ");
				count1++;
			}
			else if(ch>='a' && ch<='z')
			{
				System.out.println("No, c. ");
				count2++;
			}
		}
		System.out.println("Vowel is:- " +count1 +"\n" + "Conso is:- "+count2);


	}

}

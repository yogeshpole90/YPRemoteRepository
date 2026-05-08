package javaBasics1;

public class BreakSt1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i=1;
		
		while(i<=10)
		{
			if(i==5)//when i=5,breaks the loop
			{
				break;
			}
			System.out.println(i);
			i++;
		}

	}

}

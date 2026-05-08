package OOPs;

class Bank{//heirarchical
	double roi() //rate of int is different for every bank
	{
		return 0;
	}
	
}
class ICIC extends Bank{
	@Override
	double roi()
	{
		return 10.5;//Newly Implemented
		
	}
	
}

class SBI extends Bank{
	@Override
	double roi()
	{
		return 7.5;//Rewriting logic
		
	}
	
}



public class OverridingDemo {
	public static void main(String[] args) {
		//------------------------//
		Bank b = new Bank();
		double roibank = b.roi();
		System.out.println(roibank);
		
		//--------------------------//
		ICIC i = new ICIC();
		System.out.println(i.roi());
		
		//-------------------------//
		SBI sb = new SBI();
		double roisbi = sb.roi();
		System.out.println(roisbi);
		
		
	}

}

package OOPs;

public class ThisKeyword {
	int x,y;
	//class variable
	//class var and local variable are same
	/*
	ThisKeyword(int x, int y)
	{
		this.x=x;
		this.y=y;
	}
	 */
	//if class var and local var is not same.
	void setData(int a,int b)
	{
		x=a;
		y=b;
	}


	void display()
	{
		System.out.println(x);
		System.out.println(y);
	}
	public static void main(String[] args) {

		//ThisKeyword tk = new ThisKeyword(100,200);
		ThisKeyword tk = new ThisKeyword();
		tk.setData(100, 200);
		tk.display();


	}
}
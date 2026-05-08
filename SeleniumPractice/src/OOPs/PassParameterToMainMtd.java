package OOPs;

public class PassParameterToMainMtd {
	
	public static void main(String[] args) {
		
		System.out.println("total: "+args.length);
		for(String arg:args)
		{
		System.out.print(arg+" ");
		}
		
	}

}

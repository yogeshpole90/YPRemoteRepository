package LOS;

import org.testng.annotations.Test;

public class Call11 extends Setup {
	@Test
	public static void callingmtd() throws Exception {

		//Setup[
		Setup st = new Setup();
		st.Setuped();
		
		//logined
		Login lg = new Login(driver);
		lg.logined();
		
		
		
		
		
		
		

	}

}

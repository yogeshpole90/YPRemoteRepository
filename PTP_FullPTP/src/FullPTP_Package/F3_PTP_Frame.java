package FullPTP_Package;

/**
 * F3_PTP_Frame - Switch to PTP iframe
 */
public class F3_PTP_Frame extends A1_LoginSetup {

	public void switchToPTPFrame()
	{
		// Switch to PTP child frame
		driver.switchTo().frame("fetchPTPMstTabFrame");
		System.out.println("=================================================");
		System.out.println("Switched to PTP Frame: fetchPTPMstTabFrame");
	}

}

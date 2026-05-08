package Downpayment_Package;

/**
 * D3_DP_Frame - Switch to PTP iframe
 */
public class D3_DP_Frame extends D2_DP_Login {

	public void switchToPTPFrame()
	{
		driver.switchTo().frame("fetchPTPMstTabFrame");
		System.out.println("=================================================");
		System.out.println("Switched to PTP Frame: fetchPTPMstTabFrame");
	}

}

package FullPTP_Package;

public class F3_PTP_Frame extends F2_PTP_Login {

	public void switchToPTPFrame()
	{
		driver.switchTo().frame("fetchPTPMstTabFrame");
		logInfo("PTP Frame", "Switched to frame", "fetchPTPMstTabFrame");
	}
}

package Skeleton_pkg;

import org.testng.annotations.Test;

public class SuperTest extends Setup1 {

    @Test
    public void executeTest() throws Exception {

        // Login (driver pass karna zaroori hai)
        Login_EBID login = new Login_EBID(driver);
        login.login();

        // Screenshot (driver pass karna zaroori hai)
        ScreenshotUtil ss = new ScreenshotUtil(driver);
        ss.takeScreenshot();
        
        Law_Firm lf = new Law_Firm();   // object bana

        lf.bc();       // before class method call
        lf.taker2("", "", "", "", "", "", "", "", "", "", "", "", "");
        // ya
    }
}
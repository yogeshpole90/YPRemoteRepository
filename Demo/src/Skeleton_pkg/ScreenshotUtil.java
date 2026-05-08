package Skeleton_pkg;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    WebDriver driver;

    // ✅ Constructor MUST be like this
    public ScreenshotUtil(WebDriver driver) {
        this.driver = driver;
    }

    public void takeScreenshot() throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);

        File dest = new File("D:\\Screenshots\\"
                + "ss_" + System.currentTimeMillis() + ".png");

        FileUtils.copyFile(src, dest);

        System.out.println("Screenshot Saved");
    }
}
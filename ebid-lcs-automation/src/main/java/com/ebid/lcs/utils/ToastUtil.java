package com.ebid.lcs.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ToastUtil {

    public static String getErrorToast(WebDriver driver) {
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo({top:0,behavior:'smooth'})");
            Thread.sleep(1000);
            WebElement toast = driver.findElement(By.cssSelector("div.msg-toast.msg-error.msg-showing em"));
            return toast.getText();
        } catch (Exception e) { return ""; }
    }

    public static String getSuccessToast(WebDriver driver) {
        try {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo({top:0,behavior:'smooth'})");
            Thread.sleep(1000);
            WebElement toast = driver.findElement(By.cssSelector("div.msg-toast.msg-success.msg-showing em"));
            return toast.getText();
        } catch (Exception e) { return ""; }
    }
}

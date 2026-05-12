package com.ebid.lcs.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ToastUtil {

    private static final Logger logger = LogManager.getLogger(ToastUtil.class);
    private static final int TOAST_WAIT_SECONDS = 5;

    private static final By TOAST_CONTAINER = By.cssSelector(".toast-messages .msg-toast.msg-showing");
    private static final By TOAST_MESSAGE = By.tagName("em");
    private static final By TOAST_SUCCESS = By.cssSelector(".toast-messages .msg-toast.msg-success.msg-showing");
    private static final By TOAST_ERROR = By.cssSelector(".toast-messages .msg-toast.msg-error.msg-showing");

    public static String getSuccessToast(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TOAST_WAIT_SECONDS);
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(TOAST_SUCCESS));
            String msg = toast.findElement(TOAST_MESSAGE).getText().trim();
            logger.info("SUCCESS TOAST: " + msg);
            return msg;
        } catch (Exception e) {
            logger.debug("No success toast found within " + TOAST_WAIT_SECONDS + " seconds");
            return "";
        }
    }

    public static String getErrorToast(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TOAST_WAIT_SECONDS);
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(TOAST_ERROR));
            String msg = toast.findElement(TOAST_MESSAGE).getText().trim();
            logger.info("ERROR TOAST: " + msg);
            return msg;
        } catch (Exception e) {
            logger.debug("No error toast found within " + TOAST_WAIT_SECONDS + " seconds");
            return "";
        }
    }

    public static String getAnyToast(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TOAST_WAIT_SECONDS);
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(TOAST_CONTAINER));
            String msg = toast.findElement(TOAST_MESSAGE).getText().trim();
            String classAttr = toast.getAttribute("class");
            String type = classAttr.contains("success") ? "SUCCESS" : classAttr.contains("error") ? "ERROR" : "INFO";
            logger.info(type + " TOAST: " + msg);
            return msg;
        } catch (Exception e) {
            logger.debug("No toast found within " + TOAST_WAIT_SECONDS + " seconds");
            return "";
        }
    }

    public static boolean isSuccessToast(WebDriver driver) {
        return !getSuccessToast(driver).isEmpty();
    }

    public static boolean isErrorToast(WebDriver driver) {
        return !getErrorToast(driver).isEmpty();
    }
}

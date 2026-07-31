package com.agat.los.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ToastUtil {

    private static final Logger logger = LogManager.getLogger(ToastUtil.class);
    private static final int TOAST_WAIT_SECONDS = 10;
    private static final By TOAST_CONTAINER = By.cssSelector(".toast-messages .msg-toast.msg-showing");
    private static final By TOAST_MESSAGE = By.tagName("em");

    public static String getSuccessToast(WebDriver driver) {
        String msg = findToastMessage(driver);
        if (msg == null) {
            try {
                driver.switchTo().defaultContent();
                msg = findToastMessage(driver);
            } catch (Exception e) {}
        }
        return msg != null ? msg : "";
    }

    public static String getErrorToast(WebDriver driver) {
        return getSuccessToast(driver);
    }

    private static String findToastMessage(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TOAST_WAIT_SECONDS));
            WebElement toast = wait.until(ExpectedConditions.presenceOfElementLocated(TOAST_CONTAINER));
            String message = "";
            try {
                message = toast.findElement(TOAST_MESSAGE).getText().trim();
            } catch (Exception e) {
                message = toast.getText().trim();
            }
            logger.info("Toast captured: {}", message);
            return message;
        } catch (Exception e) {
            return null;
        }
    }
}

package com.ebid.lcs.utils;

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
    private static final int TOAST_WAIT_SECONDS = 5;

    private static final By TOAST_CONTAINER = By.cssSelector(".toast-messages .msg-toast.msg-showing");
    private static final By TOAST_MESSAGE = By.tagName("em");

    public enum ToastType {
        SUCCESS, ERROR, WARNING, INFO, UNKNOWN
    }

    public static class ToastMessage {
        private final ToastType type;
        private final String message;

        public ToastMessage(ToastType type, String message) {
            this.type = type;
            this.message = message;
        }

        public ToastType getType() {
            return type;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return "[" + type + "] " + message;
        }
    }

    /**
     * Generic toast capture - checks in current context first, then defaultContent
     */
    public static ToastMessage getToastIfPresent(WebDriver driver) {
        // Try in current context first (frame)
        ToastMessage toast = findToast(driver);
        if (toast != null) return toast;

        // Try in defaultContent
        try {
            driver.switchTo().defaultContent();
            toast = findToast(driver);
            if (toast != null) return toast;
        } catch (Exception e) {}

        logger.debug("No toast found within {} seconds", TOAST_WAIT_SECONDS);
        return null;
    }

    private static ToastMessage findToast(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TOAST_WAIT_SECONDS));
            WebElement toast = wait.until(ExpectedConditions.presenceOfElementLocated(TOAST_CONTAINER));

            String classAttr = toast.getAttribute("class");
            String message = "";

            try {
                message = toast.findElement(TOAST_MESSAGE).getText().trim();
            } catch (Exception e) {
                try {
                    message = (String) ((org.openqa.selenium.JavascriptExecutor) driver)
                            .executeScript("return arguments[0].querySelector('em').textContent.trim();", toast);
                } catch (Exception e2) {
                    message = toast.getText().trim();
                }
            }

            ToastType type = ToastType.INFO;
            if (classAttr.contains("msg-error")) {
                type = ToastType.ERROR;
            } else if (classAttr.contains("msg-success")) {
                type = ToastType.SUCCESS;
            } else if (classAttr.contains("msg-warning")) {
                type = ToastType.WARNING;
            }

            logger.info("Toast captured: {} - {}", type, message);
            return new ToastMessage(type, message);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getSuccessToast(WebDriver driver) {
        ToastMessage toast = getToastIfPresent(driver);
        if (toast != null && (toast.getType() == ToastType.SUCCESS || !toast.getMessage().isEmpty())) {
            return toast.getMessage();
        }
        return "";
    }

    public static String getErrorToast(WebDriver driver) {
        ToastMessage toast = getToastIfPresent(driver);
        if (toast != null && toast.getType() == ToastType.ERROR) {
            return toast.getMessage();
        }
        return "";
    }

    public static String getAnyToast(WebDriver driver) {
        ToastMessage toast = getToastIfPresent(driver);
        if (toast != null) {
            return toast.getMessage();
        }
        return "";
    }

    public static boolean isSuccessToast(WebDriver driver) {
        return !getSuccessToast(driver).isEmpty();
    }

    public static boolean isErrorToast(WebDriver driver) {
        return !getErrorToast(driver).isEmpty();
    }
}

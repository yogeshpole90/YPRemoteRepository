package TopBank_Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for handling toast messages.
 * Captures success/error messages and extracts account numbers.
 */
public class ToastUtil {

    private static final Logger logger = LogManager.getLogger(ToastUtil.class);
    private static final int TOAST_WAIT_SECONDS = 3;

    // Common toast locators - adjust based on actual application
    private static final By TOAST_CONTAINER = By.cssSelector(".toast-messages .msg-toast.msg-showing");
    private static final By TOAST_MESSAGE = By.tagName("em");
    private static final By TOAST_SUCCESS = By.cssSelector(".toast-messages .msg-toast.msg-success");
    private static final By TOAST_ERROR = By.cssSelector(".toast-messages .msg-toast.msg-error");
    private static final By TOAST_WARNING = By.cssSelector(".toast-messages .msg-toast.msg-warning");

    public enum ToastType {
        SUCCESS, ERROR, WARNING, INFO, UNKNOWN,
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

    private ToastUtil() {
        // Utility class
    }

    /**
     * Wait for and capture toast message
     */
    public static ToastMessage getToastIfPresent(WebDriver driver) {
        try {
            WebDriverWait toastWait = new WebDriverWait(driver, TOAST_WAIT_SECONDS);
            WebElement toast = toastWait.until(ExpectedConditions.visibilityOfElementLocated(TOAST_CONTAINER));

            String classAttr = toast.getAttribute("class");
            String message = "";

            try {
                message = toast.findElement(TOAST_MESSAGE).getText().trim();
            } catch (Exception e) {
                message = toast.getText().trim();
            }

            ToastType type = ToastType.INFO;
            if (classAttr.contains("msg-error") || classAttr.contains("error")) {
                type = ToastType.ERROR;
            } else if (classAttr.contains("msg-success") || classAttr.contains("success")) {
                type = ToastType.SUCCESS;
            } else if (classAttr.contains("msg-warning") || classAttr.contains("warning")) {
                type = ToastType.WARNING;
            }

            logger.info("Toast captured: {} - {}", type, message);
            return new ToastMessage(type, message);

        } catch (Exception e) {
            logger.debug("No toast message found within {} seconds", TOAST_WAIT_SECONDS);
            return null;
        }
    }

    /**
     * Enforce toast rules - fail on error toast
     */
    public static void enforceToastRules(WebDriver driver) {
        ToastMessage toast = getToastIfPresent(driver);
        if (toast == null) {
            return;
        }

        switch (toast.getType()) {
            case ERROR:
                throw new RuntimeException("Application Error: " + toast.getMessage());
            case WARNING:
                logger.warn("Warning Toast: {}", toast.getMessage());
                break;
            case SUCCESS:
                logger.info("Success Toast: {}", toast.getMessage());
                break;
            default:
                logger.info("Toast: {}", toast.getMessage());
        }
    }

    /**
     * Capture account number from success toast
     * Pattern: "Record Saved Successfully - Account No: XXXXX" or similar
     */
    public static String captureAccountNumber(WebDriver driver) {
        ToastMessage toast = getToastIfPresent(driver);
        if (toast == null) {
            logger.error("No toast message to capture account from");
            return null;
        }

        String message = toast.getMessage();
        logger.info("Attempting to extract account from: {}", message);

        // Try different patterns
        String[] patterns = {
                "Account\\s*(?:No|Number)?\\s*[:\\-]?\\s*([A-Za-z0-9]+)",
                "A/c\\s*(?:No)?\\s*[:\\-]?\\s*([A-Za-z0-9]+)",
                "([A-Z]{2,}\\d{10,})",  // Pattern like ACC1234567890
                "(\\d{10,})"            // Just numbers 10+ digits
        };

        for (String pattern : patterns) {
            Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(message);
            if (m.find()) {
                String accountNo = m.group(1).trim();
                logger.info("Captured Account Number: {}", accountNo);
                return accountNo;
            }
        }

        // Fallback: try to extract last word/number sequence
        String[] words = message.split("\\s+");
        for (int i = words.length - 1; i >= 0; i--) {
            String word = words[i].replaceAll("[^A-Za-z0-9]", "");
            if (word.length() >= 8) {
                logger.info("Captured Account Number (fallback): {}", word);
                return word;
            }
        }

        logger.warn("Could not extract account number from toast: {}", message);
        return null;
    }

    /**
     * Wait for success toast and validate
     */
    public static boolean waitForSuccessToast(WebDriver driver) {
        ToastMessage toast = getToastIfPresent(driver);
        if (toast == null) {
            return false;
        }
        return toast.getType() == ToastType.SUCCESS;
    }

    /**
     * Capture error message from toast (for negative testing)
     */
    public static String captureErrorMessage(WebDriver driver) {
        ToastMessage toast = getToastIfPresent(driver);
        if (toast != null && toast.getType() == ToastType.ERROR) {
            return toast.getMessage();
        }
        return null;
    }
}

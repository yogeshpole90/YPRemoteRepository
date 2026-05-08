package TopBank_Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Utility class for common wait operations.
 */
public class WaitUtil {

    private static final Logger logger = LogManager.getLogger(WaitUtil.class);

    private WaitUtil() {
        // Utility class
    }

    /**
     * Wait for page to fully load
     */
    public static void waitForPageLoad(WebDriver driver, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }

    /**
     * Wait for AJAX calls to complete
     */
    public static void waitForAjax(WebDriver driver, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        try {
            wait.until(webDriver -> {
                JavascriptExecutor js = (JavascriptExecutor) webDriver;
                return (Boolean) js.executeScript("return (typeof jQuery === 'undefined') || (jQuery.active === 0)");
            });
        } catch (Exception e) {
            logger.debug("jQuery not present or AJAX check failed");
        }
    }

    /**
     * Wait for element and scroll into view
     */
    public static WebElement waitAndScrollTo(WebDriver driver, By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});", element);
        sleep(300); // Small delay for smooth scroll
        return element;
    }

    /**
     * Wait for element to be stale (useful after page refresh)
     */
    public static void waitForStale(WebDriver driver, WebElement element, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
            wait.until(ExpectedConditions.stalenessOf(element));
        } catch (Exception e) {
            // Element might already be stale
        }
    }

    /**
     * Wait for element attribute to have value
     */
    public static boolean waitForAttributeValue(WebDriver driver, By locator,
                                                String attribute, String value, int timeoutSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
            return wait.until(ExpectedConditions.attributeContains(locator, attribute, value));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Wait for element to be enabled
     */
    public static WebElement waitForEnabled(WebDriver driver, By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        wait.until(d -> element.isEnabled());
        return element;
    }

    /**
     * Sleep for specified milliseconds
     */
    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Retry operation with wait
     */
    public static <T> T retryWithWait(int maxRetries, int waitBetweenMs, RetryOperation<T> operation) {
        Exception lastException = null;
        for (int i = 0; i < maxRetries; i++) {
            try {
                return operation.execute();
            } catch (Exception e) {
                lastException = e;
                logger.debug("Retry {}/{} failed: {}", i + 1, maxRetries, e.getMessage());
                sleep(waitBetweenMs);
            }
        }
        throw new RuntimeException("Operation failed after " + maxRetries + " retries", lastException);
    }

    @FunctionalInterface
    public interface RetryOperation<T> {
        T execute() throws Exception;
    }
}

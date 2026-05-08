package TopBank_Utils;

import org.apache.logging.log4j.ThreadContext;

/**
 * Utility to set test case ID in logging context for thread-aware logs.
 */
public class LogContext {

    private static final String TC_ID_KEY = "tcId";

    private LogContext() {
    }

    /**
     * Set the current test case ID for this thread's logs.
     */
    public static void setTestCase(String tcId) {
        if (tcId != null && !tcId.isEmpty()) {
            ThreadContext.put(TC_ID_KEY, tcId);
        }
    }

    /**
     * Clear the test case ID from this thread's context.
     */
    public static void clear() {
        ThreadContext.remove(TC_ID_KEY);
    }

    /**
     * Get current test case ID.
     */
    public static String getTestCase() {
        return ThreadContext.get(TC_ID_KEY);
    }
}

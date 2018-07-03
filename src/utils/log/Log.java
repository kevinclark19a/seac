package utils.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * <p>
 * Project-wide logging class. It is recommended to setThreshold(on) for
 * debugging and setThreshold(e) for production. setThreshold(i) and
 * setThreshold(off) are also viable options, though they may make maintenance
 * more difficult.
 * </p>
 * <p>
 * The send*() methods are also provided. calling sendOutputToConsole() may
 * speed up debugging, while calling sendOutputToFile(fileName) provides more
 * log permanence.
 * </p>
 *
 *
 * @author Kevin Clark
 */
public abstract class Log {

    /**
     * Default to a console logger.
     */
    private static Logger delegate = Logger.makeConsoleLogger();

    private static Level level = Level.on;

    /**
     * Logs the specified message as an <i>error</i> if and only if threshold is set
     * to <b>e</b> or <b>on</b>.
     *
     * @param errorMessage
     *            the message to be logged.
     */
    public static void e(final String errorMessage) {
        if ( !(Log.level.equals(Level.on) || Log.level.equals(Level.e))) return;

        final String className = Helpers.resolveOriginatingClassName(),
                methodName = Helpers.resolveOriginatingMethodName();

        Log.delegate.logError("@" + className + "." + methodName + "\t-\t" + errorMessage + "\n");
    }

    /**
     * Logs the exception's message as an <i>error</i> if and only if threshold is
     * set to <b>e</b> or <b>on</b>.
     *
     * @param e
     *            the error to be logged.
     */
    public static void ex(final Exception e) {
        if ( !(Log.level.equals(Level.on) || Log.level.equals(Level.e))) return;

        final String className = Helpers.resolveOriginatingClassName(),
                methodName = Helpers.resolveOriginatingMethodName();

        Log.delegate.logError("Exception logged @" + className + "." + methodName + "\t-\t" + e.getClass() + ":\n\t"
                + e.getMessage() + "\n");
    }

    /**
     * Logs the specified message and exception as an <i>error</i> if and only if
     * threshold is set to <b>e</b> or <b>on</b>.
     *
     * @param e
     *            the error to be logged.
     * @param errorMessage
     *            the message to be logged.
     */
    public static void ex(final Exception e, final String errorMessage) {
        if ( !(Log.level.equals(Level.on) || Log.level.equals(Level.e))) return;

        final String className = Helpers.resolveOriginatingClassName(),
                methodName = Helpers.resolveOriginatingMethodName();

        Log.delegate.logError("Exception logged @" + className + "." + methodName + "\t-\t" + e.getClass() + ":\n\t"
                + errorMessage + "\n");
    }

    /**
     * Logs the specified message as <i>information</i> if and only if threshold is
     * set to <b>i</b> or <b>on</b>.
     *
     * @param infoMessage
     *            the message to be logged.
     */
    public static void i(final String infoMessage) {
        if ( !(Log.level.equals(Level.on) || Log.level.equals(Level.i))) return;

        final String className = Helpers.resolveOriginatingClassName(),
                methodName = Helpers.resolveOriginatingMethodName();

        Log.delegate.logInfo("@" + className + "." + methodName + "\t-\t" + infoMessage + "\n");
    }

    /**
     * Sets logging to output to the console.
     */
    public static void sendOutputToConsole() {
        Log.delegate = Logger.makeConsoleLogger();
    }

    /**
     * Sets logging to output to the specified file location.
     *
     * @param fileName
     *            the local path of the file to be logged to. If the file does not
     *            exist, it will be created.
     */
    public static void sendOutputToFile(final String fileName) {
        Log.delegate = Logger.makeFileLogger(fileName);
    }

    /**
     * Sets the logging threshold.
     *
     * @param level
     *            the level to set the logging threshold to.
     */
    public static void setThreshold(final Level level) {
        Log.level = level;
    }

    /**
     * Enum for setting the logging thresholds. <b>i</b> and <b>e</b> turn on only
     * info and error logging respectively. <b>on</b> turns on all logging, while
     * <b>off</b> turns off all logging.
     */
    public enum Level {
        /**
         * Only error logs are output.
         */
        e,
        /**
         * Only info logs are output.
         */
        i,
        /**
         * No logs are output.
         */
        off,
        /**
         * All logs are output.
         */
        on
    }

    /**
     * Helper methods for the log package.
     */
    static final class Helpers {

        /**
         *
         * @param fileName
         * @return
         */
        static PrintStream resolveStreamFromFile(final String fileName) {
            PrintStream stream = null;

            try {
                stream = new PrintStream(new FileOutputStream(fileName));
            } catch (final FileNotFoundException e) {
                final File file = new File(fileName);
                try {
                    if (file.createNewFile()) {
                        stream = new PrintStream(new FileOutputStream(file));
                    }
                } catch (final IOException ignored) {}
            }

            if (stream != null) return stream;

            // Default to stdout. Don't fail silently!
            System.err.println("Error initializing file for logging. Defaulting to System.out.");
            return System.out;
        }

        private static String resolveOriginatingClassName() {
            String className = "";

            try {
                final StackTraceElement originatingStackTrace = new Throwable().getStackTrace()[3];

                className = originatingStackTrace.getClassName();
                className = className.substring(className.lastIndexOf(".") + 1, className.length());
            } catch (final NullPointerException e) {
                System.err.println("Log.i - Error reading stacktrace");
            }

            return className;
        }

        private static String resolveOriginatingMethodName() {
            String methodName = "";

            try {
                final StackTraceElement originatingStackTrace = new Throwable().getStackTrace()[3];

                methodName = originatingStackTrace.getMethodName();
            } catch (final NullPointerException e) {
                System.err.println("Log.i - Error reading stacktrace");
            }

            return methodName;
        }
    }
}
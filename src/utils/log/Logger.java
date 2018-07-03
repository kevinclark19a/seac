package utils.log;

import java.io.PrintStream;

/**
 * Interface for delegates for the static Log class.
 */
interface Logger {

    /**
     * @param message
     *            the error message to log.
     */
    void logError(String message);

    /**
     * @param message
     *            the info message to log.
     */
    void logInfo(String message);

    /**
     * Default factory method for the logger interface.
     *
     * @return a console logger.
     */
    static Logger makeConsoleLogger() {
        return new PrintStreamLogger(System.out, System.err);
    }

    static Logger makeFileLogger(final String fileName) {
        return new PrintStreamLogger(Log.Helpers.resolveStreamFromFile(fileName + ".info"),
                Log.Helpers.resolveStreamFromFile(fileName + ".error"));
    }

    /**
     * An implementation of the logger interface. Sends passed messages to a print
     * stream.
     */
    class PrintStreamLogger implements Logger {

        private final PrintStream infoStream, errorStream;

        /**
         * Creates a new PrintStreamLogger with the specified info and error streams.
         *
         * @param infoStream
         *            a <b>PrintStream</b> that info logs should be directed to.
         * @param errorStream
         *            a <b>PrintStream</b> that error and exception logs should be
         *            directed to.
         */
        private PrintStreamLogger(final PrintStream infoStream, final PrintStream errorStream) {
            this.infoStream = infoStream;
            this.errorStream = errorStream;
        }

        @Override
        public void logError(final String message) {
            this.printMessageToStream(this.errorStream, message);
        }

        @Override
        public void logInfo(final String message) {
            this.printMessageToStream(this.infoStream, message);
        }

        /**
         * Internal use (i.e. from within <b>PrintStreamLogger</b>) only.
         *
         * @param stream
         *            the stream the message should be directed at.
         * @param logMessage
         *            the message to log.
         */
        private void printMessageToStream(final PrintStream stream, final String logMessage) {
            stream.println(logMessage);
        }
    }
}
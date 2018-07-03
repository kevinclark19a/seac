package core.model.parsing.states.factories;

import java.util.LinkedList;
import java.util.Queue;

import utils.log.Log;
import core.model.parsing.states.InSearchCommandStatement;
import core.model.parsing.states.ParseState;

/**
 * @author Kevin Clark
 *
 */
public abstract class InSearchCommandStatementFactory implements ParseStateFactory {

    /**
     * Singleton use only.
     */
    private InSearchCommandStatementFactory() {
    }

    @Override
    public ParseState make(final Queue<Character> nextChars) {

        // This is a simpler case.
        if (nextChars.peek() == '!') {
            return InSearchCommandStatement.withFlag(InSearchCommandStatementFactory.parseFlag(nextChars));
        }

        final int size = ParseState.getNextTokenSize(nextChars, (string) -> string.endsWith("!")) - 1;

        if (size < 1) return ParseState.Error;

        final Queue<Character> argumentQueue = new LinkedList<>();

        while (nextChars.peek() != '!') {
            argumentQueue.add(nextChars.poll());
        }

        final ParseState state =
                InSearchCommandStatement.withFlag(InSearchCommandStatementFactory.parseFlag(nextChars));
        while ( !argumentQueue.isEmpty())
            nextChars.add(argumentQueue.poll());

        return state;
    }

    public static final InSearchCommandStatementFactory Singleton = new InSearchCommandStatementFactory() {};

    /**
     * @param nextChars
     * @return
     */
    private static String parseFlag(final Queue<Character> nextChars) {
        int size = ParseState.getNextTokenSize(nextChars, (string) -> {
            return string.startsWith("!") && (string.endsWith(" ") || string.length() == nextChars.size());
        }) - 1;

        // Remove whitespace
        while (nextChars.peek() == ' ')
            nextChars.poll();

        // Remove the flag symbol
        if (nextChars.peek() == '!') nextChars.poll();

        String rawFlag = "";
        while (size-- > 0) {
            rawFlag += nextChars.poll();
        }

        Log.i("Returning flag " + rawFlag + ", length=" + rawFlag.length());
        return rawFlag.trim();
    }
}
/**
 *
 */
package core.model.parsing.states;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Function;
import java.util.function.Supplier;

import core.model.command.CommandToken;
import core.model.parsing.states.factories.InSearchCommandStatementFactory;
import utils.log.Log;

/**
 * @author Kevin Clark
 *
 */
public interface ParseState extends Supplier<CommandToken> {

    /**
     * @return
     */
    default boolean isErrorState() {
        return false;
    }

    /**
     * @param nextChars
     * @return
     */
    ParseState update(Queue<Character> nextChars);

    /**
     *
     */
    ParseState Error = new ParseState() {

        @Override
        public CommandToken get() {
            Log.i("Get called on ParseState.Error.");
            return CommandToken.Null;
        }

        @Override
        public boolean isErrorState() {
            return true;
        }

        @Override
        public ParseState update(final Queue<Character> nextChars) {
            Log.e("Update called on ParseState.Error.");
            return this;
        }

    };

    /**
     *
     */
    ParseState Initial = new ParseState() {

        @Override
        public CommandToken get() {
            Log.e("Get called on ParseState.Initial.");
            return CommandToken.Null;
        }

        @Override
        public ParseState update(final Queue<Character> nextChars) {
            ParseState state;

            Log.i("Attempting to parse command as a local executable");
            // Attempt to parse local executable command
            state = InLocalExecutableCommandStatement.madeBy(nextChars);
            if ( !state.isErrorState()) return state;

            Log.i("Attempting to parse command as a search call");
            // Attempt to parse search command.
            state = InSearchCommandStatementFactory.Singleton.make(nextChars);
            if ( !state.isErrorState()) return state;

            Log.e("No valid parsing found.");
            return ParseState.Error;
        }

    };

    /**
     * @param nextChars
     * @return
     */
    public static int getNextTokenSize(final Queue<Character> nextChars, final Function<String, Boolean> isValid) {
        final Queue<Character> copy = new LinkedList<>(nextChars);

        int numElemsPopped = 0;

        String rawCommand = "";
        while (numElemsPopped++ < nextChars.size()) {
            rawCommand += copy.poll();

            if (isValid.apply(rawCommand)) return numElemsPopped;

        }

        return -1;
    }
}
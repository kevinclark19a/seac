/**
 *
 */
package core.model.parsing.states;

import java.util.Queue;

import core.model.command.ArgumentToken;
import core.model.command.CommandToken;
import core.model.command.factories.LocalExecutableTokenFactories;
import core.model.command.localExecutable.LocalExecutableToken;

/**
 * @author Kevin Clark
 *
 */
public class InLocalExecutableCommandStatement implements ParseState {

    private final LocalExecutableToken exec;

    private CommandToken lastestTokenParsed;

    /**
     * @param token
     */
    public InLocalExecutableCommandStatement(final LocalExecutableToken token) {
        this.exec = token;
        this.lastestTokenParsed = this.exec;
    }

    /*
     * (non-Javadoc)
     * @see java.util.function.Supplier#get()
     */
    @Override
    public CommandToken get() {
        return this.lastestTokenParsed;
    }

    /**
     * @param nextChars
     * @return
     */
    public ParseState parseArgumentToken(final Queue<Character> nextChars) {
        int size = ParseState.getNextTokenSize(nextChars, (string) -> {
            return this.exec.isValidArgument(ArgumentToken.of(string));
        });

        if (size < 1) return ParseState.Error;

        // Pop off the correct number of elements
        String rawCommand = "";
        while (size-- > 0) {
            rawCommand += nextChars.poll();
        }
        this.lastestTokenParsed = ArgumentToken.of(rawCommand);

        return this;
    }

    /*
     * (non-Javadoc)
     * @see util.parsing.ParseState#update(java.util.Queue)
     */
    @Override
    public ParseState update(final Queue<Character> nextChars) {
        ParseState state;

        // Attempt to parse an argument.
        state = this.parseArgumentToken(nextChars);
        if ( !state.isErrorState()) return state;

        // TODO: Add additional useage cases here.

        return ParseState.Error;
    }
    // TODO Kevin Clark: Implement class.

    /**
     * @param nextChars
     * @return
     */
    public static ParseState madeBy(final Queue<Character> nextChars) {

        int size = ParseState.getNextTokenSize(nextChars, (string) -> {
            try {
                LocalExecutableTokenFactories.valueOf(string);

                return true;
            } catch (final IllegalArgumentException e) {
                return false;
            }
        });

        if (size < 1) return ParseState.Error;

        // Pop off the correct number of elements
        String rawCommand = "";
        while (size-- > 0) {
            rawCommand += nextChars.poll();
        }

        return new InLocalExecutableCommandStatement(LocalExecutableTokenFactories.valueOf(rawCommand).make());
    }
}
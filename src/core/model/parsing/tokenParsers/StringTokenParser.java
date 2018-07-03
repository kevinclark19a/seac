/**
 *
 */
package core.model.parsing.tokenParsers;

import java.util.Queue;

import core.model.command.ArgumentToken;
import core.model.command.CommandToken;

/**
 * @author Kevin Clark
 *
 */
public abstract class StringTokenParser implements TokenParser {

    /**
     * Singleton use only.
     */
    private StringTokenParser() {
    }

    @Override
    public CommandToken parse(final Queue<Character> nextChars) {
        String rawCommand = "";
        while ( !nextChars.isEmpty() && nextChars.peek() != '!')
            rawCommand += nextChars.poll();

        return ArgumentToken.of(rawCommand.trim());
    }

    public static final StringTokenParser Singleton = new StringTokenParser() {};
}
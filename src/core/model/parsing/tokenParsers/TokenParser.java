/**
 *
 */
package core.model.parsing.tokenParsers;

import java.util.Queue;

import core.model.command.CommandToken;

/**
 * @author Kevin Clark
 *
 */
public interface TokenParser {
    // TODO Kevin Clark: Implement interface.

    /**
     * @param nextChars
     * @return
     */
    CommandToken parse(Queue<Character> nextChars);
}
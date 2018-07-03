/**
 *
 */
package core.model.parsing;

import java.util.LinkedList;
import java.util.Queue;

import core.model.command.CommandToken;
import core.model.parsing.states.ParseState;

/**
 * @author Kevin Clark
 *
 */
public class CommandLineParser {

    private final Queue<Character> characterQueue = new LinkedList<>();

    private ParseState state = ParseState.Initial;

    /**
     * @param text
     */
    public CommandLineParser(final String text) {
        text.chars().forEach(character -> this.characterQueue.add((char)character));
    }

    /**
     * @return
     */
    public CommandToken getNextCommand() {
        if ( !this.hasMore() || this.state.isErrorState()) return CommandToken.Null;

        this.state = this.state.update(this.characterQueue);

        return this.state.get();
    }

    /**
     * @return
     */
    public boolean hasMore() {
        return !this.characterQueue.isEmpty();
    }
}
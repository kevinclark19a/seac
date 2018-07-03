package core.model.parsing.states.factories;

import java.util.Queue;

import core.model.parsing.states.ParseState;

/**
 * @author Kevin Clark
 *
 */
public interface ParseStateFactory {

    /**
     * @param nextChars
     * @return
     */
    ParseState make(Queue<Character> nextChars);
    // TODO Kevin Clark: Implement interface.
}
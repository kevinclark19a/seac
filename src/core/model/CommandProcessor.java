/**
 *
 */
package core.model;

import core.model.command.CommandToken;
import core.model.states.ProcessorState;

/**
 * @author Kevin Clark
 *
 */
public class CommandProcessor {

    private ProcessorState state = ProcessorState.Initial;

    /**
     * @param token
     */
    public void addToken(final CommandToken token) {

        this.state = this.state.update(token);
    }

    /**
     *
     */
    public void execute() {

        this.state.execute();
        this.state = ProcessorState.Initial;
    }
}
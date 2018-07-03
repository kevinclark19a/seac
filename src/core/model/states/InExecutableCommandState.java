/**
 *
 */
package core.model.states;

import core.model.command.ArgumentToken;
import core.model.command.CommandToken;
import core.model.command.ExecutableCommandToken;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Clark
 *
 */
public class InExecutableCommandState implements ProcessorState {

    private final List<ArgumentToken> arguments = new ArrayList<>();
    private final ExecutableCommandToken exec;

    /**
     * @param token
     */
    public InExecutableCommandState(final ExecutableCommandToken token) {
        this.exec = token;
    }

    /*
     * (non-Javadoc)
     * @see model.states.ProcessorState#execute()
     */
    @Override
    public void execute() {
        final ArgumentToken[] tokens = new ArgumentToken[this.arguments.size()];
        this.exec.execute(this.arguments.toArray(tokens));
    }

    /*
     * (non-Javadoc)
     * @see model.states.ProcessorState#update(model.command.CommandToken)
     */
    @Override
    public ProcessorState update(final CommandToken token) {
        if (token instanceof ArgumentToken) {
            this.arguments.add((ArgumentToken)token);
            return this;
        }

        return Error;
    }
}
/**
 *
 */
package core.model.states;

import core.model.command.CommandToken;
import core.model.command.ExecutableCommandToken;
import utils.log.Log;

/**
 * @author Kevin Clark
 *
 */
public interface ProcessorState {

    /**
     *
     */
    void execute();

    /**
     * @param token
     * @return
     */
    ProcessorState update(CommandToken token);

    ProcessorState Error = new ProcessorState() {

        @Override
        public void execute() {
            // TODO Kevin Clark: implement method logic.
            Log.e("Method undefined.");

        }

        @Override
        public ProcessorState update(final CommandToken token) {
            return this;
        }

    };

    ProcessorState Initial = new ProcessorState() {

        @Override
        public void execute() {
            // TODO Kevin Clark: implement method logic.
            Log.e("Method undefined.");

        }

        @Override
        public ProcessorState update(final CommandToken token) {
            if (token instanceof ExecutableCommandToken) {
                return new InExecutableCommandState((ExecutableCommandToken)token);
            }

            return ProcessorState.Error;
        }

    };
}
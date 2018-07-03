/**
 *
 */
package core.model.command.localExecutable;

import core.model.command.ArgumentToken;

/**
 * @author Kevin Clark
 *
 */
public class ExitCommandToken extends LocalExecutableToken {

    /*
     * (non-Javadoc)
     * @see
     * model.command.ExecutableCommandToken#execute(model.command.ArgumentToken[])
     */
    @Override
    public void execute(final ArgumentToken... ignored) {
        System.exit(0);
    }
}
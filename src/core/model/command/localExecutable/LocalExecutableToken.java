/**
 *
 */
package core.model.command.localExecutable;

import java.util.Arrays;
import java.util.List;

import core.model.command.ArgumentToken;
import core.model.command.ExecutableCommandToken;

/**
 * @author Kevin Clark
 *
 */
public abstract class LocalExecutableToken implements ExecutableCommandToken {

    protected final List<String> acceptableArguments;

    /**
     * @param acceptableArguments
     */
    protected LocalExecutableToken(final String... acceptableArguments) {
        this.acceptableArguments = Arrays.asList(acceptableArguments);
    }

    /**
     * @param argument
     * @return
     */
    public boolean isValidArgument(final ArgumentToken argument) {
        return this.acceptableArguments.contains(argument.toString());
    }
}
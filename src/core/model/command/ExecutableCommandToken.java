/**
 *
 */
package core.model.command;

/**
 * @author Kevin Clark
 *
 */
public interface ExecutableCommandToken extends CommandToken {

    /**
     * @param argumentTokens
     *
     */
    void execute(ArgumentToken... argumentTokens);
}
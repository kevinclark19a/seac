/**
 *
 */
package core.model.command;

/**
 * @author Kevin Clark
 *
 */
public class ArgumentToken implements CommandToken {
    private String argument;

    private ArgumentToken(final String argument) {
        this.argument = argument;
    }

    /**
     * @param toReplace
     * @param replacementText
     * @return
     */
    public ArgumentToken replace(final String toReplace, final String replacementText) {
        this.argument = this.argument.replaceAll(toReplace, replacementText);
        return this;
    }

    @Override
    public String toString() {
        return this.argument;
    }

    /**
     * @param startAndFinish
     * @return
     */
    public ArgumentToken wrap(final String startAndFinish) {
        this.argument = startAndFinish + this.argument + startAndFinish;
        return null;
    }

    /**
     * @param string
     * @return
     */
    public static ArgumentToken of(final String string) {
        return new ArgumentToken(string);
    }
}
/**
 *
 */
package core.model.command.searchCommand;

import core.model.command.ArgumentToken;

/**
 * @author Kevin Clark
 *
 */
public class WikiSearchToken extends SearchCommandToken {

    /**
     *
     */
    public WikiSearchToken() {
        // TODO Kevin Clark: implement constructor logic.
        super("http://www.wikipedia.com/", "wiki/");
    }

    /**
     * @param prefix
     * @param searchPrompt
     */
    protected WikiSearchToken(final String prefix, final String searchPrompt) {
        super(prefix, searchPrompt);
    }

    /*
     * (non-Javadoc)
     * @see
     * model.command.searchCommand.SearchCommandToken#formatArgument(model.command.
     * ArgumentToken)
     */
    @Override
    protected ArgumentToken formatArgument(final ArgumentToken token) {
        return token.replace(" ", "_");
    }
}
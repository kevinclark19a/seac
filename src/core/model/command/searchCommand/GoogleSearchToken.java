/**
 *
 */
package core.model.command.searchCommand;

import core.model.command.ArgumentToken;

/**
 * @author Kevin Clark
 *
 */
public class GoogleSearchToken extends SearchCommandToken {

    /**
     *
     */
    public GoogleSearchToken() {
        super("https://www.google.com/", "search?q=");
    }

    /**
     * @param prefix
     * @param searchPrompt
     */
    protected GoogleSearchToken(final String prefix, final String searchPrompt) {
        super(prefix, searchPrompt);
    }

    /*
     * (non-Javadoc)
     * @see
     * model.command.SearchCommandToken#formatArgument(model.command.ArgumentToken)
     */
    @Override
    protected ArgumentToken formatArgument(final ArgumentToken token) {
        token.replace("%", "%25").replace("\\+", "%2B").replace(" ", "+").replace("@", "%40").replace("#", "%23")
                .replace("\\$", "%24").replace("\\^", "%5E").replace("&", "%26").replace("=", "%3D");

        return token;
    }
}
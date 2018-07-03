/**
 *
 */
package core.model.command.searchCommand;

import utils.log.Log;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import core.model.command.ArgumentToken;
import core.model.command.ExecutableCommandToken;

/**
 * @author Kevin Clark
 *
 */
public abstract class SearchCommandToken implements ExecutableCommandToken {

    protected final String prefix;
    protected final String searchPrompt;

    /**
     * @param prefix
     * @param searchPrompt
     */
    protected SearchCommandToken(final String prefix, final String searchPrompt) {
        this.prefix = prefix;
        this.searchPrompt = searchPrompt;

    }

    @Override
    public final void execute(final ArgumentToken... argumentTokens) {
        if (argumentTokens.length != 1) return;

        try {
            Desktop.getDesktop()
                    .browse(new URI(this.prefix + this.searchPrompt + this.formatArgument(argumentTokens[0])));
        } catch (IOException | URISyntaxException e) {
            Log.ex(e, "Something went wrong opening google.");
        }
    }

    /**
     * @param token
     * @return
     */
    protected abstract ArgumentToken formatArgument(ArgumentToken token);
}
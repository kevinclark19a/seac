/**
 *
 */
package core.model.command.factories;

import core.model.command.searchCommand.GoogleSearchToken;
import core.model.command.searchCommand.SearchCommandToken;
import core.model.command.searchCommand.WikiSearchToken;
import core.model.command.searchCommand.YoutubeSearchToken;

import java.util.function.Supplier;

/**
 * @author Kevin Clark
 *
 */
public enum SearchCommandTokenFactories {
    /**
     *
     */
    g(GoogleSearchToken::new),
    /**
     *
     */
    wiki(WikiSearchToken::new),
    /**
     *
     */
    yt(YoutubeSearchToken::new);

    private final Supplier<SearchCommandToken> delegate;

    SearchCommandTokenFactories(final Supplier<SearchCommandToken> delegate) {
        this.delegate = delegate;
    }

    /**
     * @return
     */
    public SearchCommandToken make() {
        return this.delegate.get();
    }
    // TODO Kevin Clark: Implement enum.
}
/**
 *
 */
package core.model.parsing.states;

import java.util.Queue;

import core.model.parsing.tokenParsers.StringTokenParser;
import utils.log.Log;
import core.model.command.CommandToken;
import core.model.command.factories.SearchCommandTokenFactories;

/**
 * @author Kevin Clark
 *
 */
public class InSearchCommandStatement implements ParseState {

    private CommandToken latestTokenParsed;

    private final CommandToken searchCommandToken;

    /**
     * @param searchCommandFactory
     */
    public InSearchCommandStatement(final SearchCommandTokenFactories searchCommandFactory) {
        this.searchCommandToken = searchCommandFactory.make();
        this.latestTokenParsed = this.searchCommandToken;
    }

    /*
     * (non-Javadoc)
     * @see java.util.function.Supplier#get()
     */
    @Override
    public CommandToken get() {
        return this.latestTokenParsed;
    }

    /*
     * (non-Javadoc)
     * @see util.parsing.ParseState#update(java.util.Queue)
     */
    @Override
    public ParseState update(final Queue<Character> nextChars) {

        this.latestTokenParsed = StringTokenParser.Singleton.parse(nextChars);
        Log.i("Parsed Token " + this.latestTokenParsed.toString());

        if (this.latestTokenParsed.isNull()) return Error;

        return this;
    }

    /**
     * @param flag
     * @return
     */
    public static ParseState withFlag(final String flag) {
        SearchCommandTokenFactories searchCommandFactory;

        try {
            searchCommandFactory = SearchCommandTokenFactories.valueOf(flag);
        } catch (final IllegalArgumentException e) {
            return Error;
        }

        return new InSearchCommandStatement(searchCommandFactory);
    }
}
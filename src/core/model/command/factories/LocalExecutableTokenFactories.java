/**
 *
 */
package core.model.command.factories;

import java.util.function.Supplier;

import core.model.command.localExecutable.ExitCommandToken;
import core.model.command.localExecutable.LocalExecutableToken;

/**
 * @author Kevin Clark
 *
 */
public enum LocalExecutableTokenFactories {
    exit(() -> new ExitCommandToken());

    private final Supplier<LocalExecutableToken> delegate;

    LocalExecutableTokenFactories(final Supplier<LocalExecutableToken> delegate) {
        this.delegate = delegate;
    }

    /**
     * @return
     */
    public LocalExecutableToken make() {
        return this.delegate.get();
    }
    // TODO Kevin Clark: Implement enum.
}
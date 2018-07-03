/**
 *
 */
package core.model.command;

/**
 * @author Kevin Clark
 *
 */
public interface CommandToken {

    /**
     * @return
     */
    default boolean isNull() {
        return false;
    }

    /**
     *
     */
    CommandToken Null = new CommandToken() {

        @Override
        public boolean isNull() {
            return true;
        }
    };
}
/**
 *
 */
package core.model.command.searchCommand;

/**
 * @author Kevin Clark
 *
 */
public class YoutubeSearchToken extends GoogleSearchToken {

    /**
     *
     */
    public YoutubeSearchToken() {
        super("https://www.youtube.com/", "search?q=");
    }
}
/**
 *
 */
package utils.completion.dictionary;

import utils.completion.SearchTree;
import utils.log.Log;

import java.io.InputStream;
import java.util.Scanner;

/**
 * @author Kevin Clark
 *
 */
public class DictionaryFactory {

    /**
     * @return a search tree filled with dictionary entries.
     */
    public static SearchTree make() {

        final SearchTree tree = new SearchTree();

        InputStream dictionaryStream;

        // First try to get the dictionary stored on disk, if there is one.
        try {
            dictionaryStream = DictionaryUtils.getLocalDictionary();
        } catch (final Exception e) {
            Log.ex(e, "Something went wrong reading in the dictionary Falling back to default.");

            // Fallback to packaged file
            dictionaryStream = DictionaryUtils.getPackagedDictionary();
        }

        try (var dictionary = new Scanner(dictionaryStream)) {

            while (dictionary.hasNext()) {
                final var entry = dictionary.next();
                final var priority = dictionary.nextLong();

                Log.i(entry + " | " + priority);

                tree.add(entry, priority);
            }

        }

        return tree;

    }
}
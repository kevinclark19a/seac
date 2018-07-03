/**
 *
 */
package core.view.adapters;

import utils.completion.SearchTree;

/**
 * @author Kevin Clark
 *
 */
public interface DictionaryAdapter {

    /**
     * @return
     */
    SearchTree getLookupTree();

    /**
     * @param lookup
     * @return
     */
    void saveDictionary(SearchTree lookup);

}
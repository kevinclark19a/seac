package utils.completion;

import utils.log.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @author Kevin Clark
 *
 */
public class SearchTree {

    /**
     *
     */
    protected final Map<Character, SearchTree> children;

    private long priority = 0L;

    /**
     *
     */
    private boolean terminate = false;

    /**
     *
     */
    public SearchTree() {

        this(new HashMap<>());
    }

    /**
     * @param children
     */
    protected SearchTree(final Map<Character, SearchTree> children) {

        this.children = children;
        this.terminate = false;
    }

    /**
     * @param s
     * @param l
     * @return
     */
    public SearchTree add(final String s, final long l) {

        // Base case.
        if (s.length() == 0) {
            this.terminate = true;
            this.priority = l;
            return this;
        }

        // Recursive case
        final char[] characters = s.toCharArray();
        final String rest = s.replaceFirst(characters[0] + "", "");

        /* The branch already exists, just travel down it. */
        if (this.children.containsKey(characters[0])) this.children.get(characters[0]).add(rest, l);
            /* Recursively create a new branch. */
        else this.children.put(characters[0], new SearchTree().add(rest, l));

        return this;
    }

    /**
     *
     */
    public void clear() {

        // It's as simple as removing all of the entries in the mapping.
        this.children.clear();
    }

    /**
     * @param s
     * @return
     */
    public boolean contains(final String s) {

        // We always contain the empty string.
        if (s.length() == 0) return true;

        // Recursive case
        final char[] characters = s.toCharArray();
        final String rest = s.replaceFirst(characters[0] + "", "");

        return this.children.containsKey(characters[0]) && this.children.get(characters[0]).contains(rest);
    }

    /**
     * @param s
     * @return
     */
    public List<String> get(final String s) {

        final List<PriorityAccumulator> toListOutput = this.getEntries();

        // Filter through our toList output...
        final Stream<PriorityAccumulator> filteredOutputStream = toListOutput.stream().filter(elem -> {
            final boolean isBiggerThan = elem.get().length() > s.length();
            final boolean startsWith = elem.get().startsWith(s);

            return isBiggerThan && startsWith;
        });

        // Sort our collection
        final List<PriorityAccumulator> sortedOutputList = Arrays
                .asList(filteredOutputStream.toArray(PriorityAccumulator[]::new));
        Collections.sort(sortedOutputList);


        sortedOutputList.forEach((entry) -> {
            Log.i(entry.get() + " " + entry.getPriority());
        });

        // Convert from List<PriorityAccumulator> to List<String>
        final Stream<String> stringOutputStream = sortedOutputList.stream().map(PriorityAccumulator::get);

        // Return our sorted string list.
        return Arrays.asList(stringOutputStream.toArray(String[]::new));
    }

    /**
     * @return
     */
    public Map<String, Long> getPriorityMap() {

        final Map<String, Long> priorityMapping = new HashMap<>();
        this.getEntries().stream().forEach(entry -> priorityMapping.put(entry.get(), entry.getPriority()));

        return priorityMapping;
    }

    /**
     *
     * @return
     */
    public boolean isEmpty() {

        return this.children.isEmpty();
    }

    /**
     * @param s
     */
    public void prioritize(final String s) {

        // That's me!
        if (s.length() == 0) {
            this.priority++ ;
            return;
        }


        // Recursive case
        final char[] characters = s.toCharArray();
        final String rest = s.replaceFirst(characters[0] + "", "");

        if (this.children.containsKey(characters[0])) this.children.get(characters[0]).prioritize(rest);

    }

    /**
     * @return Returns a list of strings corresponding to each of the entries in this tree.
     */
    private List<PriorityAccumulator> getEntries() {

        final List<PriorityAccumulator> list = new ArrayList<>();

        final PriorityAccumulator NULL = new PriorityAccumulator("", this.priority);

        // Base case -- if we're child-less, we want to kick up an empty string.
        if (this.isEmpty()) {
            list.add(NULL);
            return list;
        }

        this.children.forEach((keyChar, child) -> {

            /**
             * The append function that takes in a child list and returns a new list with this
             * character appended to it's front.
             */
            final Consumer<List<PriorityAccumulator>> appendChar = (entries) -> {

                for (final PriorityAccumulator entry : entries)
                    entry.accumulate(keyChar);
            };

            final List<PriorityAccumulator> childList = child.getEntries();
            appendChar.accept(childList);

            list.addAll(childList);
        });

        // if (this.terminate) list.add(NULL);

        return list;
    }
}
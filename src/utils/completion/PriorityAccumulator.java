/**
 *
 */
package utils.completion;

/**
 * @author Kevin Clark
 *
 */
public class PriorityAccumulator implements Comparable<PriorityAccumulator> {

    private Long priority = 0L;

    private String value = "";

    /**
     * @param value
     * @param priority
     */
    public PriorityAccumulator(final String value, final Long priority) {

        this.priority = priority;
        this.value = value;
    }


    /**
     * @param c
     */
    public void accumulate(final char c) {

        this.value = c + this.value;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(final PriorityAccumulator other) {

        if (other == null) return 1;

        return other.priority.compareTo(this.priority);
    }

    /**
     * @return
     */
    public String get() {

        return this.value;
    }

    /**
     * @return
     */
    public Long getPriority() {

        return this.priority;
    }

    /**
     *
     */
    public void incrementPriority() {

        this.priority++ ;
    }
}
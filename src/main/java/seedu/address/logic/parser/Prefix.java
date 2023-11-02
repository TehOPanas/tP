package seedu.address.logic.parser;

/**
 * A prefix that marks the beginning of an argument in an arguments string.
 * E.g. 't/' in 'add James t/ friend'.
 */
public class Prefix {
    private final String prefix;

    private final String longPrefix;

    private boolean isUsingLongPrefix = false;

    public Prefix(String prefix, String longPrefix) {
        this.prefix = prefix;
        this.longPrefix = longPrefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getLongPrefix() {
        return longPrefix;
    }

    /**
     * Returns the length of the prefix.
     */
    public int getPrefixLength() {
        return isUsingLongPrefix ? longPrefix.length() : prefix.length();
    }

    public void useShortPrefix() {
        isUsingLongPrefix = false;
    }
    public void useLongPrefix() {
        isUsingLongPrefix = true;
    }

    @Override
    public String toString() {
        return getPrefix();
    }

    @Override
    public int hashCode() {
        return prefix == null ? 0 : prefix.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Prefix)) {
            return false;
        }

        Prefix otherPrefix = (Prefix) other;
        return prefix.equals(otherPrefix.prefix) && longPrefix.equals(otherPrefix.longPrefix);
    }
}

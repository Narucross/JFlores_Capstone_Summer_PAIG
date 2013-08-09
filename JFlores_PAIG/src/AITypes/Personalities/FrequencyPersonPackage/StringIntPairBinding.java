package AITypes.Personalities.FrequencyPersonPackage;

/**
 * A regular databeam that is basically a c# couplet of: String,short
 * User: Jflores
 * Date: 8/7/13
 * Time: 3:56 PM
 */
public class StringIntPairBinding implements Comparable<StringIntPairBinding> {
    private String wordOf;
    private short occurrence;

    public StringIntPairBinding() {
        wordOf = "";
        occurrence = 1;
    }

    public StringIntPairBinding(String word) {
        setWordOf(word);
        setOccurrence((short) 0);
    }

    public StringIntPairBinding(String word, short value) {
        setWordOf(word);
        setOccurrence(value);
    }

    public void incrementValue() {
        this.occurrence++;
    }

    @Override
    public int compareTo(StringIntPairBinding o) {
        return wordOf.compareTo(o.wordOf);
    }

    public String getWordOf() {
        return wordOf;
    }

    public void setWordOf(String wordOf) {
        this.wordOf = wordOf;
    }

    public short getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(short occurrence) {
        this.occurrence = occurrence;
    }

    @Override
    public String toString() {
        return "StringIntPairBinding{" +
                "wordOf='" + wordOf + '\'' +
                ", occurrence=" + occurrence +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StringIntPairBinding that = (StringIntPairBinding) o;

        if (!wordOf.equalsIgnoreCase(that.wordOf)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = wordOf.hashCode();
        result = 31 * result + (int) occurrence;
        return result;
    }
}//end of class
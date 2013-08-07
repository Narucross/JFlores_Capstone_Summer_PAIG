package AITypes.Personalities.FrequencyPersonPackage;

/**
 * User: Jflores
 * Date: 8/7/13
 * Time: 3:56 PM
 */
public class StringIntPairBinding implements Comparable<StringIntPairBinding> {
    private String wordOf;
    private short occurance;

    public StringIntPairBinding() {
        wordOf = "";
        occurance = 0;
    }

    public StringIntPairBinding(String word) {
        setWordOf(word);
        setOccurance((short) 0);
    }

    public StringIntPairBinding(String word, short value) {
        setWordOf(word);
        setOccurance(value);
    }

    public void incrementValue() {
        occurance++;
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

    public short getOccurance() {
        return occurance;
    }

    public void setOccurance(short occurance) {
        this.occurance = occurance;
    }
}//end of class
package AITypes.Personalities.FrequencyPersonPackage;

import AITypes.WeightedInstrument;

import java.util.ArrayList;

/**
 * This weighted instrument tries to tick up the likability of a word or antonym
 * by the number of times it can find it.
 * User: Jflores
 * Date: 8/7/13
 * Time: 1:54 PM
 */
public class FrequencyPersonality extends WeightedInstrument {

    private ArrayList<ArrayList<StringIntPairBinding>> Buckets;

    public FrequencyPersonality() {
        Buckets = new ArrayList<>((122 - 97));
        // Goes through A-Z uppercase
        for (byte i = 97; i <= 122; i++) {
            Buckets.add(new ArrayList<StringIntPairBinding>());
        }
        Buckets.trimToSize();
    }

    @Override
    protected void InnerWeightFunction(String word) {
        addWord(word);
    }

    public void addWord(String word) {
        if (word.length() > 0) {
            char a = Character.toLowerCase(word.charAt(0));
            int index = getCharAsNum(a);
            if (index > -1 && index <= 25) {
                ArrayList<StringIntPairBinding> selectedBucket = Buckets.get(index);
                int indexOf = getIndexOf(word, index);
                if (indexOf == -1) {
                    selectedBucket.add(new StringIntPairBinding(word));
                } else {
                    StringIntPairBinding inForm = selectedBucket.get(indexOf);
                    inForm.incrementValue();
                }
            }
        }
    }

    /**
     * Joshua implemented: Gets the index in the current bucket of this personality
     * returns -1 if no result is found or index out of bounds
     */
    public int getIndexOf(String word, int currentBucket) {
        int value = -1;
        if (currentBucket >= 0 && currentBucket < Buckets.size()) {
            ArrayList<StringIntPairBinding> bucket = Buckets.get(currentBucket);
            for (int i = 0; i < bucket.size() && value == -1; i++) {
                String z = bucket.get(i).getWordOf();
                if (z.equalsIgnoreCase(word)) {
                    value = i;
                }
            }
        }
        return value;
    }


    private int getCharAsNum(char a) {
        char x = Character.toLowerCase(a);
        return x - 97;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Personality Frequency:[");
        byte i = 0;
        for (ArrayList<StringIntPairBinding> gum : Buckets) {
            char x = (char) ((97) + (i++));
            builder.append("\nBucket(").append(x).append(") :{ ");
            for (StringIntPairBinding currentBinding : gum) {
                String readability = String.format("\n\t%s\n", currentBinding.toString());
                builder.append(readability);
            }
            builder.append("}");
        }
        builder.append("\n]");
        return builder.toString();
    }
}//end of class
package AITypes.Personalities.FrequencyPersonPackage;

import AITypes.WeightedInstrument;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * This weighted instrument tries to tick up the likability of a word or antonym
 * by the number of times it can find it.
 * User: Jflores
 * Date: 8/7/13
 * Time: 1:54 PM
 */
public class FrequencyPersonality extends WeightedInstrument {

    private ArrayList<StringIntPairBinding>[] Buckets;
    private HashSet<String> stopWords;

    public FrequencyPersonality() {
        byte length = 122 - 97;
        Buckets = new ArrayList[length + 1];
        // Goes through A-Z uppercase
        for (byte i = 0; i <= length; i++) {
            Buckets[i] = (new ArrayList<>());
        }
        stopWords = FrequencyHelper.getStopWords();
    }

    @Override
    protected void InnerWeightFunction(String word) {
        addWord(word);
    }

    public void addWord(String word) {
        if (word.length() > 1 && !stopWords.contains(word.toLowerCase())) {
            char a = Character.toLowerCase(word.charAt(0));
            int index = getCharAsNum(a);
            if (index > -1 && index <= 25) {
                ArrayList<StringIntPairBinding> selectedBucket = Buckets[index];
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
        if (currentBucket >= 0 && currentBucket < Buckets.length) {
            ArrayList<StringIntPairBinding> bucket = Buckets[currentBucket];
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

    public static ComparisonObject compareFrequencies(FrequencyPersonality a1, FrequencyPersonality a2) {
        ComparisonObject object = new ComparisonObject();
        short size_a1 = 0;
        for (ArrayList<StringIntPairBinding> binding : a1.Buckets) {
            Collections.sort(binding);
            size_a1 += binding.size();
        }
        int size_a2 = 0;
        for (ArrayList<StringIntPairBinding> binding : a2.Buckets) {
            Collections.sort(binding);
            size_a2 += binding.size();
        }
        boolean maxOne = size_a1 <= size_a2;
        int differenceInSizes = Math.max(size_a1, size_a2) - Math.min(size_a1, size_a2);
        object.setSizeDifference(differenceInSizes);

        ArrayList<StringIntPairBinding>[] biggerArray;
        ArrayList<StringIntPairBinding>[] smallerArray;

        if (maxOne) {
            biggerArray = a1.Buckets;
            smallerArray = a2.Buckets;
        } else {
            biggerArray = a2.Buckets;
            smallerArray = a1.Buckets;
        }
        for (int alphabet = 0; alphabet < biggerArray.length; alphabet++) {
            for (StringIntPairBinding currentBinding : smallerArray[alphabet]) {
                int indexOf = biggerArray[alphabet].indexOf(currentBinding);
                if (indexOf != -1) {
                    int biggerOccurrence = biggerArray[alphabet].get(indexOf).getOccurrence();
                    int occurrenceDifference = Math.min(biggerOccurrence, currentBinding.getOccurrence());
                    StringIntPairBinding newBinding = new StringIntPairBinding(currentBinding.getWordOf(), occurrenceDifference);
                    object.addIntersection(newBinding);
                }
            }
        }
        return object;
    }


}//end of class
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

    public static void main(String[] args) {
        FrequencyPersonality personality = new FrequencyPersonality();
    }

    private ArrayList<ArrayList<StringIntPairBinding>> Buckets;

    public FrequencyPersonality() {
        Buckets = new ArrayList<>((122 - 97));
        // Goes through A-Z uppercase
        for (byte i = 97; i <= 122; i++) {
            Character c = (char) i;
            int reversed = c;
            Buckets.add(new ArrayList<StringIntPairBinding>());
            System.out.println(c);
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
            int index = a;
            ArrayList<StringIntPairBinding> selectedBucket = Buckets.get(index);
            int indexOf = selectedBucket.indexOf(word);
            if (indexOf == -1) {
                selectedBucket.add(new StringIntPairBinding(word));
            } else {
                StringIntPairBinding inForm = selectedBucket.get(indexOf);
                short previousValue = inForm.getOccurance();
                inForm.incrementValue();
            }
        }
    }

    @Override
    public String toString() {
        return "FrequencyPersonality{" +
                "Buckets=" + Buckets +
                '}';
    }
}//end of class
package AITypes;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Just a generic implementation or paradigm for a weighted instrument
 * User: Jflores
 * Date: 8/7/13
 * Time: 7:41 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class WeightedInstrument {


    /**
     * A frontal method that takes the String in lowercase
     * Then calls the implementation method implemented by all versions of this class
     */
    public void WeightFunction(String wordToWeight) {
        String toLowerCase = wordToWeight.toLowerCase();
        InnerWeightFunction(toLowerCase);
    }

    /**
     * Implemented
     */
    protected abstract void InnerWeightFunction(String word);

    ////////////////// ==================================================================================================== \\\\\\\\\\\\\\\\\\
    //======= Weighted-Instrument's Utilities =======\\
    ////////////////// ==================================================================================================== \\\\\\\\\\\\\\\\\\
    public void addWordsToWeightedInstrument(WeightedInstrument wInstrument, String sentence) {
        Scanner scan = new Scanner(sentence);
        while (scan.hasNext()) {
            String currentWord = scan.next();
            currentWord = currentWord.replaceAll("\\W", "");
            if (!("".equals(currentWord) || " ".equals(currentWord))) {
                wInstrument.WeightFunction(currentWord);
            }
        }
    }

    public void addWordsToWeightedInstrument(WeightedInstrument wInstrument, String[] sentences) {
        addWordsToWeightedInstrument(wInstrument, Arrays.asList(sentences));
    }

    public void addWordsToWeightedInstrument(WeightedInstrument wInstrument, Iterable<String> sentences) {
        for (String sent : sentences) {
            addWordsToWeightedInstrument(wInstrument, sent);
        }
    }
    ////////////////// ==================================================================================================== \\\\\\\\\\\\\\\\\\
    //======= End of Weighted-Instrument's Utilities =======\\
    ////////////////// ==================================================================================================== \\\\\\\\\\\\\\\\\\
}//end of class
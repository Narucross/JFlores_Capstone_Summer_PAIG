package AITypes;

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

}//end of class
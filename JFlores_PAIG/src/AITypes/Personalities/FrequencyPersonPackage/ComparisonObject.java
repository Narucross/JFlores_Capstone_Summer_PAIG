package AITypes.Personalities.FrequencyPersonPackage;

import java.util.ArrayList;

/**
 * Stores some information on the differences between two frequency personalities
 * this probably should be a factory pattern...
 * User: jflores
 * Date: 8/28/13
 * Time: 11:39 AM
 */
public class ComparisonObject {
    private int sizeDifference = 0;
    private ArrayList<StringIntPairBinding> intersections;
    private int numOfIntersection = 0;

    public ComparisonObject() {
        intersections = new ArrayList<>();
    }

    public boolean addIntersection(StringIntPairBinding binding) {
        incrementNumOfIntersections();
        return intersections.add(binding);
    }

    /**
     * Shows which words are found in both frequencies
     */
    public String showCollisions() {
        StringBuilder builder = new StringBuilder();
        builder.append("Collisions occurred { \n");
        short i = 0;
        for (StringIntPairBinding binding : intersections) {
            String readability = String.format("\n\t%s.) %s\n", i++, binding.toString());
            builder.append(readability);
        }
        builder.append("}");
        return builder.toString();
    }


    //region Getters and setters
    public int getSizeDifference() {
        return sizeDifference;
    }

    public void setSizeDifference(int sizeDifference) {
        this.sizeDifference = sizeDifference;
    }

    public ArrayList<StringIntPairBinding> getIntersections() {
        return intersections;
    }

    public void setIntersections(ArrayList<StringIntPairBinding> intersections) {
        this.intersections = intersections;
    }


    public int getNumOfIntersection() {
        return numOfIntersection;
    }

    public void incrementNumOfIntersections() {
        numOfIntersection++;
    }

    public void setNumOfIntersection(int numOfIntersection) {
        this.numOfIntersection = numOfIntersection;
    }
    //endregion


    @Override
    public String toString() {
        return String.format("Size Difference: %s\nnumberOfCollisions: %s\n%s", sizeDifference, numOfIntersection, showCollisions());
    }
}//end of class
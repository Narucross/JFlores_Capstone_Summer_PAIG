package WebCrawlingResources.BigThesaurusImplement;

/**
 * A bean that can consume the second resource on a
 * User: jflores
 * Date: 7/26/13
 * Time: 1:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class BigThesaurusJSonResult {
    /**
     * All the nouns accociated with the word
     */
    private RelationShipResult noun;
    /**
     * All the verbs accociated with the word
     */
    private RelationShipResult verb;

    public BigThesaurusJSonResult() {
        //empty constructor
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BigThesaurusJSonResult that = (BigThesaurusJSonResult) o;

        if (noun != null ? !noun.equals(that.noun) : that.noun != null) return false;
        if (verb != null ? !verb.equals(that.verb) : that.verb != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = noun != null ? noun.hashCode() : 0;
        result = 31 * result + (verb != null ? verb.hashCode() : 0);
        return result;
    }

    //region Getters and Setters
    public RelationShipResult getNoun() {
        return noun;
    }

    public void setNoun(RelationShipResult noun) {
        this.noun = noun;
    }

    public RelationShipResult getVerb() {
        return verb;
    }

    public void setVerb(RelationShipResult verb) {
        this.verb = verb;
    }
    //endregion
}//End of class
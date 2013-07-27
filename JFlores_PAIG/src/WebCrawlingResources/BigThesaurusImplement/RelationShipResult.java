package WebCrawlingResources.BigThesaurusImplement;

import java.util.Arrays;

/**
 * A second part yea... what else ya gonna name it?
 * User: jflores
 * Date: 7/26/13
 * Time: 1:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class RelationShipResult {

    /**
     * Stands for  Synonyms
     */
    private String[] syn;
    /**
     * Stands for antonyms
     */
    private String[] ant;
    /**
     * Stands for user suggestions
     */
    private String[] usr;
    /**
     * Stands for similar terms
     */
    private String[] sim;

    /**
     * Stands for related terms
     */
    private String[] rel;

    public RelationShipResult() {
        //Empty constructor
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RelationShipResult that = (RelationShipResult) o;

        if (!Arrays.equals(ant, that.ant)) return false;
        if (!Arrays.equals(rel, that.rel)) return false;
        if (!Arrays.equals(sim, that.sim)) return false;
        if (!Arrays.equals(syn, that.syn)) return false;
        if (!Arrays.equals(usr, that.usr)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = syn != null ? Arrays.hashCode(syn) : 0;
        result = 31 * result + (ant != null ? Arrays.hashCode(ant) : 0);
        result = 31 * result + (usr != null ? Arrays.hashCode(usr) : 0);
        result = 31 * result + (sim != null ? Arrays.hashCode(sim) : 0);
        result = 31 * result + (rel != null ? Arrays.hashCode(rel) : 0);
        return result;
    }

    //region Getters and Setters
    public String[] getSyn() {
        return syn;
    }

    public void setSyn(String[] syn) {
        this.syn = syn;
    }

    public String[] getAnt() {
        return ant;
    }

    public void setAnt(String[] ant) {
        this.ant = ant;
    }

    public String[] getUsr() {
        return usr;
    }

    public void setUsr(String[] usr) {
        this.usr = usr;
    }

    public String[] getSim() {
        return sim;
    }

    public void setSim(String[] sim) {
        this.sim = sim;
    }

    public String[] getRel() {
        return rel;
    }

    public void setRel(String[] rel) {
        this.rel = rel;
    }
    //endregion
}//end of class
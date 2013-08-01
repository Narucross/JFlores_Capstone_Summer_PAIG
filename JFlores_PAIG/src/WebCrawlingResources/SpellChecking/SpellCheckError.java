package WebCrawlingResources.SpellChecking;

import java.util.ArrayList;
import java.util.List;

/**
 * the type of error object from the spellcheck xml
 * User: jflores
 * Date: 8/1/13
 * Time: 12:49 PM
 */
public class SpellCheckError {
    private String string;
    private String description;
    private String type;
    private List<String> suggestions;


    //region Getters and Setter
    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String[] getSuggestions() {
        return suggestions.toArray(new String[suggestions.size()]);
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }
//endregion

    public void addSuggestion(String suggestion) {
        if (this.suggestions == null) {
            this.suggestions = new ArrayList<>(2);
        }
        this.suggestions.add(suggestion);
    }

    @Override
    public String toString() {
        return "SpellCheckError{" +
                "string='" + string + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", suggestions=" + suggestions +
                '}';
    }

    public void addSuggestion(String[] suggestions) {
        if (this.suggestions == null) {
            this.suggestions = new ArrayList<>(suggestions.length);
        }
        for (String s : suggestions) {
            this.suggestions.add(s);
        }
    }
}//end of class
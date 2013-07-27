package WebCrawlingResources.BigThesaurusImplement;


import com.google.gson.Gson;
import org.apache.cxf.jaxrs.client.WebClient;

/**
 * This queries BigThesaurus's API for what it thinks
 * User: jflores
 * Date: 7/26/13
 * Time: 3:42 PM
 */
public class BigThesQuery {

    private static final String APIKey = "a1b74156ff65faae2811d2e8cc58ac88";
    private static final String ThesaurusURL = "http://words.bighugelabs.com/api/2/%s";

    public static BigThesaurusJSonResult defaultCase;

    {
        defaultCase = new BigThesaurusJSonResult();
        defaultCase.setNoun(null);
        defaultCase.setVerb(null);
    }


    /**
     * Gets the word with all its affiliations and relationships from the thesaurus,
     * This is the JSON implementation
     * @param wordOf the word we want to find relationships for
     * @return  An object
     */
    public BigThesaurusJSonResult getWordJsonResult(String wordOf) {
        String url = String.format(ThesaurusURL, APIKey);
        String result =
                WebClient.create(url)
                        .path("{word}/{format}", wordOf, "json")
                        .get(String.class);
        BigThesaurusJSonResult toReturnResult = defaultCase;
        Gson son = new Gson();
        BigThesaurusJSonResult jsonResulted = son.fromJson(result,BigThesaurusJSonResult.class);
        if (result != null && !(jsonResulted.equals(toReturnResult))) {
            toReturnResult = jsonResulted;
        }
        return toReturnResult;
    }


}//end of class
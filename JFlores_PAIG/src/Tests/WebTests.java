package Tests;

import WebCrawlingResources.BigThesaurusImplement.BigThesQuery;
import WebCrawlingResources.BigThesaurusImplement.BigThesaurusJSonResult;
import com.google.gson.Gson;

/**
 * Just some tests to see if I can create a client to big thesaurus
 * User: jflores
 * Date: 7/26/13
 * Time: 1:15 PM
 */
public class WebTests {

    public static void main(String[] args) {
        TestJSonBeanObjects();
        TestQueryThesaurus();
    }

    private static String JSONLoveResultFromThesaurus = "{\"noun\":{\"syn\":[\"passion\",\"beloved\",\"dear\",\"dearest\",\"honey\",\"sexual love\",\"erotic love\",\"lovemaking\",\"making love\",\"love life\",\"concupiscence\",\"emotion\",\"eros\",\"loved one\",\"lover\",\"object\",\"physical attraction\",\"score\",\"sex\",\"sex activity\",\"sexual activity\",\"sexual desire\",\"sexual practice\"],\"ant\":[\"hate\"],\"usr\":[\"amour\"]},\"verb\":{\"syn\":[\"love\",\"enjoy\",\"roll in the hay\",\"make out\",\"make love\",\"sleep with\",\"get laid\",\"have sex\",\"know\",\"do it\",\"be intimate\",\"have intercourse\",\"have it away\",\"have it off\",\"screw\",\"jazz\",\"eff\",\"hump\",\"lie with\",\"bed\",\"have a go at it\",\"bang\",\"get it on\",\"bonk\",\"copulate\",\"couple\",\"like\",\"mate\",\"pair\"],\"ant\":[\"hate\"]}}";

    private static void TestJSonBeanObjects() {
        BigThesaurusJSonResult result = getBigThesaurusJSonResultForLove();
        result.toString();
    }

    private static BigThesaurusJSonResult getBigThesaurusJSonResultForLove() {
        String jsonResult = JSONLoveResultFromThesaurus;
        Gson gson = new Gson();
        return gson.fromJson(jsonResult, BigThesaurusJSonResult.class);
    }

    private static void TestQueryThesaurus() {
        BigThesQuery asker = new BigThesQuery();
        BigThesaurusJSonResult result = asker.getWordJsonResult("Love");
        boolean bool = result.equals(getBigThesaurusJSonResultForLove());
        System.out.println(bool ? "Success" : "Failure");
    }
}//end of class
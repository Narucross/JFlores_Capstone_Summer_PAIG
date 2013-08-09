package Tests;

import AITypes.Personalities.FrequencyPersonPackage.FrequencyPersonality;
import WebCrawlingResources.BigThesaurusImplement.BigThesQuery;
import WebCrawlingResources.BigThesaurusImplement.BigThesaurusJSonResult;
import WebCrawlingResources.GoogleSearchImpl.QueryCustomSearch;
import WebCrawlingResources.SpellChecking.SpellCheckRestService;
import com.google.api.services.customsearch.model.Result;
import com.google.gson.Gson;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Just some tests to see if I can create a client to big thesaurus && More
 * User: jflores
 * Date: 7/26/13
 * Time: 1:15 PM
 */
public class WebTests {

    public static void main(String[] args) {
//        TestJSonBeanObjects();
//        TestQueryThesaurus();
//        Scanner scan = new Scanner(System.in);
//        String query = "Please enter the word you want results for: \n";
//        System.out.println(query);
//        query = scan.nextLine();
//        testSpellChecker(query);
//        getWord(query);
//        getBetterToString();


        //// Personality getResults
        testWebSearchAndFrequencyPersonality();
//        testPersonality();

    }

    public static String JSONLoveResultFromThesaurus = "{\"noun\":{\"syn\":[\"passion\",\"beloved\",\"dear\",\"dearest\",\"honey\",\"sexual love\",\"erotic love\",\"lovemaking\",\"making love\",\"love life\",\"concupiscence\",\"emotion\",\"eros\",\"loved one\",\"lover\",\"object\",\"physical attraction\",\"score\",\"sex\",\"sex activity\",\"sexual activity\",\"sexual desire\",\"sexual practice\"],\"ant\":[\"hate\"],\"usr\":[\"amour\"]},\"verb\":{\"syn\":[\"love\",\"enjoy\",\"roll in the hay\",\"make out\",\"make love\",\"sleep with\",\"get laid\",\"have sex\",\"know\",\"do it\",\"be intimate\",\"have intercourse\",\"have it away\",\"have it off\",\"screw\",\"jazz\",\"eff\",\"hump\",\"lie with\",\"bed\",\"have a go at it\",\"bang\",\"get it on\",\"bonk\",\"copulate\",\"couple\",\"like\",\"mate\",\"pair\"],\"ant\":[\"hate\"]}}";

    private static void TestJSonBeanObjects() {
        BigThesaurusJSonResult result = getBigThesaurusJSonResultForLove();
        String s = result.toString();
        InputStream stream = new ByteArrayInputStream(s.getBytes());
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

    private static void getWord(String word) {
        BigThesQuery asker = new BigThesQuery();
        BigThesaurusJSonResult result = asker.getWordJsonResult(word);
        System.out.println("\nResults form the query: \n\n" + result);
    }

    private static void getBetterToString() {
        BigThesaurusJSonResult loveResult = getBigThesaurusJSonResultForLove();
        System.out.println(loveResult);
    }

    private static void testSpellChecker(String tryWord) {
        SpellCheckRestService spellChecker = new SpellCheckRestService();
        String firstResult = spellChecker.isWordCorrect(tryWord);
        String result = spellChecker.correctTest();
        String result2 = spellChecker.inCorrectTest();
        String result3 = spellChecker.testingPhase(firstResult);
        System.out.printf("Chibcha defense\n%s\n\nIf It hadn't been for cotton eye joe, I'd be married long time go\n%s\n\nResult 3 or the word of: %s\n and result\n%s", result, result2, tryWord, result3);
    }

    private static void testWebSearchAndFrequencyPersonality() {
        QueryCustomSearch doc = new QueryCustomSearch();
        System.out.println("Starting test now:\n\n");
        List<Result> items = doc.getResults();
        ArrayList<String> pagesHtmlFree = doc.getHtmlFreeFromResults(items);
        // Open first page on webBrowser
        System.out.println("Opening browser");
        try {
            URL url = new URL(items.get(0).getLink());
            Desktop.getDesktop().browse(url.toURI());
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        //Frequency perosnality
        FrequencyPersonality personality = new FrequencyPersonality();

        for (String s : pagesHtmlFree) {
            Scanner scan = new Scanner(s);
            while (scan.hasNext()) {
                String currentWord = scan.next();
                if (!(currentWord.equals("") || currentWord.equals(" "))) {
                    personality.addWord(currentWord);
                }
            }
        }
        System.out.println(personality);
    }

    private static void testPersonality() {
        FrequencyPersonality person = new FrequencyPersonality();
        String Flower1st = "Flowers";
        String Flower2nd = "Dandalions";
        String Flower3rd = "Rednecks";
        String[] wordsToAdd = new String[]{Flower1st, Flower2nd, Flower3rd};
        System.out.println("Beginning operation");
        for (int i = 0; i < 10; i++) {
            for (String s : wordsToAdd) {
                person.addWord(s);
            }
        }
        System.out.println(person);
    }

}//end of class
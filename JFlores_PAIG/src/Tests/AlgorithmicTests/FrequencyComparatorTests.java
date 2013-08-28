package Tests.AlgorithmicTests;

import AITypes.Personalities.FrequencyPersonPackage.ComparisonObject;
import AITypes.Personalities.FrequencyPersonPackage.FrequencyPersonality;
import WebCrawlingResources.GoogleSearchImpl.QueryCustomSearch;
import WebCrawlingResources.WebSurfing.BasicWebExtractor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * User: jflores
 * Date: 8/28/13
 * Time: 12:48 AM
 */
public class FrequencyComparatorTests {

    public static void main(String[] args) throws IOException {

        FrequencyComparatorTests test = new FrequencyComparatorTests();
        test.temp_MainMethodVersion2();

    }

    public FrequencyComparatorTests() {
    }

    /**
     * This was to get the basic foundation down to get two frequencies together
     */
    private void temp_MainMethodV1() {
        String query = "Polar bear dogs";
        String linkToCompareTo = "http://polarbearconstruction.com/";
        QueryCustomSearch customSearch = new QueryCustomSearch();
        ArrayList<String> results = customSearch.getHtmlFreeFromResults(
                customSearch.getResultsFromQueryString(query));
        print(String.format("Successfully obtained: [%s] query results", query));

        FrequencyPersonality bot = new FrequencyPersonality();
        bot.addWordsToWeightedInstrument(bot, results);
        print(String.format("Now for the bot information{\n%s\n\n}", bot.toString()));

        FrequencyPersonality comparedGraph = new FrequencyPersonality();
        String pageContent = customSearch.getPageContentFromLink(linkToCompareTo);
        print(String.format("Obtained page content for the link: %s", linkToCompareTo));

        comparedGraph.addWordsToWeightedInstrument(comparedGraph, pageContent);
        print("Page Content added to the second frequency");
    }

    /**
     * This updated on temp_1, in that we are saving previous results as files in the tests package,
     * this way we don't burn up my free quotes from google on repeated tests, also makes it faster to re_due tests
     */
    private void temp_MainMethodVersion2() throws IOException {
//        String query = "Polar bear dogs";
//        String linkToCompareTo = "http://polarbearconstruction.com/";
        String query = "Flowers";
        String linkToCompareTo = "http://www.aboutflowers.com/flower-a-plant-information-and-photos/meanings-of-flowers.html";

        FrequencyComparatorTests test = new FrequencyComparatorTests();
        ArrayList<String> webContent = test.getResultButLookForFile(query);
        print(String.format("Successfully obtained: [%s] query results", query));

        FrequencyPersonality bot = new FrequencyPersonality();
        bot.addWordsToWeightedInstrument(bot, webContent);
        print(String.format("Now for the bot information{\n%s\n\n}", bot.toString()));

        FrequencyPersonality comparedGraph = new FrequencyPersonality();
        BasicWebExtractor webSurfer = new BasicWebExtractor();
        String pageContent = webSurfer.getHtmlFreeFromLink(linkToCompareTo);
        print(String.format("Obtained page content for the link: %s", linkToCompareTo));

        comparedGraph.addWordsToWeightedInstrument(comparedGraph, pageContent);
        print("Page Content added to the second frequency");


        ComparisonObject object = FrequencyPersonality.compareFrequencies(bot, comparedGraph);
        print("\n\n=====New Tests=====");
        print(object.toString());
    }

    public static void print(String newLine) {
        System.out.println(newLine);
    }

    // Write content to Files
    private static String contentPageDelimiter = "46696e69736865644c696e65"; /* FinishedLine in hex*/

    public ArrayList<String> getResultButLookForFile(String query) throws IOException {
        String pathway = "C:\\projects\\Capstone_Summer\\JFlores_Capstone_Summer_PAIG\\JFlores_PAIG\\src\\Tests\\AlgorithmicTests\\Files\\%s";
        ArrayList<String> results = new ArrayList<>(10);
        String convertedString = query.toLowerCase().trim() + ".txt";
        File file = new File(String.format(pathway, convertedString));
        if (file.exists()) {
            Scanner scan = new Scanner(file);
            StringBuilder currentPage = new StringBuilder();
            while (scan.hasNext()) {
                String s = scan.next();
                if (contentPageDelimiter.equals(s)) {
                    results.add(currentPage.toString());
                    currentPage = new StringBuilder();
                } else {
                    currentPage.append(s + " ");
                }
            }
            results.add(currentPage.toString());
        } else {
            QueryCustomSearch customSearch = new QueryCustomSearch();
            results = customSearch.getHtmlFreeFromResults(customSearch.getResultsFromQueryString(query));
            print("New Files found for query: [" + query + "]");
            if (file.createNewFile()) {
                FileWriter writer = new FileWriter(file);
                for (String content : results) {
                    writer.write(content + "\n" + contentPageDelimiter + "\n");
                }
                writer.close();
            }
        }
        return results;
    }


}//end of class
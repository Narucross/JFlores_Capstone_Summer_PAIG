package WebCrawlingResources.GoogleSearchImpl;

import WebCrawlingResources.WebSurfing.BasicWebExtractor;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * User: Jflores
 * Date: 8/7/13
 * Time: 7:53 AM
 */
public class QueryCustomSearch extends BasicWebExtractor {

    /**
     * Create a request for the method "cse.list". This request holds the parameters needed by the the custom-search server.
     *
     * @param Query May want to write this in the format of Google custom search, extras
     * @return a result of Google.customSearch Result API
     */
    public List<Result> getResultsFromQueryString(String Query) {
        String APIKey = "AIzaSyDE72spoHdNxzJG9pZkBnhjAxvtTr3fHBQ";
        String CustomSearchCX = "012247252479130476325:iifrxrgqhmc";
        List<Result> items = null;
        try {
            Customsearch customsearch
                    = new Customsearch(new NetHttpTransport(), new JacksonFactory(), null);
            com.google.api.services.customsearch.Customsearch.Cse.List list =
                    customsearch.cse().list(Query);
            list.setKey(APIKey);
            list.setCx(CustomSearchCX);
            Search results = list.execute();
            items = results.getItems();
            int i = 1;
            for (Result result : items) {
                System.out.println("" + (i++) + "Title:" + result.getHtmlTitle() + "\n\tLink:" + result.getLink());
            }
            // System.out.print("======= Now to testGetFlowerResults the links and print bodies =======\n\n");    readLinks(items);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Retrieves the pages usually 10 as strings from google Results
     * In addition we use JSoap to remove html,css and jquery tags
     *
     * @param items a list of google custom search Result items
     */
    public ArrayList<String> getHtmlFreeFromResults(List<Result> items) {
        ArrayList<String> builders = new ArrayList<>(items.size());
        for (Result result : items) {
            String currentResultsLink = result.getLink();
            String pageContent = getHtmlFreeFromLink(currentResultsLink);
            if (!errorStringInHex.equals(pageContent)) {
                builders.add(pageContent);
            }
        }
        return builders;
    }


    ////////////////// ==================================================================================================== \\\\\\\\\\\\\\\\\\
    //======= Test-Methods =======\\
    ////////////////// ==================================================================================================== \\\\\\\\\\\\\\\\\\

    /**
     * A test method predecessor to "getHtmlFreeFromResults" method
     */
    public void Test_ReadLinks_And_Bodies(List<Result> items) {
        Scanner scan = new Scanner(System.in);
        for (Result result : items) {
            String currentResultsLink = result.getLink();
            try {
                URL url = new URL(currentResultsLink);
                URLConnection con = url.openConnection();
                InputStream in = con.getInputStream();
                StringBuilder responceInString = buildFromInputStream(in);
                String pageHtmlFree = htmlToText(responceInString.toString());
                System.out.println("The Magic of the body:\n\n" + responceInString.toString() + "\n\n");
                System.out.println("The Body without HTML\n\n" + pageHtmlFree + "\n\n");
                System.out.println("\nPress enter for the next link:\n\n");
                scan.nextLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Opening browser");
        try {
            URL url = new URL(items.get(0).getLink());
            Desktop.getDesktop().browse(url.toURI());
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test method for the getResultsFromQueryString () above
     */
    public List<Result> testGetFlowerResults() {
        String Query = "flowers";
        String APIKey = "AIzaSyDE72spoHdNxzJG9pZkBnhjAxvtTr3fHBQ";
        String CustomSearchCX = "012247252479130476325:iifrxrgqhmc";
        List<Result> items = null;
        try {
            Customsearch customsearch
                    = new Customsearch(new NetHttpTransport(), new JacksonFactory(), null);
            com.google.api.services.customsearch.Customsearch.Cse.List list =
                    customsearch.cse().list(Query);
            list.setKey(APIKey);
            list.setCx(CustomSearchCX);
            Search results = list.execute();
            items = results.getItems();
            int i = 1;
            for (Result result : items) {
                System.out.println("" + (i++) + "Title:" + result.getHtmlTitle() + "\n\tLink:" + result.getLink());
            }
            // System.out.print("======= Now to testGetFlowerResults the links and print bodies =======\n\n");    readLinks(items);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }


    ////////////////// ==================================================================================================== \\\\\\\\\\\\\\\\\\
    //======= End of Test-Methods =======\\
    ////////////////// ==================================================================================================== \\\\\\\\\\\\\\\\\\

}//end of class
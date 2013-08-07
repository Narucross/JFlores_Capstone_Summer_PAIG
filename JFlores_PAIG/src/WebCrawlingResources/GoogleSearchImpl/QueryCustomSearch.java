package WebCrawlingResources.GoogleSearchImpl;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Scanner;

/**
 * User: Jflores
 * Date: 8/7/13
 * Time: 7:53 AM
 */
public class QueryCustomSearch {

    public static void main(String[] args) {

        QueryCustomSearch doc = new QueryCustomSearch();
        System.out.println("Starting test now:\n\n");
        doc.test();
    }

    public void test() {
        String APIKey = "AIzaSyDE72spoHdNxzJG9pZkBnhjAxvtTr3fHBQ";
        String CustomSearchCX = "012247252479130476325:iifrxrgqhmc";
        String Query = "flowers";
        try {

            Customsearch customsearch
                    = new Customsearch(new NetHttpTransport(), new JacksonFactory(), null);
            com.google.api.services.customsearch.Customsearch.Cse.List list =
                    customsearch.cse().list(Query);
            list.setKey(APIKey);
            list.setCx(CustomSearchCX);
            Search results = list.execute();

            List<Result> items = results.getItems();
            int i = 1;
            for (Result result : items) {
                System.out.println("" + (i++) + "Title:" + result.getHtmlTitle() + "\n\tLink:" + result.getLink());
            }
            System.out.println("======= Now to test the links and print bodies =======\n\n");

            readLinks(items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readLinks(List<Result> items) {
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
    }

    public String htmlToText(String html) {
        return Jsoup.parse(html).text();
    }

    public StringBuilder buildFromInputStream(InputStream inputStream) throws IOException {
        String line;
        StringBuilder builder = new StringBuilder();
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(inputStream));
        while ((line = buffReader.readLine()) != null) {
            builder.append(line);
        }
        return builder;

    }

}//end of class
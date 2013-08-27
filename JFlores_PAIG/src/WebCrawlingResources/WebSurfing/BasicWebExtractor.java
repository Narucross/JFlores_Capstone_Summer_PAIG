package WebCrawlingResources.WebSurfing;

import WebCrawlingResources.GoogleSearchImpl.QueryCustomSearch;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class BasicWebExtractor {
    public BasicWebExtractor() {
    }

    public static final String errorStringInHex = "4576656e2074686f75676820492077616c6b207468726f75676820746865206461726b6573742076616c6c65792c20492077696c6c2066656172206e6f206576696c2e";

    /**
     * Establishes a URL connection to the link's website
     * On fail it will return this.errorStringInHex as the error string
     *
     * @return String - Think of this as the Page Content
     */
    public String getPageContentFromLink(String link) {
        String returnedString = null;
        try {
            URL url = new URL(link);
            URLConnection con = url.openConnection();
            InputStream in = con.getInputStream();
            StringBuilder page = buildFromInputStream(in);
            returnedString = page.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (returnedString != null) ? returnedString : QueryCustomSearch.errorStringInHex;
    }

    public String getHtmlFreeFromLink(String link) {
        String returnedString = getPageContentFromLink(link);
        if (!QueryCustomSearch.errorStringInHex.equals(returnedString)) {
            returnedString = htmlToText(returnedString);
        }
        return returnedString;
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
}
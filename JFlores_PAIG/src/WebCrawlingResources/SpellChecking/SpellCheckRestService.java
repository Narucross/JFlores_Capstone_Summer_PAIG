package WebCrawlingResources.SpellChecking;

import org.apache.cxf.jaxrs.client.WebClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A spell checking Rest service consumer
 * User: jflores
 * Date: 8/1/13
 * Time: 11:02 AM
 */
public class SpellCheckRestService {

    private static String SpellCheckURLFormat = "http://service.afterthedeadline.com/checkDocument?key=%s&data=%s";
    private static String SpellCheckAPIKey = "PAIGITERATION53e51a5645215ec3ca76cdff6708a605";
    private String testXML = "<results><error><string>lov</string><description>Spelling</description><precontext/><suggestions><option>log</option><option>love</option><option>low</option><option>lot</option><option>lo</option></suggestions><type>spelling</type></error></results>";

    public String isWordCorrect(String word) {
        String URL = String.format(SpellCheckURLFormat, SpellCheckAPIKey, word);
        String result = WebClient.create("URL").get(String.class);
        return result;
    }


    private Document parseWebResponce(String result) {
        //get the factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document dom = null;
        try {

            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            //parse using builder to get DOM representation of the XML file
            ByteArrayInputStream stringedInputStream = new ByteArrayInputStream(result.getBytes());
            dom = db.parse(stringedInputStream);


        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException se) {
            se.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return dom;
    }

    public String testingPhase() {


        Document testDoc = parseWebResponce(testXML);
        // get root element
        Element entireDoc = testDoc.getDocumentElement();

        //get nodelist of
        ArrayList<SpellCheckError> errors = new ArrayList<>();
        NodeList list = entireDoc.getElementsByTagName("error");
        if (list != null && list.getLength() > 0) {
            for (int i = 0; i < list.getLength(); i++) {
                // get da element
                Element el = (Element) list.item(i);

                SpellCheckError error = parseWebElementsIntoObjects(el);
                errors.add(error);

            }
        } else {
            // return all good
        }
        return errors.toString();
    }


    private SpellCheckError parseWebElementsIntoObjects(Element empEl) {
        SpellCheckError error = new SpellCheckError();
        String originalString = getTextValue(empEl, "string");
        String descriptionErrorType = getTextValue(empEl, "description");
        String type = getTextValue(empEl, "type");
        //TODO
        String[] suggestions = getMultiples(empEl, "suggestions");
        error.setDescription(descriptionErrorType);
        error.setType(type);
        error.setString(originalString);
        error.addSuggestion(suggestions);
        return error;
    }


// ========================================================================================================================
// creditted to: totheriver.com -- http://www.java-samples.com/showtutorial.php?tutorialid=152
// ========================================================================================================================

    /**
     * I take a xml element and the tag name, look for the tag and get
     * the text content
     * i.e for <employee><name>John</name></employee> xml snippet if
     * the Element points to employee node and tagName is name I will return John
     *
     * @param ele
     * @param tagName
     * @return
     */
    private String getTextValue(Element ele, String tagName) {
        String textVal = null;
        NodeList nl = ele.getElementsByTagName(tagName);
        if (nl != null && nl.getLength() > 0) {
            Element currentElement = (Element) nl.item(0);
            textVal = currentElement.getFirstChild().getNodeValue();
            textVal.toString();
        }

        return textVal;
    }

    private String[] getMultiples(Element ele, String hostName) {
        ArrayList<String> options = new ArrayList<>(2);
        NodeList nodeList = ele.getElementsByTagName(hostName);
        if (nodeList != null && nodeList.getLength() > 0) {
            Element currentElement = (Element) nodeList.item(0);
            // we are at "Suggestions"
            NodeList childrenOfSuggestions = currentElement.getChildNodes();
            for (int i = 0; i < childrenOfSuggestions.getLength(); i++) {
                Element childElement = (Element) childrenOfSuggestions.item(i);
                options.add(childElement.getFirstChild().getNodeValue());
            }
//            textVal = currentElement.getFirstChild().getFirstChild().getNodeValue();
//            textVal.toString();
        }
        return options.toArray(new String[options.size()]);
    }

    /**
     * Calls getTextValue and returns a int value
     *
     * @param ele
     * @param tagName
     * @return
     */
    private int getIntValue(Element ele, String tagName) {
        //in production application you would catch the exception
        return Integer.parseInt(getTextValue(ele, tagName));
    }

}//end of class
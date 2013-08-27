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
 * To use this class, call the get errors method, if the array is empty good for you
 * User: jflores
 * Date: 8/1/13
 * Time: 11:02 AM
 */
public class SpellCheckRestService {


    private static String SpellCheckURLFormat = "http://service.afterthedeadline.com/checkDocument?key=%s&data=%s";

    private static String SpellCheckAPIKey = "PAIGITERATION53e51a5645215ec3ca76cdff6708a605";
    private String testXML = "<results><error><string>lov</string><description>Spelling</description><precontext/><suggestions><option>log</option><option>love</option><option>low</option><option>lot</option><option>lo</option></suggestions><type>spelling</type></error></results>";

    private String testXMLComplete = "<results></results>";

    public String isWordCorrect(String word) {
        String URL = String.format(SpellCheckURLFormat, SpellCheckAPIKey, word);
        return WebClient.create(URL).get(String.class);
    }

    /**
     * Returns suggestions for error's words,
     * Will return an empty String[] if there is nothing.
     */
    public String[] isWordCorrectIfNotReturnSuggestions(String word) {
        //TODO
        String URL = String.format(SpellCheckURLFormat, SpellCheckAPIKey, word);
        String result = WebClient.create(URL).get(String.class);
        ArrayList<SpellCheckError> errors = getErrors(result);
        ArrayList<String> returnedResults = new ArrayList<>(1);
        if (!errors.isEmpty()) {
            for (SpellCheckError error : errors) {
                if (error.getType().equals("spelling")) {
                    String[] currentSuggestions = error.getSuggestions();
                    if (currentSuggestions != null && currentSuggestions.length > 0) {
                        for (String suggest : currentSuggestions) {
                            returnedResults.add(suggest);
                        }
                    }
                }
            }
        }
        return returnedResults.toArray(new String[returnedResults.size()]);
    }


    private Document parseWebResponse(String result) {
        //get the factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document dom = null;
        try {

            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            //parse using builder to get DOM representation of the XML file
            ByteArrayInputStream stringedInputStream = new ByteArrayInputStream(result.getBytes());
            dom = db.parse(stringedInputStream);


        } catch (ParserConfigurationException | IOException | SAXException pce) {
            pce.printStackTrace();
        }
        return dom;
    }

    public ArrayList<SpellCheckError> getErrors(String testCase) {
        Document testDoc = parseWebResponse(testCase);
        // get root element
        Element entireDoc = testDoc.getDocumentElement();
        ArrayList<SpellCheckError> errors = new ArrayList<>();
        //get node list of elements
        NodeList list = entireDoc.getElementsByTagName("error");
        if (list != null && list.getLength() > 0) {
            for (int i = 0; i < list.getLength(); i++) {
                // get da element
                Element el = (Element) list.item(i);
                SpellCheckError error = parseWebElementsIntoObjects(el);
                errors.add(error);
            }
        }
        return errors;
    }

    public String testingPhase(String testCase) {
        return getErrors(testCase).toString();
    }


    private SpellCheckError parseWebElementsIntoObjects(Element empEl) {
        SpellCheckError error = new SpellCheckError();
        String originalString = getTextValue(empEl, "string");
        String descriptionErrorType = getTextValue(empEl, "description");
        String type = getTextValue(empEl, "type");
        //TODO
        String[] suggestions = getArray2ndChildren(empEl, "suggestions");
        error.setDescription(descriptionErrorType);
        error.setType(type);
        error.setString(originalString);
        error.addSuggestion(suggestions);
        return error;
    }


/*
========================================================================================================================
 credit to: totheriver.com -- http://www.java-samples.com/showtutorial.php?tutorialid=152
========================================================================================================================
*/

    /**
     * I take a xml element and the tag name, look for the tag and get
     * the text content
     * i.e for <employee><name>John</name></employee> xml snippet if
     * the Element points to employee node and tagName is name I will return John
     */
    private String getTextValue(Element ele, String tagName) {
        String textVal = null;
        NodeList nl = ele.getElementsByTagName(tagName);
        if (nl != null && nl.getLength() > 0) {
            Element currentElement = (Element) nl.item(0);
            textVal = currentElement.getFirstChild().getNodeValue();
        }

        return textVal;
    }

    /**
     * This is for additional children 1 level deeper : <br/>
     * [suggestions]<br/>
     * [option]This gets me!!![/option]<br/>
     * [option]And ME!!![/option]<br/>
     * [/suggestions]
     */
    private String[] getArray2ndChildren(Element ele, String hostName) {
        ArrayList<String> options = new ArrayList<>(2);
        NodeList nodeList = ele.getElementsByTagName(hostName);
        if (nodeList != null && nodeList.getLength() > 0) {
            Element currentElement = (Element) nodeList.item(0);
            // we are at "Suggestions"
            NodeList childrenOfSuggestions = currentElement.getChildNodes();
            for (int i = 0; i < childrenOfSuggestions.getLength(); i++) {
                if (childrenOfSuggestions.item(i) instanceof Element) {
                    Element childElement = (Element) childrenOfSuggestions.item(i);
                    options.add(childElement.getFirstChild().getNodeValue());
                }
            }
        }
        return options.toArray(new String[options.size()]);
    }

    /**
     * Calls getTextValue and returns a int value
     */
    @SuppressWarnings("unused")
    private int getIntValue(Element ele, String tagName) {
        //in production application you would catch the exception
        return Integer.parseInt(getTextValue(ele, tagName));
    }

    public String inCorrectTest() {
        return testingPhase(testXMLComplete);
    }

    public String correctTest() {
        return testingPhase(testXML);
    }
}//end of class
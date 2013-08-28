package AITypes.Personalities.FrequencyPersonPackage;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

/**
 * User: jflores
 * Date: 8/28/13
 * Time: 4:37 AM
 */
public class FrequencyHelper {

    public static HashSet<String> getStopWords() {
        File file = new File("C:\\projects\\Capstone_Summer\\JFlores_Capstone_Summer_PAIG\\JFlores_PAIG\\src\\AITypes\\Personalities\\FrequencyPersonPackage\\commonStopWords");
        HashSet<String> stopWords = new HashSet<>(174);
        Scanner scan;
        try {
            scan = new Scanner(file);
            while (scan.hasNext()) {
                String word = scan.next();
                stopWords.add(word);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return stopWords;
    }


}//end of class
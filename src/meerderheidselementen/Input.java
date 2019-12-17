package meerderheidselementen;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * An exercise on Brute Force and Divide & Conquer.
 * Find an algorithm that finds the majority element of an input array within a JSON-file.
 * 
 * @author Jordi Jaspers
 * @version V1.0
 */
public class Input {
    
    //Invoer van de JSON-file
    private Integer[] invoer;
    
    /**
     * A method to process the file to usable input-values.
     * 
     * @param file JSON-File with input-values.
     * @return Input-Values of the file.
     */
     public static Input[] createFromJson(String file) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(new FileReader(file), Input[].class);
        } catch (FileNotFoundException ex) {
	     System.out.println("Bestand niet gevonden.");
        }
        return null;
    }

     /**
      * a Getter for all the input-values of the JSON file.
      * @return an array of integers.
      */
    public Integer[] getInvoer() {
        return invoer;
    }
}

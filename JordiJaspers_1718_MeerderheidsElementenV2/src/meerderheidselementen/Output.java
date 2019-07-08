package meerderheidselementen;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

/**
 * An exercise on Brute Force and Divide & Conquer.
 * Find an algorithm that finds the majority element of an input array within a JSON-file.
 * 
 * @author Jordi Jaspers
 * @version V1.0
 */
public class Output {
    
    /**
     * This method will write all the solutions in a JSON-formatted text file.
     * 
     * @param uitkomsten The solutions of the algorithm.
     * @param args a string name for the new file.
     * @throws IOException An exception if there would be no input-file.
     */
    public static void saveToJson(ArrayList<Optional<Integer>> uitkomsten, String args) throws IOException{
        Gson gson = new Gson();
        String jsonOutput = gson.toJson(uitkomsten);
        try (FileWriter file = new FileWriter(args + "uit.txt")) {
            file.write(jsonOutput);
            file.close();
        }
    }
    
}

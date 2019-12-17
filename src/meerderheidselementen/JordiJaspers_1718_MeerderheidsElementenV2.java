package meerderheidselementen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 * A Java application that Returns the majority element of an Array of integers.
 * this makes use of the Divide & Conquer method.
 * 
 * @author Jordi Jaspers
 */
public class JordiJaspers_1718_MeerderheidsElementenV2 {

    /**
     * getting all input values and after the calculations returning them to the
     * output method and writing the into a file.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        meerderheidselementen.Input[] inputFiles = meerderheidselementen.Input.createFromJson(args[0]);

        ArrayList<Optional<Integer>> uitkomsten = new ArrayList<>();

        for (meerderheidselementen.Input inputFile : inputFiles){
           if(getMeerderheidsElement(inputFile.getInvoer()).isPresent()){
                uitkomsten.add(Optional.of(getMeerderheidsElement(inputFile.getInvoer()).get().getKey()));
            }
           else{
               uitkomsten.add(Optional.empty());
           }
        }
        
        try {
            meerderheidselementen.Output.saveToJson(uitkomsten, args[0]);
        } catch (IOException ex) {
            Logger.getLogger(JordiJaspers_1718_MeerderheidsElementenV2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Make use of Divide & Conquer recursion method to get the majorityElement
     * 
     * @param rij Array of Integers
     * @return Optional<Pair<Integer, Integer>>
     */
    public static Optional<Pair<Integer, Integer>> getMeerderheidsElement(Integer[] rij){
        Optional<Pair<Integer, Integer>> meerderheidsElement = Optional.empty();
        
        /**
         * Triviaal geval:
         * als de lengte van de rij 1 is.
         */
        if(rij.length == 1){
            meerderheidsElement = Optional.of(new Pair(rij[0], 1));
            return meerderheidsElement;
        }
        
        /**
         * Recursief geval:
         * Via recursie steeds de rij verdelen tot minstens 2 elementen
         * Erna vergelijk deze waarden om het meerderheidsElement te bepalen.
         */
        Integer midden = (rij.length - 1) / 2;
        Integer[] rijLinks = Arrays.copyOfRange(rij, 0, midden + 1);
        Integer[] rijRechts = Arrays.copyOfRange(rij, midden + 1, rij.length);
        
        Optional <Pair<Integer, Integer>> elementLinks = getMeerderheidsElement(rijLinks);
        Optional <Pair<Integer, Integer>> elementRechts = getMeerderheidsElement(rijRechts);
        
        if (elementLinks.isPresent() && elementRechts.isPresent()){
            int tellerLinks = elementLinks.get().getValue();
            int tellerRechts = elementRechts.get().getValue();
            
            if(Objects.equals(elementLinks.get().getKey(), elementRechts.get().getKey())){
                return Optional.of(new Pair(elementLinks.get().getKey(), tellerLinks + tellerRechts));
            }
            
            for (Integer rijRecht : rijRechts) {
                if (Objects.equals(elementLinks.get().getKey(), rijRecht)) {
                    tellerLinks ++;
                }
            }
            
            for (Integer rijLink : rijLinks) {
                if (Objects.equals(elementRechts.get().getKey(), rijLink)) {
                    tellerRechts ++;
                }
            }
            
            if (tellerLinks > rij.length / 2){
                return Optional.of(new Pair(elementLinks.get().getKey(), tellerLinks));
            }
            
            if (tellerRechts > rij.length / 2){
                return Optional.of(new Pair(elementRechts.get().getKey(), tellerRechts));
            }
            
            return Optional.empty();
            
        }
        
        if (elementLinks.isPresent()){
            int tellerLinks = elementLinks.get().getValue();
            
            for (Integer rijRecht : rijRechts) {
                if (Objects.equals(elementLinks.get().getKey(), rijRecht)) {
                    tellerLinks ++;
                }
            }
            
            if (tellerLinks > rij.length / 2){
                return Optional.of(new Pair(elementLinks.get().getKey(), tellerLinks));
            }
            
            return Optional.empty();
        }
        
        if (elementRechts.isPresent()){
            int tellerRechts = elementRechts.get().getValue();
            
            for (Integer rijLink : rijLinks) {
                if (Objects.equals(elementRechts.get().getKey(), rijLink)) {
                    tellerRechts ++;
                }
            }
            
            if (tellerRechts > rij.length / 2){
                return Optional.of(new Pair(elementRechts.get().getKey(), tellerRechts));
            }
            
            return Optional.empty();
        }
        
        return Optional.empty();
    }
}

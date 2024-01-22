package prelim.model;

import java.util.*;
import java.io.*;

public class Tester {
    public static void main(String[] args) throws Exception {
        DataHandler dataHandler = new DataHandler();
        dataHandler.readFile(new File("athlete-ni-ramon.csv"));

        // Output becomes 413 athletes because there are athletes with multiple competitions joined,
        // so we map them to one hash key

        // TreeMap because hashmap does not maintain order
        TreeMap<String, Double> avePerCountry = dataHandler.aveHeightPerCountry();

        System.out.println(avePerCountry);

        int x = 0;
        for (String key : avePerCountry.keySet()) {
            x++;
            System.out.println(x +". " + key + ": " + avePerCountry.get(key));
        }

        System.out.println();
        System.out.println("Top 3 sports with most medals");
        TreeMap<String, Integer> top3Sports = dataHandler.topSportsWithMostMedals();
        System.out.println(top3Sports);
        for (Map.Entry<String, Integer> entry : top3Sports.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " medals");
        }
    }




    }


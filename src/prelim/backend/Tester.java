package prelim.backend;

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

        int x = 0;
        for (String key : avePerCountry.keySet()) {
            x++;
            System.out.println(x +". " + key + ": " + avePerCountry.get(key));
        }

       // dataHandler.printMap();
    }
}

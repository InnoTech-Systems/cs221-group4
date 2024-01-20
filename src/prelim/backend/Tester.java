package prelim.backend;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Tester {
    public static void main(String[] args) throws Exception {
        DataHandler dataHandler = new DataHandler();
        dataHandler.readFile(new File("athlete-ni-ramon.csv"));

        // Output becomes 413 athletes because there are athletes with multiple competitions joined,
        // so we map them to one hash key
        dataHandler.printMap();
    }
}

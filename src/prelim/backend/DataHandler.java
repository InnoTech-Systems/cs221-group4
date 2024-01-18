package prelim.backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DataHandler {
    static HashMap<Integer, Athlete> athleteMap;

    public static void readFile(File file) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        athleteMap = new HashMap<Integer, Athlete>();
        Athlete athleteObj;
        int x = 0;
        String lineRead;
        while ((lineRead = fileReader.readLine()) != null) {
            String[] lineSplit = lineRead.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            athleteObj = new Athlete(lineSplit);
            if (athleteMap.containsKey(lineSplit[0])) {
                //TODO: Figure out how to map when an athlete joined two events
            } else
                athleteMap.put(Integer.parseInt(lineSplit[0]), athleteObj);
        }
    }

    public static void printMap() {
        int x=0;
        for (int key : athleteMap.keySet()) {
            x++;
            System.out.println(x + ": " + athleteMap.get(key));
        }
    }

    public static void main(String[] args) throws Exception {
        readFile(new File("athlete-ni-ramon.csv"));
        printMap();
    }
}

package prelim.backend;

import com.sun.source.tree.Tree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class DataHandler {
    static HashMap<Integer, Athlete> athleteMap;

    public static void readFile(File file) throws IOException {
        // Buffered Reader obj
        BufferedReader fileReader = new BufferedReader(new FileReader(file));
        // Instantiate Hashmap obj. Integer is the athlete id.
        athleteMap = new HashMap<Integer, Athlete>();

        Athlete athleteObj;
        String lineRead;
        while ((lineRead = fileReader.readLine()) != null) {

            // Split obj where there is a comma, but don't if it's between quotation marks
            String[] lineSplit = lineRead.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

            // if athlete ID exists in hashmap
            if (athleteMap.containsKey(Integer.parseInt(lineSplit[0]))) {

                // Get the obj in hashmap and just add the new event to the event arrayList
                athleteMap.get(Integer.parseInt(lineSplit[0])).addEventStanding(lineSplit);
            } else {
                // Add athlete to arrayList
                athleteObj = new Athlete(lineSplit);
                athleteMap.put(Integer.parseInt(lineSplit[0]), athleteObj);
            }
        }
    }

    public TreeMap<String, Double> aveHeightPerCountry() {
        HashMap<String, ArrayList<Integer>> heightCountryRecord = new HashMap<>();

        // Sort them by city
        for (Integer key : athleteMap.keySet()) {
            if (heightCountryRecord.containsKey(athleteMap.get(key).getTeam())) {
                 heightCountryRecord.get(athleteMap.get(key).getTeam()).add(athleteMap.get(key).getHeight());
            } else {
                ArrayList<Integer> heightList = new ArrayList<>();
                heightList.add(athleteMap.get(key).getHeight());
                heightCountryRecord.put(athleteMap.get(key).getTeam(), heightList);
            }
        }

        // Get Average per country
        HashMap<String, Double> avePerCountry = new HashMap<String, Double>();
        for (String key : heightCountryRecord.keySet()) {
            ArrayList<Integer> heightList = heightCountryRecord.get(key);
            double average = heightList.stream()
                    .mapToDouble(Integer::doubleValue)
                    .average()
                    .orElse(0.0);
            avePerCountry.put(key,average);
        }

        // Return only the top 3
        String top1 = avePerCountry.keySet()
                .stream()
                .max((x, y) -> avePerCountry.get(x).compareTo(avePerCountry.get(y)))
                .orElse(null);

        String top2 = avePerCountry.keySet()
                .stream()
                .filter(key -> key != top1)
                .max((x,y) -> avePerCountry.get(x).compareTo(avePerCountry.get(y)))
                .orElse(null);

        String top3 = avePerCountry.keySet()
                .stream()
                .filter(key -> key != top1 && key != top2)
                .max((x,y) -> avePerCountry.get(x).compareTo(avePerCountry.get(y)))
                .orElse(null);

        TreeMap<String, Double> top3Map = new TreeMap<>();
        top3Map.put(top3,avePerCountry.get(top3));
        top3Map.put(top2,avePerCountry.get(top2));
        top3Map.put(top1,avePerCountry.get(top1));


        return top3Map;
    }

    public static void printMap() {
        int x=0;
        for (int key : athleteMap.keySet()) {
            x++;
            System.out.println(x + ": " + athleteMap.get(key));
        }
    }

}

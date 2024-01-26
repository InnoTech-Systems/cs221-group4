package prelim.controller;

import prelim.model.Athlete;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    /**
     * Author: Tank, Rithik
     * @param topN : int
     * @return LinkedHashMap<String, Double>
     */
    public Map<String, Double> aveHeightPerCountry(int topN) {
        HashMap<String, ArrayList<Integer>> heightCountryRecord = new HashMap<>();

        // Sort them by city
        for (Integer key : athleteMap.keySet()) {
            if (heightCountryRecord.containsKey(athleteMap.get(key).getNOC())) {
                heightCountryRecord.get(athleteMap.get(key).getNOC()).add(athleteMap.get(key).getHeight());
            } else {
                ArrayList<Integer> heightList = new ArrayList<>();
                heightList.add(athleteMap.get(key).getHeight());
                heightCountryRecord.put(athleteMap.get(key).getNOC(), heightList);
            }
        }

        // Get Average per country
        Map<String, Double> avePerCountry = new LinkedHashMap<>();
        for (String key : heightCountryRecord.keySet()) {
            ArrayList<Integer> heightList = heightCountryRecord.get(key);
            double average = heightList.stream()
                    .mapToDouble(Integer::doubleValue)
                    .average()
                    .orElse(0.0);
            avePerCountry.put(key, average);
        }

        List<Double> averages = avePerCountry.values()
                .stream()
                .sorted()
                .toList();

        Map<String, Double> mapToReturn = new LinkedHashMap<>();

        ListIterator<Double> iterator = averages.listIterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        while (iterator.hasPrevious()) {
            if (mapToReturn.size() == topN)
                break;
            double average = iterator.previous();
            for (String key: avePerCountry.keySet()) {
                if (avePerCountry.get(key) == average) {
                    mapToReturn.put(key, average);
                    break;
                }
            }
        }
        return mapToReturn;
    }


    /**
     * This method tallies all the gold medals for each country based on the filters applied
     * @author Jasmin, Ramon Emmiel P.
     * @return sortedTopCountries The Map(TreeMap) that contains the top countries with gold medals
     */
    public Map<String, Integer> topCountriesMedals(int topN){
        Map<String, Integer> topCountries = new HashMap<>();

        for (Athlete athlete : athleteMap.values()){
            topCountries.put(athlete.getNOC(), topCountries.getOrDefault(athlete.getNOC(),0) + 1);
        }

        Map<String, Integer> sortedTopCountries = new TreeMap<>(
                Comparator.comparing(topCountries::get).reversed()
        );

        sortedTopCountries.putAll(topCountries);

        LinkedHashMap<String, Integer> topNCountries = new LinkedHashMap<>();
        int x=0;
        for (String key: sortedTopCountries.keySet()) {
            if (x == topN)
                break;
            topNCountries.put(key, sortedTopCountries.get(key));
            x++;
        }


        return topNCountries;
    }

    /**
     * This method calculates the total number of medals for each sport and returns a sorted map
     * with the sports as keys and their corresponding total medal counts as values.
     * Returns a TreeMap containing the top 3 sports with the highest total medal counts.
     *
     * @return A TreeMap containing the top 3 sports and their corresponding total medal counts.
     */
    public Map<String, Integer> topSportsWithMostMedals(int topN) {
        TreeMap<String, Integer> topSports = new TreeMap<>();

        // iterate through the athletes and update the topSports map
        for (Athlete athlete : athleteMap.values()) {
            List<String> sports = athlete.getSport();

            // iterate through the sports for each athlete
            for (String sport : sports) {
                List<String> medals = athlete.getMedal();

                // assuming each medal is represented as a String
                topSports.put(sport, topSports.getOrDefault(sport, 0) + medals.size());
            }
        }
        // creates a sorted map based on the total medal count in descending order
        TreeMap<String, Integer> sortedTopSports = new TreeMap<>(
                Comparator.comparing(topSports::get).reversed()
        );

        // populates the sorted map with data from the unsorted map
        sortedTopSports.putAll(topSports);

        // creates a TreeMap to store the top 3 sports and their total medal counts
        LinkedHashMap<String, Integer> top3Sports = new LinkedHashMap<>();

        // adds the top n sports to the new TreeMap
        int count = 0;
        for (Map.Entry<String, Integer> entry : sortedTopSports.entrySet()) {
            top3Sports.put(entry.getKey(), entry.getValue());
            count++;
            if (count >= topN) {
                break;
            }
        }

        return top3Sports;
    }
    /**
     * This methods finds the top 3 countries with the youngest medalists.
     * It loops through athlete data, noting the youngest medalist for each country.
     * Returns a TreeMap sorted by ages, showcasing the top 3 countries with their
     * youngest medalists' ages.
     *
     * @return A TreeMap with country names as keys and corresponding youngest medalists' ages as values.
     */
    // TODO: Implement topN
    public TreeMap<String, Integer> topCountriesYoungestMedalist(int topN) {
        // TreeMap to store countries and their youngest medalists' ages
        TreeMap<String, Integer> youngestMedalists = new TreeMap<>();

        // Loop through athletes and update youngestMedalists map
        for (Athlete athlete : athleteMap.values()) {
            // Get country and age of the current athlete
            String country = athlete.getNOC();
            int age = athlete.getAge();

            // Check if country is not in map or if current age is younger
            if (!youngestMedalists.containsKey(country) || age <= youngestMedalists.get(country)) {
                // Update youngestMedalists map with current athlete's age
                youngestMedalists.put(country, age);
            }
        }

        // TreeMap to store top countries and their youngest medalists' ages
        TreeMap<String, Integer> topCountriesYoungest = new TreeMap<>(Comparator.comparingInt(youngestMedalists::get));

        // Add top countries to the new TreeMap
        int counter = 0;
        for (Map.Entry<String, Integer> entry : youngestMedalists.entrySet()) {
            // Limit to the top 3 countries
            if (counter > topN) {
                break;
            }

            // Copy top countries and their ages to the new TreeMap
            topCountriesYoungest.put(entry.getKey(), entry.getValue());
            counter++;
        }

        return topCountriesYoungest;
    }

    public Map<String, Integer> topAthletes(int topN) {
        LinkedHashMap<String, Integer> toReturn =
                athleteMap.values().stream()
                        .collect(Collectors.toMap(
                                Athlete::getName,
                                athlete -> athlete.getMedal().size(),
                                Integer::sum
                        ))
                        .entrySet().stream()
                        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()
                                .thenComparing(Map.Entry.comparingByKey()))
                        .limit(topN)
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        return toReturn;
    }




    public static void printMap() {
        int x=0;
        for (int key : athleteMap.keySet()) {
            x++;
            System.out.println(x + ": " + athleteMap.get(key));
        }
    }

}

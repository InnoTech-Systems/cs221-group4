package prelim.controller;

import com.sun.source.tree.Tree;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Tester {
    public static void main(String[] args) throws IOException {
        DataHandler dh = new DataHandler();
        dh.readFile(new File("athlete-ni-ramon.csv"));
       // dh.printMap();
     //   dh.topAthletes();

        Map<String, Double> treeMap = dh.aveHeightPerCountry(10);

        int x = 0;
        for(String key : treeMap.keySet()) {
            x++;
            System.out.println(x + ". " + key + ", " + treeMap.get(key));
        }
    }
}

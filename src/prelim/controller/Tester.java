package prelim.controller;

import java.io.File;
import java.io.IOException;

public class Tester {
    public static void main(String[] args) throws IOException {
        DataHandler dh = new DataHandler();
        dh.readFile(new File("athlete-ni-ramon.csv"));
        dh.printMap();
        dh.topAthletes();
    }
}

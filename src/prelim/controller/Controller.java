package prelim.controller;

import prelim.model.Athlete;
import prelim.view.GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Controller {
    private DataHandler dataHandler;
    private GUI view;

    public Controller(DataHandler dataHandler, GUI view) {
        this.dataHandler = dataHandler;
        this.view = view;

        //String[] columnNames = {"ID", "Name", "Sex", "Age", "Height", "Weight", "Team", "NOC", "Year", "Season", "City", "Sport",
        //        "Event", "Medals"};
        //view.updateTable(columnNames);

        // Button Implementation
        // Create method to map filterOptions to an integer
        // Create a switch-case block to update the model

        this.view.showResultsButton.addActionListener(new showResultsListener());
    }

    public class showResultsListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            System.out.println("button click reached");
            checkFilter(checkTopNFilterOption());
        }
    }

    public int checkTopNFilterOption() {
        int choice = view.filterDropdown1.getSelectedIndex();
        switch (choice) {
            case 1 -> { return 3; }
            case 2 -> { return 5; }
            case 3 -> { return 10; }
            default -> { return -1; }
        }
    }

    public void checkFilter(int topN) {
        int choice = view.filterDropdown2.getSelectedIndex();
        System.out.println("check filter reached");
        switch (choice) {
            case 1 -> athletesMostMedal(topN);
            case 2 -> countriesMostMedal(topN);
            case 3 -> topCountriesYoungestMedalist(topN);
            case 4 -> topSportsWithMostMedals(topN);
            case 5 -> aveHeightPerCountry(topN);
        }
    }
    public void athletesMostMedal(int topN) {
        Map<String, Integer> map = dataHandler.topAthletes(topN);
        String[] columnNames = {"Name", "Gold Medals"};
        view.updateTable(columnNames);
        for (String key : map.keySet()) {
            view.model.addRow(new Object[]{key, map.get(key)});
        }
    }

    public void countriesMostMedal(int topN) {
        Map<String, Integer> map = dataHandler.topCountriesMedals(topN);
        String[] columnNames = {"Country", "Gold Medals"};
        System.out.println("Countries Most Medal reached");
        view.updateTable(columnNames);
        for (String key : map.keySet()) {
            view.model.addRow(new Object[]{key, map.get(key)});
        }
    }

    public void topCountriesYoungestMedalist(int topN) {
        Map<String, Integer> map = dataHandler.topCountriesYoungestMedalist(topN);
        String[] columnNames = {"Country", "Youngest Medalist Age"};
        view.updateTable(columnNames);
        for (String key : map.keySet()) {
            view.model.addRow(new Object[]{key, map.get(key)});
        }
    }

    public void topSportsWithMostMedals(int topN) {
        Map<String, Integer> map = dataHandler.topSportsWithMostMedals(topN);
        String[] columnNames = {"Sports", "Gold Medals"};
        view.updateTable(columnNames);
        for (String key : map.keySet()) {
            view.model.addRow(new Object[]{key, map.get(key)});
        }
    }

    public void aveHeightPerCountry(int topN) {
        Map<String, Double> map = dataHandler.aveHeightPerCountry(topN);
        String[] columnNames = {"Country", "Average Height of Athletes"};
        view.updateTable(columnNames);
        for (String key : map.keySet()) {
            view.model.addRow(new Object[]{key, map.get(key)});
        }
    }
}

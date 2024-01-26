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

        String[] columnNames = {"ID", "Name", "Sex", "Age", "Height", "Weight", "Team", "NOC", "Year", "Season", "City", "Sport",
                "Event", "Medals"};
        view.updateTable(columnNames);

        // Button Implementation
        // Create method to map filterOptions to an integer
        // Create a switch-case block to update the model

        this.view.showResultsButton.addActionListener(new showResultsListener());
    }

    public class showResultsListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
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
        switch (choice) {
            case 1 -> athletesMostMedal(topN);
            case 2 -> countriesMostMedal(topN);
        }
    }
    public void athletesMostMedal(int topN) {
        Map<String, Integer> map = dataHandler.topAthletes(topN);
        String[] columnNames = {"Name", "Gold Medals"};
        view.updateTable(columnNames);
        for (String key : map.keySet()) {
            view.model.addRow(new Object[]{key, map.get(key)});
        }
        view.revalidate();
    }

    public void countriesMostMedal(int topN) {
        Map<String, Integer> map = dataHandler.topCountriesMedals(topN);
        String[] columnNames = {"Country", "Gold Medals"};
        view.updateTable(columnNames);
        for (String key : map.keySet()) {
            view.model.addRow(new Object[]{key, map.get(key)});
        }
    }
}

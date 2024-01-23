package prelim.controller;

import prelim.model.Athlete;
import prelim.view.GUI;

public class Controller {
    private DataHandler dataHandler;
    private GUI view;

    public Controller(DataHandler dataHandler, GUI view) {
        this.dataHandler = dataHandler;
        this.view = view;
    }
}

package prelim;

import prelim.controller.Controller;
import prelim.controller.DataHandler;
import prelim.view.GUI;

import javax.swing.*;
import javax.xml.crypto.Data;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final GUI gui = new GUI();
                gui.setVisible(true);

                DataHandler dh = new DataHandler();
                Controller controller = new Controller(dh, gui);
            }
        });
    }
}

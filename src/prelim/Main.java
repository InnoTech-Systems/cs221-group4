package prelim;

import prelim.controller.Controller;
import prelim.controller.DataHandler;
import prelim.view.GUI;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final GUI gui = new GUI();
                gui.setVisible(true);

                DataHandler dh = new DataHandler();
                try {
                    dh.readFile(new File("athlete-ni-ramon.csv"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Controller controller = new Controller(dh, gui);
            }
        });
    }
}

package prelim.frontend;

import javax.swing.*;

public class GUI extends JFrame {
    public GUI() {
        super("Olympic Data");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900,600);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

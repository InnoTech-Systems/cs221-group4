package prelim.frontend;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class GUI extends JFrame {
    /**
     * Header of the frame containing navigational buttons.
     */
    private JPanel header;

    /**
     * Main body of the frame
     */
    private JPanel main;

    /**
     * Sidebar of the frame containing control buttons.
     */
    private JPanel aside;

    /**
     * Footer of the frame containing copyright notices.
     */
    private JPanel footer;

    /**
     * Constructs GUI.
     */
    public GUI() {
        super("Olympic Data");
        setLayout(new BorderLayout());

        header = populateHeader();
        header.setPreferredSize(new Dimension(650,70));
        add(header, BorderLayout.NORTH);

        aside = populateAside();
        aside.setPreferredSize(new Dimension(350,600));
        add(aside, BorderLayout.WEST);

        main = populateMain();
        main.setPreferredSize(new Dimension(650,700));
        add(main, BorderLayout.CENTER);

        footer = populateFooter();
        footer.setPreferredSize(new Dimension(650,30));
        add(footer, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,700);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel populateHeader() {
        JPanel container = new JPanel();
        container.setBackground(Color.BLACK);

        return container;
    }

    private JPanel populateAside() {
        JPanel container = new JPanel();
        container.setBackground(Color.BLUE);

        return container;
    }

    private JPanel populateMain() {
        JPanel container = new JPanel();

        return container;
    }

    private JPanel populateFooter() {
        JPanel container = new JPanel();
        container.setBackground(Color.cyan);

        return container;
    }
}

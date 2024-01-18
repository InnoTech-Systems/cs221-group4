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
     * Footer of the frame containing copyright notices.
     */
    private JPanel footer;

    private final Resources resources = new Resources();

    /**
     * Constructs GUI.
     */
    public GUI() {
        super("Olympic Data");
        setLayout(new BorderLayout());

        header = populateHeader();
        header.setPreferredSize(new Dimension(650,70));
        add(header, BorderLayout.NORTH);

        main = populateMain();
        main.setPreferredSize(new Dimension(1000,700));
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

    /**
     * Populates the components of the Header panel
     * @return panel with fixed populated elements and components
     */
    private JPanel populateHeader() {
        JPanel container = new JPanel();
        container.setBackground(resources.polynesianBlue);
        container.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Navigation Buttons

        // Home
        JButton btnHome = createButton("Home", Color.WHITE);
        btnHome.setHorizontalAlignment(SwingConstants.LEFT);
        btnHome.setVerticalTextPosition(SwingConstants.CENTER);;
        btnHome.setFont(btnHome.getFont().deriveFont(18f));
        container.add(btnHome);

        // Results
        JButton btnResults = createButton("Olympic Results", Color.WHITE);
        btnResults.setHorizontalAlignment(SwingConstants.LEFT);
        btnResults.setVerticalAlignment(SwingConstants.CENTER);
        btnResults.setFont(btnHome.getFont().deriveFont(18f));
        container.add(btnResults);

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
        container.setLayout(new BorderLayout());
        container.setBackground(resources.polynesianBlue);

        int copyrightSymbolCodePoint = 169 ;
        String cSymbol = Character.toString(copyrightSymbolCodePoint);

        JLabel copyright = new JLabel(cSymbol + "Deans 5 2024");
        copyright.setHorizontalAlignment(SwingConstants.CENTER);
        copyright.setForeground(Color.WHITE);
        container.add(copyright, BorderLayout.CENTER);



        return container;
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        // button.setFont(resources.montserratBold);
        button.setForeground(color);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        return button;
    }
}

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
        container.setLayout(new BorderLayout());

        // Filter Panel
        JPanel filterPanel = new JPanel();
        filterPanel.setPreferredSize(new Dimension(900, 60));
        filterPanel.setBorder(BorderFactory.createLineBorder(Color.RED));

        // Adding a label
        JLabel filterLabel = new JLabel("FILTER:");
        filterLabel.setFont(new Font("Arial", Font.BOLD, 16));
        filterPanel.add(filterLabel);

        // Adding a dropdown box with filter options
        String[] filterOptions = {"Choose here",  // Prompt text
                "Top 5 Athletes with Most Medals",
                "Top 5 Countries with Most Medals",
                "Average Height of Female Athletes",
                "Top 3 Sports with Most Medals",
                "Top 3 Highest Average Height of Athletes per Country"};

        JComboBox<String> filterDropdown = new JComboBox<>(filterOptions);
        filterDropdown.setFont(new Font("Arial", Font.BOLD, 16)); // Setting bold font
        filterDropdown.setSelectedIndex(0);  // Set default selection to the prompt
        filterPanel.add(filterDropdown);

        container.add(filterPanel, BorderLayout.NORTH);

        // Table Panel
        JPanel tablePanel = new JPanel();
        tablePanel.setPreferredSize(new Dimension(900, 600));
        tablePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        container.add(tablePanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(900, 40));
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
        container.add(buttonPanel, BorderLayout.SOUTH);

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

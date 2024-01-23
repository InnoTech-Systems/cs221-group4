package prelim.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

    public JComboBox<String> filterDropdown;

    public DefaultTableModel model;

    public JButton btnHome;

    public JButton btnResults;

    /**
     * UI components
     */
    private final Resources resources = new Resources();

    /**
     * CardLayout object used to switch in between pages.
     */
    private CardLayout cardLayout = new CardLayout(0,0);

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
        btnHome = createButton("Home", Color.WHITE);
        btnHome.setHorizontalAlignment(SwingConstants.LEFT);
        btnHome.setVerticalTextPosition(SwingConstants.CENTER);;
        btnHome.setFont(btnHome.getFont().deriveFont(18f));
        container.add(btnHome);

        // Results
        btnResults = createButton("Olympic Results", Color.WHITE);
        btnResults.setHorizontalAlignment(SwingConstants.LEFT);
        btnResults.setVerticalAlignment(SwingConstants.CENTER);
        btnResults.setFont(btnHome.getFont().deriveFont(18f));
        container.add(btnResults);

        btnHome.addActionListener(e -> {
            cardLayout.show(main, "home");
        });

        btnResults.addActionListener(e -> {
            cardLayout.show(main, "table");
        });

        return container;
    }

    /**
     * Creates the components of the main panel for the main frame.
     * @return UI components for the main panel.
     */
    private JPanel populateMain() {
        JPanel container = new JPanel();

        try {
            container.setLayout(cardLayout);

            JPanel homePanel = populateHome();
            container.add(homePanel, "home");

            JPanel tablePanel = populateTable();
            container.add(tablePanel, "table");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return container;
    }

    private JPanel populateTable() {
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());

        // Filter Panel
        JPanel filterPanel = new JPanel();
        filterPanel.setPreferredSize(new Dimension(900, 40));

        // Adding a label
        JLabel filterLabel = new JLabel("FILTER:");
        filterLabel.setFont(new Font("Arial", Font.BOLD, 16));
        filterPanel.add(filterLabel);

        // Adding a dropdown box with filter options
        String[] filterOptions = {"Choose here",  // Prompt text
                "Top 5 Athletes with Most Medals",
                "Top 5 Countries with Most Medals",
                "Top 5 Countries with Youngest Medalists",
                "Top 5 Sports with Most Medals",
                "Top 5 Highest Average Height of Athletes per Country"};

        filterDropdown = new JComboBox<>(filterOptions);
        filterDropdown.setFont(new Font("Arial", Font.BOLD, 16));
        filterDropdown.setSelectedIndex(0);
        filterPanel.add(filterDropdown);

        container.add(filterPanel, BorderLayout.NORTH);

        // Table Panel
        JPanel tablePanel = new JPanel();
        tablePanel.setPreferredSize(new Dimension(900, 600));

        // Column names for the table
        String[] columnNames = {"ID", "Name", "Sex", "Age", "Height", "Weight", "Team", "NOC", "Year", "Season", "City", "Sport", "Event", "Medals"};

        // DefaultTableModel with 14 columns
        model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);

        // Set the preferred size of the JTable
        table.setPreferredScrollableViewportSize(new Dimension(950, 445));

        // Labels of the columns
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        // Set the border for the scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), 1));

        table.getTableHeader().setResizingAllowed(true);
        table.getTableHeader().setReorderingAllowed(false);
        tablePanel.add(scrollPane);
        container.add(tablePanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(900, 50));

        // Adding a button
        JButton showResultsButton = new JButton("SHOW RESULTS");
        showResultsButton.setFont(new Font("Arial", Font.BOLD, 16));
        showResultsButton.setPreferredSize(new Dimension(180, 40));
        showResultsButton.setBackground(resources.polynesianBlue);
        showResultsButton.setForeground(Color.BLACK);
        buttonPanel.add(showResultsButton);

        container.add(buttonPanel, BorderLayout.SOUTH);

        return container;
    }

    private JPanel populateHome() throws IOException {
        JPanel container = new JPanel();
        container.setBorder(new EmptyBorder(10,20,10,20));
        container.setLayout(new BorderLayout());
        container.setBackground(Color.WHITE);

        BufferedImage olympicsLogo = ImageIO.read(new File("img/olympics-logo.png"));
        Image image = olympicsLogo.getScaledInstance(300,154, Image.SCALE_DEFAULT);


        JLabel olympicsLbl = new JLabel(new ImageIcon(image));
        container.add(olympicsLbl, BorderLayout.NORTH);


        JTextArea instructionsTxtArea = new JTextArea();

        String instructions =
                "The data consists of <specify data>." +"\n\n" +
                        "To view desired Results of the Olympics based on the given pre-defined set of data," +
                        "simply choose desired filter in the dropdown menu in the Olympic Results page.";

        instructionsTxtArea.setText(instructions);
        instructionsTxtArea.setPreferredSize(new Dimension(500, 260));
        instructionsTxtArea.setWrapStyleWord(true);
        instructionsTxtArea.setLineWrap(true);
        instructionsTxtArea.setOpaque(false);
        instructionsTxtArea.setEditable(false);
        instructionsTxtArea.setFocusable(false);
        instructionsTxtArea.setForeground(Color.BLACK);

        container.add(instructionsTxtArea, BorderLayout.CENTER);

        return container;
    }

    /**
     * Creates the components for the footer panel for the main frame.
     * @return UI components of the footer
     */
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

    /**
     * Template for creating a button
     * @param text given text of Button
     * @param color given color of button.
     * @return JButton with user-defined look.
     */
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

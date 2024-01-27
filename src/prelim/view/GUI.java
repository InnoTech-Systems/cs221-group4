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
    public JButton showResultsButton;
    public JComboBox<String> filterDropdown2;

    public JComboBox<String> filterDropdown1;

    public JTable table ;
    /**
     * Header of the frame containing navigational buttons.
     */
    private JPanel header;

    /**
     * Main body of the frame
     */
    public JPanel main;

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

        footer = populateFooter();
        footer.setPreferredSize(new Dimension(650,30));
        add(footer, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,700);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void updateTable(String[] columnNames) {
        main = populateMain(columnNames);
        main.setPreferredSize(new Dimension(1000,700));
        add(main, BorderLayout.CENTER);
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
    public JPanel populateMain(String[] columnNames) {
        JPanel container = new JPanel();

        try {
            container.setLayout(cardLayout);

            JPanel homePanel = populateHome();
            container.add(homePanel, "home");

            JPanel tablePanel = populateTable(columnNames);
            container.add(tablePanel, "table");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return container;
    }

    private JPanel populateTable(String[] columnNames) {
        JPanel container = new JPanel();
        container.setLayout(new BorderLayout());

        // Filter Panel
        JPanel filterPanel = new JPanel();
        filterPanel.setPreferredSize(new Dimension(900, 40));

        // Adding labels
        JLabel filterLabel1 = new JLabel("TOP n");
        filterLabel1.setFont(new Font("Arial", Font.BOLD, 16));
        filterPanel.add(filterLabel1);

        // Adding the first dropdown box with filter options
        String[] filterOptions1 = {"Choose here", "Top 3", "Top 5", "Top 10"};
        filterDropdown1 = new JComboBox<>(filterOptions1);
        filterDropdown1.setFont(new Font("Arial", Font.BOLD, 16));
        filterDropdown1.setSelectedIndex(0);
        filterPanel.add(filterDropdown1);

        JLabel filterLabel2 = new JLabel("Medal Statistics:");
        filterLabel2.setFont(new Font("Arial", Font.BOLD, 16));
        filterPanel.add(filterLabel2);

        // Adding the second dropdown box with filter options
        String[] filterOptions2 = {"Choose here", "Athletes with Most Medals", "Countries with Most Medals", "Countries with Youngest Medalists", "Sports with Most Medals", "Highest Average Height of Athletes per Country"};
        filterDropdown2 = new JComboBox<>(filterOptions2);
        filterDropdown2.setFont(new Font("Arial", Font.BOLD, 16));
        filterDropdown2.setSelectedIndex(0);
        filterPanel.add(filterDropdown2);

        container.add(filterPanel, BorderLayout.NORTH);

        // Table Panel
        JPanel tablePanel = new JPanel();
        tablePanel.setPreferredSize(new Dimension(900, 600));

        // Column names for the table
       // String[] columnNames = {"ID", "Name", "Sex", "Age", "Height", "Weight", "Team", "NOC", "Year", "Season", "City", "Sport", "Event", "Medals"};

        // DefaultTableModel with 14 columns
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);

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
        showResultsButton = new JButton("SHOW RESULTS");
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
                "The dataset consists of:" +"\n" +
                        "\t1. Female Athletes\n" +
                        "\t2. Athletes within 25 years old or older\n" +
                        "\t3. Athletes not more than 70 kilograms.\n" +
                        "\t4. Games within 2004 or later.\n" +
                        "\t5. Summer games\n" +
                        "\t6. Gold medalists\n\n" +
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

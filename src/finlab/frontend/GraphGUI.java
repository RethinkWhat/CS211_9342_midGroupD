package finlab.frontend;

import finlab.backend.GraphUtility;

import javax.swing.*;
import java.awt.*;

public class GraphGUI extends JFrame {
    private Resources resources = new Resources();
    private GraphUtility graphUtility = new GraphUtility();
    private JPanel panelMain;
    private JPanel panelCenter;
    private JPanel panelSidebar;
    private JPanel panelHome;
    private JPanel panelDfs;
    private JPanel panelBfs;
    private JPanel panelShortestPath;
    private JPanel panelCard;
    private final CardLayout cardLayout = new CardLayout(10,20);
    private final CardLayout cardLayout2 = new CardLayout(10,20);

    /**
     * TODO: Documentation
     */
    public GraphGUI() {
        super("Graph Traversal and Shortest Path");

        panelMain = new JPanel();
        panelMain.setLayout(new BorderLayout());
        panelMain.setPreferredSize(new Dimension(900,500));
        add(panelMain);

        panelCenter = new JPanel();
        panelCenter.setPreferredSize(new Dimension(700,500));
        panelMain.add(panelCenter, BorderLayout.CENTER);

        panelCard = new JPanel();
        panelCard.setLayout(cardLayout);
        panelCenter.add(panelCard);

        panelSidebar = populateSidebar();
        panelSidebar.setPreferredSize(new Dimension(200,500));
        panelSidebar.setBackground(Color.BLACK);
        panelMain.add(panelSidebar, BorderLayout.WEST);

        panelHome = populatePanelHome();
        panelHome.setPreferredSize(new Dimension(700,500));
        panelCard.add(panelHome, "home");

        panelDfs = populatePanelVisualize();
        panelDfs.setPreferredSize(new Dimension(700,500));
        panelCard.add(panelDfs,"visualize");

        panelBfs = populatePanelTraverse();
        panelDfs.setPreferredSize(new Dimension(700,500));
        panelCard.add(panelBfs, "traverse");

        panelShortestPath = populatePanelPath();
        panelShortestPath.setPreferredSize(new Dimension(700,500));
        panelCard.add(panelShortestPath, "path");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900,500);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    } // end of GraphGUI constructor

    private JPanel populateSidebar() {
        JPanel panelSidebar = new JPanel();
        panelSidebar.setBackground(resources.richBlack);
        panelSidebar.setLayout(new BorderLayout());

        JPanel panelButtons = new JPanel();
        panelButtons.setBackground(resources.richBlack);
        panelButtons.setPreferredSize(new Dimension(200, 300));
        panelButtons.setLayout(new GridBagLayout());
        panelSidebar.add(panelButtons, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.weightx = 50;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridy = 0;
        JButton btnHome = createButtonSidebar("Home");
        btnHome.setForeground(resources.ultravioletBlue);
        panelButtons.add(btnHome, gbc);

        gbc.gridy = 1;
        JButton btnDfs = createButtonSidebar("Graph Visualization");
        btnDfs.setForeground(resources.white);
        panelButtons.add(btnDfs, gbc);

        gbc.gridy = 2;
        JButton btnBfs = createButtonSidebar("Traverse Graph");
        btnBfs.setForeground(resources.white);
        panelButtons.add(btnBfs, gbc);

        gbc.gridy = 3;
        JButton btnShortestPath = createButtonSidebar("Find Shortest Path");
        btnShortestPath.setForeground(resources.white);
        panelButtons.add(btnShortestPath, gbc);

        gbc.gridy = 4;
        JButton btnExit = createButtonSidebar("Exit");
        btnExit.setForeground(resources.white);
        panelButtons.add(btnExit, gbc);

        btnHome.addActionListener(e-> {
            cardLayout.show(panelCard, "home");
            btnColorChangeSidebar(btnHome, btnBfs, btnDfs, btnShortestPath);
        });

        btnDfs.addActionListener(e -> {
            cardLayout.show(panelCard, "visualize");
            btnColorChangeSidebar(btnDfs, btnHome, btnBfs, btnShortestPath);
        });


        btnBfs.addActionListener(e -> {
            cardLayout.show(panelCard, "traverse");
            btnColorChangeSidebar(btnBfs, btnHome, btnDfs, btnShortestPath);
        });

        btnShortestPath.addActionListener(e -> {
            cardLayout.show(panelCard, "path");
            btnColorChangeSidebar(btnShortestPath, btnDfs, btnHome, btnBfs);
        });

        btnExit.addActionListener(e-> System.exit(0));

        return panelSidebar;
    }

    private JPanel populatePanelHome() {
        JPanel panelHome = new JPanel();
        panelHome.setLayout(new BorderLayout());

        JPanel panelInstructions = new JPanel();
        panelHome.setPreferredSize(new Dimension(700, 250));
        panelHome.add(panelInstructions, BorderLayout.NORTH);

        JLabel labelInstructions = new JLabel("Instructions here.");
        panelInstructions.add(labelInstructions);

        JPanel panelImport = new JPanel();
        panelImport.setLayout(new FlowLayout());
        panelImport.setPreferredSize(new Dimension(700, 250));
        panelHome.add(panelImport, BorderLayout.SOUTH);

        JButton buttonImport = createButtonHome("Import File");
        panelImport.add(buttonImport);

        buttonImport.addActionListener(e-> {
            JFileChooser fileChooser = new JFileChooser("graphs");
            fileChooser.showOpenDialog(null);

            try {
                graphUtility.readFile(fileChooser.getSelectedFile());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        JButton buttonNext = createButtonHome("Next");
        panelImport.add(buttonNext);

        panelHome.repaint();
        panelHome.revalidate();
        return panelHome;
    }

    private JPanel populatePanelVisualize() {
        JPanel panelVisualization = new JPanel();



        return panelVisualization;
    }

    private JPanel populatePanelTraverse() {
        JPanel panelDfs = new JPanel();



        return panelDfs;
    }

    private JPanel populatePanelPath() {
        JPanel panelBfs = new JPanel();

        return panelBfs;
    }

    private JButton createButtonSidebar(String text) {
        JButton button = new JButton(text);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusable(false);
        return button;
    }

    private JButton createButtonHome(String text) {
        JButton button = new JButton(text);
        button.setFont(resources.montserratBold);
        button.setForeground(resources.white);
        button.setBackground(resources.ultravioletBlue);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusable(false);
        return button;
    }

    private void btnColorChangeSidebar(JButton button1, JButton button2, JButton button3, JButton button4) {
        button1.setForeground(resources.ultravioletBlue);
        button2.setForeground(resources.white);
        button3.setForeground(resources.white);
        button4.setForeground(resources.white);
    }
}

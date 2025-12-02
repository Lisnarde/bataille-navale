package view;

import javax.swing.*;
import java.awt.*;
import view.components.*;

public class ConfigScreen extends JPanel {
    private GraphicalView _parent;

    public ConfigScreen(GraphicalView parent) {
        _parent = parent;
        setLayout(new BorderLayout());

        //TITRE
        add(new TitleBanner(), BorderLayout.NORTH);

        //CENTRE (CONTENU)
        JPanel configPanel = new JPanel(new GridLayout(1, 2));

        //panel de gauche dans configPanel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(50, 40, 20, 40));

        //pseudo
        JPanel pseudoPanel = new JPanel();
        pseudoPanel.setLayout(new FlowLayout());

        JLabel pseudoLabel = new JLabel("Pseudo:");
        pseudoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        pseudoPanel.add(pseudoLabel);

        JTextField pseudoField = new JTextField();
        pseudoField.setFont(new Font("Arial", Font.PLAIN, 16));
        pseudoField.setPreferredSize(new Dimension(200, 30));
        pseudoPanel.add(pseudoField);

        leftPanel.add(pseudoPanel);

        //taille de la grille
        JPanel sizeGridPanel = new JPanel();
        sizeGridPanel.setLayout(new BoxLayout(sizeGridPanel, BoxLayout.Y_AXIS));

        JLabel sizeLabel = new JLabel("Choisissez la taille de la grille :");
        sizeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        sizeGridPanel.add(sizeLabel);

        ButtonGroup sizeGroup = new ButtonGroup();
        JPanel sizePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (int i = 6; i <= 9; i++) {
            JRadioButton sizeButton = new JRadioButton(String.valueOf(i));
            sizeGroup.add(sizeButton);
            sizePanel.add(sizeButton);
            if (i == 6) sizeButton.setSelected(true);
        }
        sizeGridPanel.add(sizePanel);

        leftPanel.add(sizeGridPanel);

        /*
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(new JLabel("Activer le mode île ?"));
        ButtonGroup islandGroup = new ButtonGroup();
        JPanel islandPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JRadioButton islandYes = new JRadioButton("Oui");
        JRadioButton islandNo = new JRadioButton("Non", true);
        islandGroup.add(islandYes);
        islandGroup.add(islandNo);
        islandPanel.add(islandYes);
        islandPanel.add(islandNo);
        leftPanel.add(islandPanel);

        configPanel.add(leftPanel);

        // 🚢 Partie droite : configuration des bateaux
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        rightPanel.add(new JLabel("Configuration des bateaux :"));
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(new JLabel("truc type bato")); // à remplacer plus tard

        configPanel.add(rightPanel);
        add(configPanel, BorderLayout.CENTER);
        
         */
    }
}

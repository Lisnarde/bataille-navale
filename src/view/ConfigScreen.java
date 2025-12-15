package view;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

import controller.GameController;
import controller.NavigationController;
import model.Game;
import view.components.*;

public class ConfigScreen extends JPanel {
    private GameController _controller;
    private Game _model;
    private NavigationController _navigationController;

    private JTextField _pseudoField;
    private ButtonGroup _sizeGroup;
    private ButtonGroup _islandGroup;


    public ConfigScreen(GameController controller, Game model, NavigationController navigationController) {
        _controller = controller;
        _model = model;
        _navigationController = navigationController;
        setLayout(new BorderLayout());

        //titre
        add(new TitleBanner(), BorderLayout.NORTH);

        //centre (contenu)
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

        _pseudoField = new JTextField();
        _pseudoField.setFont(new Font("Arial", Font.PLAIN, 16));
        _pseudoField.setPreferredSize(new Dimension(200, 30));
        pseudoPanel.add(_pseudoField);

        leftPanel.add(pseudoPanel);

        //taille de la grille
        JPanel sizeGridPanel = new JPanel();
        sizeGridPanel.setLayout(new FlowLayout());

        JLabel sizeLabel = new JLabel("Choisissez la taille de la grille :");
        sizeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        sizeGridPanel.add(sizeLabel);

        _sizeGroup = new ButtonGroup();
        JPanel sizePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (int i = 6; i <= 10; i++) {
            JRadioButton sizeButton = new JRadioButton(String.valueOf(i));
            sizeButton.setFont(new Font("Arial", Font.PLAIN, 16));
            _sizeGroup.add(sizeButton);
            sizePanel.add(sizeButton);
            if (i == 6) sizeButton.setSelected(true);
        }
        sizeGridPanel.add(sizePanel);

        leftPanel.add(sizeGridPanel);

        //mode île
        JPanel islandPanel = new JPanel();
        islandPanel.setLayout(new FlowLayout());

        JLabel islandLabel = new JLabel("Activer le mode île ?");
        islandLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        islandPanel.add(islandLabel);

        _islandGroup = new ButtonGroup();
        JPanel islandButtonPanel = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        JRadioButton ouiButton = new JRadioButton("oui");
        ouiButton.setFont(new Font("Arial", Font.PLAIN, 16));
        JRadioButton nonButton = new JRadioButton("non");
        nonButton.setFont(new Font("Arial", Font.PLAIN, 16));
        islandButtonPanel.add(ouiButton);
        _islandGroup.add(ouiButton);
        islandButtonPanel.add(nonButton);
        _islandGroup.add(nonButton);
        islandPanel.add(islandButtonPanel);

        leftPanel.add(islandPanel);

        //ajout du panel de gauche
        configPanel.add(leftPanel);


        //panel de droite dans configPanel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(50, 40, 20, 40));


        rightPanel.add(new JLabel("Configuration des bateaux :"));
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(new JLabel("truc type bato")); // à remplacer plus tard

        configPanel.add(rightPanel);
        add(configPanel, BorderLayout.CENTER);

        //bouton suivant
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        Button next = new Button("suivant");
        next.setPreferredSize(new Dimension(120, 40));
        next.setFont(new Font("Arial", Font.BOLD, 16));
        next.setBackground(new Color(107, 97, 210));
        next.setForeground(Color.WHITE);

        next.addActionListener(e -> validation());

        buttonPanel.add(next);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void validation() {
        //pseudo
        String pseudo = _pseudoField.getText();

        // taille de la grille
        String selectedSize = "";
        for (Enumeration<AbstractButton> buttons = _sizeGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton b = buttons.nextElement();
            if (b.isSelected()) {
                selectedSize = b.getText();
                break;
            }
        }
        int gridSize = Integer.parseInt(selectedSize);

        // mode île
        String islandMode = "";
        for (Enumeration<AbstractButton> buttons = _islandGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton b = buttons.nextElement();
            if (b.isSelected()) {
                islandMode = b.getText();
                break;
            }
        }
        boolean isIslandMode = islandMode.equals("oui");

        _controller.setPlayerName(pseudo);
        _controller.setGrid(gridSize,isIslandMode);

        _navigationController.showPlacement();
    }
}

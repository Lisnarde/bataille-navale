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

    private JPanel _leftPanel;
    private JPanel _rightPanel;

    private JTextField _pseudoField;
    private ButtonGroup _sizeGroup;
    private ButtonGroup _islandGroup;

    private JButton _btnAccepter;
    private JButton _btnSuivant;


    public ConfigScreen(GameController controller, Game model, NavigationController navigationController) {
        _controller = controller;
        _model = model;
        _navigationController = navigationController;
        draw();
        setEnabledPanel(_leftPanel,true);
        setEnabledPanel(_rightPanel,false);
    }

    private void draw() {
        setLayout(new BorderLayout());

        //titre
        add(new TitleBanner(), BorderLayout.NORTH);

        Font font = new Font("Arial",Font.PLAIN, 16);

        //centre (contenu)
        JPanel configPanel = new JPanel(new GridLayout(1, 2));
        add(configPanel, BorderLayout.CENTER);

        //panel de gauche dans configPanel
        _leftPanel = new JPanel(new BorderLayout());
        _leftPanel.setBorder(BorderFactory.createEmptyBorder(50, 40, 20, 40));
        configPanel.add(_leftPanel);

        // Contenu du left panel
        JPanel leftPanelContent = new JPanel();
        leftPanelContent.setLayout(new BoxLayout(leftPanelContent, BoxLayout.Y_AXIS));
        _leftPanel.add(leftPanelContent,BorderLayout.CENTER);

        //pseudo
        JPanel pseudoPanel = new JPanel();
        pseudoPanel.setLayout(new FlowLayout());

        JLabel pseudoLabel = new JLabel("Pseudo:");
        pseudoLabel.setFont(font);
        pseudoPanel.add(pseudoLabel);

        _pseudoField = new JTextField();
        _pseudoField.setFont(font);
        _pseudoField.setPreferredSize(new Dimension(200, 30));
        pseudoPanel.add(_pseudoField);

        leftPanelContent.add(pseudoPanel);

        //taille de la grille
        JPanel sizeGridPanel = new JPanel();
        sizeGridPanel.setLayout(new FlowLayout());

        JLabel sizeLabel = new JLabel("Choisissez la taille de la grille :");
        sizeLabel.setFont(font);
        sizeGridPanel.add(sizeLabel);

        _sizeGroup = new ButtonGroup();
        JPanel sizePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (int i = 6; i <= 10; i++) {
            JRadioButton sizeButton = new JRadioButton(String.valueOf(i));
            sizeButton.setFont(font);
            _sizeGroup.add(sizeButton);
            sizePanel.add(sizeButton);
            if (i == 6) sizeButton.setSelected(true);
        }
        sizeGridPanel.add(sizePanel);

        leftPanelContent.add(sizeGridPanel);

        //mode île
        JPanel islandPanel = new JPanel();
        islandPanel.setLayout(new FlowLayout());

        JLabel islandLabel = new JLabel("Activer le mode île ?");
        islandLabel.setFont(font);
        islandPanel.add(islandLabel);

        _islandGroup = new ButtonGroup();
        JPanel islandButtonPanel = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        JRadioButton ouiButton = new JRadioButton("oui");
        ouiButton.setFont(font);
        JRadioButton nonButton = new JRadioButton("non");
        nonButton.setFont(font);
        islandButtonPanel.add(ouiButton);
        _islandGroup.add(ouiButton);
        islandButtonPanel.add(nonButton);
        _islandGroup.add(nonButton);
        islandPanel.add(islandButtonPanel);

        leftPanelContent.add(islandPanel);

        //Panel du bouton Accepter
        JPanel leftButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        _leftPanel.add(leftButtonPanel, BorderLayout.SOUTH);

        // Bouton accepter du panel gauche
        _btnAccepter = new JButton("Accepter");
        _btnAccepter.setPreferredSize(new Dimension(120, 40));
        _btnAccepter.setFont(new Font("Arial", Font.BOLD, 16));
        _btnAccepter.setBackground(new Color(107, 97, 210));
        _btnAccepter.setForeground(Color.WHITE);
        _btnAccepter.addActionListener(e -> accepter());
        leftButtonPanel.add(_btnAccepter);



        //panel de droite dans configPanel
        _rightPanel = new JPanel(new BorderLayout());
        _rightPanel.setBorder(BorderFactory.createEmptyBorder(50, 40, 20, 40));
        configPanel.add(_rightPanel);

        // Panel de contenu du panel de droite
        JPanel rightPanelContent = new JPanel();
        rightPanelContent.setLayout(new BoxLayout(rightPanelContent, BoxLayout.Y_AXIS));
        _rightPanel.add(rightPanelContent, BorderLayout.CENTER);

        // texte dans le panel droite
        rightPanelContent.add(new JLabel("Configuration des bateaux :"));
        rightPanelContent.add(Box.createVerticalStrut(10));
        rightPanelContent.add(new JLabel("truc type bato")); // à remplacer plus tard

        //Panel du bouton suivant du panel droit
        JPanel rightButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        _rightPanel.add(rightButtonPanel,BorderLayout.SOUTH);

        // Bouton suivant du panel droit
        _btnSuivant = new JButton("Suivant");
        _btnSuivant.setPreferredSize(new Dimension(120, 40));
        _btnSuivant.setFont(new Font("Arial", Font.BOLD, 16));
        _btnSuivant.setBackground(new Color(107, 97, 210));
        _btnSuivant.setForeground(Color.WHITE);
        _btnSuivant.addActionListener(e -> suivant());
        rightButtonPanel.add(_btnSuivant);
    }

    // Désactive récursivement tous les enfants du panel
    private void setEnabledPanel(JComponent panel, boolean enabled) {
        panel.setEnabled(enabled);

        for (Component c : panel.getComponents()) {
            c.setEnabled(enabled);
            if (c instanceof JComponent jc) {
                setEnabledPanel(jc, enabled);
            }
        }
    }

    private void accepter() {
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

        setEnabledPanel(_leftPanel,false);
        setEnabledPanel(_rightPanel,true);
    }

    private void suivant() {
        _navigationController.showPlacement();
    }
}

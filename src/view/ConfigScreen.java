package view;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import controller.GameController;
import controller.NavigationController;
import model.Game;
import model.ShipTypes;
import view.components.*;
import view.themes.Theme;

public class ConfigScreen extends JPanel {
    private GameController _controller;
    private Game _model;
    private NavigationController _navigationController;
    private Theme _theme;

    private JPanel _leftPanel;
    private JPanel _rightPanel;

    private JTextField _pseudoField;
    private ButtonGroup _sizeGroup;
    private ButtonGroup _islandGroup;
    private int _maxShipsCells;
    private JLabel _labelMaxCellsShip;
    private JLabel _labelUsedCells;
    private JLabel _labelNumAircraftCarrier;
    private JLabel _labelNumCruiser;
    private JLabel _labelNumDestroyer;
    private JLabel _labelNumSubmarine;
    private JLabel _labelNumTorpedo;
    private JButton _plusAircraftCarrier;
    private JButton _plusCruiser;
    private JButton _plusDestroyer;
    private JButton _plusSubmarine;
    private JButton _plusTorpedo;
    private JButton _minusAircraftCarrier;
    private JButton _minusCruiser;
    private JButton _minusDestroyer;
    private JButton _minusSubmarine;
    private JButton _minusTorpedo;

    private JButton _btnAccepter;
    private JButton _btnSuivant;


    public ConfigScreen(GameController controller, Game model, NavigationController navigationController, Theme theme) {
        _controller = controller;
        _model = model;
        _navigationController = navigationController;
        _theme = theme;
        draw();
        setEnabledPanel(_leftPanel,true);
        setEnabledPanel(_rightPanel,false);
    }

    private void draw() {
        setLayout(new BorderLayout());
        /*JLabel background = new JLabel(new ImageIcon("res/background.jpg"));
        background.setLayout(new BorderLayout());
        add(background);
        */

        //titre
        add(new TitleBanner(), BorderLayout.NORTH);

        //centre (contenu)
        JPanel configPanel = new JPanel(new GridLayout(1, 2));
        configPanel.setOpaque(false);
        add(configPanel, BorderLayout.CENTER);

        //panel de gauche dans configPanel
        _leftPanel = new JPanel(new BorderLayout());
        _leftPanel.setOpaque(false);
        _leftPanel.setBorder(BorderFactory.createEmptyBorder(50, 40, 20, 40));
        configPanel.add(_leftPanel);

        // Contenu du left panel
        JPanel leftPanelContent = new JPanel();
        leftPanelContent.setOpaque(false);
        leftPanelContent.setLayout(new BoxLayout(leftPanelContent, BoxLayout.Y_AXIS));
        _leftPanel.add(leftPanelContent,BorderLayout.CENTER);

        //pseudo
        JPanel pseudoPanel = new JPanel();
        pseudoPanel.setOpaque(false);
        pseudoPanel.setLayout(new FlowLayout());

        JLabel pseudoLabel = new JLabel("Pseudo:");
        pseudoLabel.setFont(_theme.normalFont());
        pseudoPanel.add(pseudoLabel);

        _pseudoField = new JTextField();
        _pseudoField.setFont(_theme.normalFont());
        _pseudoField.setPreferredSize(new Dimension(200, 30));
        pseudoPanel.add(_pseudoField);

        leftPanelContent.add(pseudoPanel);

        //taille de la grille
        JPanel sizeGridPanel = new JPanel();
        sizeGridPanel.setOpaque(false);
        sizeGridPanel.setLayout(new FlowLayout());

        JLabel sizeLabel = new JLabel("Choisissez la taille de la grille :");
        sizeLabel.setFont(_theme.normalFont());
        sizeGridPanel.add(sizeLabel);

        _sizeGroup = new ButtonGroup();
        JPanel sizePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sizePanel.setOpaque(false);
        for (int i = 6; i <= 10; i++) {
            JRadioButton sizeButton = new JRadioButton(i+"");
            sizeButton.setFont(_theme.normalFont());
            _sizeGroup.add(sizeButton);
            sizePanel.add(sizeButton);
            if (i == 6) sizeButton.setSelected(true);
        }
        sizeGridPanel.add(sizePanel);

        leftPanelContent.add(sizeGridPanel);

        //mode île
        JPanel islandPanel = new JPanel();
        islandPanel.setOpaque(false);
        islandPanel.setLayout(new FlowLayout());

        JLabel islandLabel = new JLabel("Activer le mode île ? (non accessible pour les grilles de tailles 6) ");
        islandLabel.setFont(_theme.normalFont());
        islandPanel.add(islandLabel);

        _islandGroup = new ButtonGroup();
        JPanel islandButtonPanel = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        islandButtonPanel.setOpaque(false);
        JRadioButton ouiButton = new JRadioButton("oui");
        ouiButton.setFont(_theme.normalFont());
        JRadioButton nonButton = new JRadioButton("non");
        nonButton.setFont(_theme.normalFont());
        nonButton.setSelected(true);
        islandButtonPanel.add(ouiButton);
        _islandGroup.add(ouiButton);
        islandButtonPanel.add(nonButton);
        _islandGroup.add(nonButton);
        islandPanel.add(islandButtonPanel);

        leftPanelContent.add(islandPanel);

        //Panel du bouton Accepter
        JPanel leftButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        leftButtonPanel.setOpaque(false);
        _leftPanel.add(leftButtonPanel, BorderLayout.SOUTH);

        // Bouton accepter du panel gauche
        _btnAccepter = new JButton("Accepter");
        _theme.buttonTheme(_btnAccepter);
        _btnAccepter.addActionListener(e -> accept());
        leftButtonPanel.add(_btnAccepter);

        //panel de droite dans configPanel
        _rightPanel = new JPanel(new BorderLayout());
        _rightPanel.setOpaque(false);
        _rightPanel.setBorder(BorderFactory.createEmptyBorder(50, 40, 20, 40));
        configPanel.add(_rightPanel);

        // Panel de contenu du panel de droite
        JPanel rightPanelContent = new JPanel();
        rightPanelContent.setOpaque(false);
        rightPanelContent.setLayout(new GridLayout(0, 1, 0, 5));
        _rightPanel.add(rightPanelContent, BorderLayout.CENTER);

        JPanel panelHeader = new JPanel();
        panelHeader.setOpaque(false);
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.Y_AXIS));

        // texte dans le panel de droite
        JLabel titleConfig = new JLabel("Configuration des bateaux :");
        titleConfig.setFont(_theme.titleFont());
        panelHeader.add(titleConfig);

        // label d'indication du nombre max de cellules bateaux
        _labelMaxCellsShip = new JLabel();
        _labelMaxCellsShip.setFont(_theme.boldFont());
        panelHeader.add(_labelMaxCellsShip);

        //label du nombre de cases utilisées
        _labelUsedCells = new JLabel();
        _labelUsedCells.setFont(_theme.normalFont());
        panelHeader.add(_labelUsedCells);

        rightPanelContent.add(panelHeader);

        // lignes de porte-avion
        JPanel lineAircraftCarrier = new JPanel();
        lineAircraftCarrier.setOpaque(false);
        lineAircraftCarrier.setLayout(new BoxLayout(lineAircraftCarrier, BoxLayout.X_AXIS));
        lineAircraftCarrier.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        JLabel labelAircraftCarrier = new JLabel("Porte-avion (5 cases)");
        labelAircraftCarrier.setFont(_theme.normalFont());
        labelAircraftCarrier.setPreferredSize(new Dimension(160, 25));
        lineAircraftCarrier.add(labelAircraftCarrier);
        lineAircraftCarrier.add(Box.createHorizontalStrut(10));

        _minusAircraftCarrier = new JButton("-");
        _theme.buttonConfigMinusTheme(_minusAircraftCarrier);
        _labelNumAircraftCarrier =new JLabel("1");
        _labelNumAircraftCarrier.setFont(_theme.normalFont());
        _labelNumAircraftCarrier.setPreferredSize(new Dimension(30, 25));
        _labelNumAircraftCarrier.setHorizontalAlignment(SwingConstants.CENTER);
        _plusAircraftCarrier = new JButton("+");
        _theme.buttonConfigPlusTheme(_plusAircraftCarrier);

        _minusAircraftCarrier.addActionListener(e -> {
            int value = Integer.parseInt(_labelNumAircraftCarrier.getText());
            value = decrementNumberOfShip(value);
            _labelNumAircraftCarrier.setText(String.valueOf(value));
            updateUsedCells();
        });
        _plusAircraftCarrier.addActionListener(e -> {
            int value = Integer.parseInt(_labelNumAircraftCarrier.getText());
            value = incrementNumberOfShip(value);
            _labelNumAircraftCarrier.setText(String.valueOf(value));
            updateUsedCells();
        });

        lineAircraftCarrier.add(_minusAircraftCarrier);
        lineAircraftCarrier.add(Box.createHorizontalStrut(20));
        lineAircraftCarrier.add(_labelNumAircraftCarrier);
        lineAircraftCarrier.add(Box.createHorizontalStrut(20));
        lineAircraftCarrier.add(_plusAircraftCarrier);

        rightPanelContent.add(lineAircraftCarrier);

        // ligne du croiseur
        JPanel lineCruiser = new JPanel();
        lineCruiser.setOpaque(false);
        lineCruiser.setLayout(new BoxLayout(lineCruiser, BoxLayout.X_AXIS));
        lineCruiser.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        JLabel labelCruiser = new JLabel("Croiseur (4 cases)");
        labelCruiser.setFont(_theme.normalFont());
        labelCruiser.setPreferredSize(new Dimension(160, 25));
        lineCruiser.add(labelCruiser);
        lineCruiser.add(Box.createHorizontalStrut(10));

        _minusCruiser = new JButton("-");
        _theme.buttonConfigMinusTheme(_minusCruiser);
        _labelNumCruiser = new JLabel("1");
        _labelNumCruiser.setFont(_theme.normalFont());
        _labelNumCruiser.setPreferredSize(new Dimension(30, 25));
        _labelNumCruiser.setHorizontalAlignment(SwingConstants.CENTER);
        _plusCruiser = new JButton("+");
        _theme.buttonConfigPlusTheme(_plusCruiser);

        _minusCruiser.addActionListener(e -> {
            int valueAircraftCarrier = Integer.parseInt(_labelNumCruiser.getText());
            valueAircraftCarrier = decrementNumberOfShip(valueAircraftCarrier);
            _labelNumCruiser.setText(String.valueOf(valueAircraftCarrier));
            updateUsedCells();
        });
        _plusCruiser.addActionListener(e -> {
            int valueAircraftCarrier = Integer.parseInt(_labelNumCruiser.getText());
            valueAircraftCarrier = incrementNumberOfShip(valueAircraftCarrier);
            _labelNumCruiser.setText(String.valueOf(valueAircraftCarrier));
            updateUsedCells();
        });

        lineCruiser.add(_minusCruiser);
        lineCruiser.add(Box.createHorizontalStrut(20));
        lineCruiser.add(_labelNumCruiser);
        lineCruiser.add(Box.createHorizontalStrut(20));
        lineCruiser.add(_plusCruiser);

        rightPanelContent.add(lineCruiser);

        // ligne du contre torpilleur
        JPanel lineDestroyer = new JPanel();
        lineDestroyer.setOpaque(false);
        lineDestroyer.setLayout(new BoxLayout(lineDestroyer, BoxLayout.X_AXIS));
        lineDestroyer.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));


        JLabel labelDestroyer = new JLabel("Contre-Torpilleur (3 cases)");
        labelDestroyer.setFont(_theme.normalFont());
        labelDestroyer.setPreferredSize(new Dimension(160, 25));
        lineDestroyer.add(labelDestroyer);
        lineDestroyer.add(Box.createHorizontalStrut(10));

        _minusDestroyer = new JButton("-");
        _theme.buttonConfigMinusTheme(_minusDestroyer);
        _labelNumDestroyer = new JLabel("1");
        _labelNumDestroyer.setFont(_theme.normalFont());
        _labelNumDestroyer.setPreferredSize(new Dimension(30, 25));
        _labelNumDestroyer.setHorizontalAlignment(SwingConstants.CENTER);
        _plusDestroyer = new JButton("+");
        _theme.buttonConfigPlusTheme(_plusDestroyer);

        _minusDestroyer.addActionListener(e -> {
            int valueCruiser = Integer.parseInt(_labelNumDestroyer.getText());
            valueCruiser = decrementNumberOfShip(valueCruiser);
            _labelNumDestroyer.setText(String.valueOf(valueCruiser));
            updateUsedCells();
        });
        _plusDestroyer.addActionListener(e -> {
            int valueCruiser = Integer.parseInt(_labelNumDestroyer.getText());
            valueCruiser = incrementNumberOfShip(valueCruiser);
            _labelNumDestroyer.setText(String.valueOf(valueCruiser));
            updateUsedCells();
        });

        lineDestroyer.add(_minusDestroyer);
        lineDestroyer.add(Box.createHorizontalStrut(20));
        lineDestroyer.add(_labelNumDestroyer);
        lineDestroyer.add(Box.createHorizontalStrut(20));
        lineDestroyer.add(_plusDestroyer);

        rightPanelContent.add(lineDestroyer);

        // ligne du sous-marin
        JPanel lineSubmarine = new JPanel();
        lineSubmarine.setOpaque(false);
        lineSubmarine.setLayout(new BoxLayout(lineSubmarine, BoxLayout.X_AXIS));
        lineSubmarine.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        JLabel labelSubmarine = new JLabel("Sous-marin (3 cases)");
        labelSubmarine.setFont(_theme.normalFont());
        labelSubmarine.setPreferredSize(new Dimension(160, 25));
        lineSubmarine.add(labelSubmarine);
        lineSubmarine.add(Box.createHorizontalStrut(10));

        _minusSubmarine = new JButton("-");
        _theme.buttonConfigMinusTheme(_minusSubmarine);
        _labelNumSubmarine = new JLabel("1");
        _labelNumSubmarine.setFont(_theme.normalFont());
        _labelNumSubmarine.setPreferredSize(new Dimension(30, 25));
        _labelNumSubmarine.setHorizontalAlignment(SwingConstants.CENTER);
        _plusSubmarine = new JButton("+");
        _theme.buttonConfigPlusTheme(_plusSubmarine);

        _minusSubmarine.addActionListener(e -> {
            int valueSubmarine = Integer.parseInt(_labelNumSubmarine.getText());
            valueSubmarine = decrementNumberOfShip(valueSubmarine);
            _labelNumSubmarine.setText(String.valueOf(valueSubmarine));
            updateUsedCells();
        });
        _plusSubmarine.addActionListener(e -> {
            int valueSubmarine = Integer.parseInt(_labelNumSubmarine.getText());
            valueSubmarine = incrementNumberOfShip(valueSubmarine);
            _labelNumSubmarine.setText(String.valueOf(valueSubmarine));
            updateUsedCells();
        });

        lineSubmarine.add(_minusSubmarine);
        lineSubmarine.add(Box.createHorizontalStrut(20));
        lineSubmarine.add(_labelNumSubmarine);
        lineSubmarine.add(Box.createHorizontalStrut(20));
        lineSubmarine.add(_plusSubmarine);

        rightPanelContent.add(lineSubmarine);

        //ligne du torpilleur
        JPanel lineTorpedo = new JPanel();
        lineTorpedo.setOpaque(false);
        lineTorpedo.setLayout(new BoxLayout(lineTorpedo, BoxLayout.X_AXIS));
        lineTorpedo.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        JLabel labelTorpedo = new JLabel("Torpilleur (2 cases)");
        labelTorpedo.setFont(_theme.normalFont());
        labelTorpedo.setPreferredSize(new Dimension(160, 25));
        lineTorpedo.add(labelTorpedo);
        lineTorpedo.add(Box.createHorizontalStrut(10));

        _minusTorpedo = new JButton("-");
        _theme.buttonConfigMinusTheme(_minusTorpedo);
        _labelNumTorpedo = new JLabel("1");
        _labelNumTorpedo.setFont(_theme.normalFont());
        _labelNumTorpedo.setPreferredSize(new Dimension(30, 25));
        _labelNumTorpedo.setHorizontalAlignment(SwingConstants.CENTER);
        _plusTorpedo = new JButton("+");
        _theme.buttonConfigPlusTheme(_plusTorpedo);

        _minusTorpedo.addActionListener(e -> {
            int valueTorpedo = Integer.parseInt(_labelNumTorpedo.getText());
            valueTorpedo = decrementNumberOfShip(valueTorpedo);
            _labelNumTorpedo.setText(String.valueOf(valueTorpedo));
            updateUsedCells();
        });
        _plusTorpedo.addActionListener(e -> {
            int valueTorpedo = Integer.parseInt(_labelNumTorpedo.getText());
            valueTorpedo = incrementNumberOfShip(valueTorpedo);
            _labelNumTorpedo.setText(String.valueOf(valueTorpedo));
            updateUsedCells();
        });

        lineTorpedo.add(_minusTorpedo);
        lineTorpedo.add(Box.createHorizontalStrut(20));
        lineTorpedo.add(_labelNumTorpedo);
        lineTorpedo.add(Box.createHorizontalStrut(20));
        lineTorpedo.add(_plusTorpedo);

        rightPanelContent.add(lineTorpedo);

        //Panel du bouton suivant du panel droit
        JPanel rightButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightButtonPanel.setOpaque(false);
        _rightPanel.add(rightButtonPanel,BorderLayout.SOUTH);

        // Bouton suivant du panel droit
        _btnSuivant = new JButton("Suivant");
        _theme.buttonTheme(_btnSuivant);
        _btnSuivant.addActionListener(e -> next());
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

    private void accept() {
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

        // Vérification des champs
        if (pseudo.equals("") || gridSize<6) {return;}

        if (gridSize == 6 && isIslandMode) {
            JOptionPane.showMessageDialog(this, "Vous ne pouvez pas sélectionner le mode île et une grille de taille 6");
            return;
        }

        _controller.setPlayerName(pseudo);
        _controller.setGrid(gridSize,isIslandMode);
        _maxShipsCells = _model.getMaxShipsCellsPossible();
        _labelMaxCellsShip.setText("Nombre maximum de cases pour les bateaux : "+ _maxShipsCells);
        _labelUsedCells.setText("Cases utilisées : "+ "17 / " +_maxShipsCells);

        setEnabledPanel(_leftPanel,false);
        setEnabledPanel(_rightPanel,true);
        updateUsedCells();
    }

    private void next() {
        Map<ShipTypes,Integer> map = new HashMap<>();
        map.put(ShipTypes.AircraftCarrier,Integer.parseInt(_labelNumAircraftCarrier.getText()));
        map.put(ShipTypes.Cruiser,Integer.parseInt(_labelNumCruiser.getText()));
        map.put(ShipTypes.Destroyer,Integer.parseInt(_labelNumDestroyer.getText()));
        map.put(ShipTypes.Submarine,Integer.parseInt(_labelNumSubmarine.getText()));
        map.put(ShipTypes.Torpedo,Integer.parseInt(_labelNumTorpedo.getText()));
        if (_controller.setNumberPerShip(map)) {
            _navigationController.showPlacement();
        }
    }
    private int decrementNumberOfShip(int number){
        if(number>1) {
            return number - 1;
        }
        return number;
    }
    private int incrementNumberOfShip(int number){
        return number+1;
    }
    private void updateUsedCells() {
        if (Integer.parseInt(_labelNumAircraftCarrier.getText())==1)
        {
            _minusAircraftCarrier.setEnabled(false);
        }
        else{
            _minusAircraftCarrier.setEnabled(true);
        }
        if (Integer.parseInt(_labelNumCruiser.getText())==1)
        {
            _minusCruiser.setEnabled(false);
        }
        else{
            _minusCruiser.setEnabled(true);
        }
        if (Integer.parseInt(_labelNumDestroyer.getText())==1)
        {
            _minusDestroyer.setEnabled(false);
        }
        else{
            _minusDestroyer.setEnabled(true);
        }
        if (Integer.parseInt(_labelNumSubmarine.getText())==1)
        {
            _minusSubmarine.setEnabled(false);
        }
        else{
            _minusSubmarine.setEnabled(true);
        }
        if (Integer.parseInt(_labelNumTorpedo.getText())==1)
        {
            _minusTorpedo.setEnabled(false);
        }
        else{
            _minusTorpedo.setEnabled(true);
        }

        int aircraft = Integer.parseInt(_labelNumAircraftCarrier.getText()) * 5;
        int cruiser = Integer.parseInt(_labelNumCruiser.getText()) * 4;
        int destroyer = Integer.parseInt(_labelNumDestroyer.getText()) * 3;
        int submarine = Integer.parseInt(_labelNumSubmarine.getText()) * 3;
        int torpedo = Integer.parseInt(_labelNumTorpedo.getText()) * 2;

        int total = aircraft + cruiser + destroyer + submarine + torpedo;
        int remaining = _maxShipsCells - total;

        _labelUsedCells.setText("Cases utilisées : " + total + " / " + _maxShipsCells);

        // Désactiver tous les boutons +
        _plusAircraftCarrier.setEnabled(remaining >= 5);
        _plusCruiser.setEnabled(remaining >= 4);
        _plusDestroyer.setEnabled(remaining >= 3);
        _plusSubmarine.setEnabled(remaining >= 3);
        _plusTorpedo.setEnabled(remaining >= 2);
    }
}

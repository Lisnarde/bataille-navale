package view;

import controller.GameController;
import controller.NavigationController;
import model.*;
import model.traps.TrapTypes;
import model.weapons.WeaponTypes;
import view.components.GridMode;
import view.components.GridPanel;
import view.components.TitleBanner;
import view.themes.Theme;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

public class PlacementScreen extends JPanel implements ViewPanel {
    private GameController _controller;
    private Game _model;
    private NavigationController _navigationController;
    private Theme _theme;

    private GridPanel _gridPanel;


    public PlacementScreen(GameController controller, Game model, NavigationController navigationController, Theme theme) {
        _controller = controller;
        _model = model;
        _navigationController = navigationController;
        _theme = theme;
    }

    @Override
    public void onShow() {
        TerminalView terminalView = new TerminalView(_model,_controller);

        setLayout(new BorderLayout());

        //titre
        add(new TitleBanner(), BorderLayout.NORTH);

        //panel du content principal
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        add(content, BorderLayout.CENTER);

        //panel quantité de bateaux
        JPanel shipQuantity = new JPanel();
        shipQuantity.setLayout(new BoxLayout(shipQuantity, BoxLayout.Y_AXIS));
        JLabel titleShipQuantity = new JLabel("Quantité de bateaux");
        titleShipQuantity.setFont(new Font("Arial", Font.PLAIN, 32));
        shipQuantity.add(titleShipQuantity);

        JLabel aircraftCarrierLabel = new JLabel("Porte-avion : 1");
        aircraftCarrierLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        shipQuantity.add(aircraftCarrierLabel);

        JLabel cruiserLabel = new JLabel("Croiseur : 1");
        cruiserLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        shipQuantity.add(cruiserLabel);

        JLabel destroyerLabel = new JLabel("Contre-torpilleur : 1");
        destroyerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        shipQuantity.add(destroyerLabel);

        JLabel submarineLabel = new JLabel("Sous-marin : 1");
        submarineLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        shipQuantity.add(submarineLabel);

        JLabel torpedoLabel = new JLabel("Torpilleur : 1");
        torpedoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        shipQuantity.add(torpedoLabel);

        content.add(shipQuantity);

        //grille de placement
        _gridPanel = new GridPanel(_model, _controller, GridMode.PLACEMENT);
        _gridPanel.setTypePlacement(ShipTypes.AircraftCarrier,-1,-1);
        _gridPanel.setAxisPlacement(Axis.HORIZONTAL);
        content.add(_gridPanel);


        //panel des bateaux à placer avec axis et pièges
        JPanel shipPanel = new JPanel();
        shipPanel.setLayout(new BoxLayout(shipPanel, BoxLayout.Y_AXIS));
        content.add(shipPanel);

        //Les bateaux
        ButtonGroup grpTypes = new ButtonGroup();
        for (ShipTypes shipType : ShipTypes.values()) {
            JRadioButton btn = new JRadioButton(shipType.name());
            _theme.buttonTheme(btn);
            btn.addActionListener(actionEvent -> _gridPanel.setTypePlacement(shipType,-1,-1));
            grpTypes.add(btn);
            shipPanel.add(btn);
            if (shipType == ShipTypes.AircraftCarrier) {btn.setSelected(true);}
        }

        shipPanel.add(Box.createRigidArea(new Dimension(10,10)));

        for (int i=0; i<_model.getTrapInventorySize(); i++) {
            TrapTypes trapType = _model.getTrapInInventory(i);
            JRadioButton btn = new JRadioButton(trapType.name());
            btn.putClientProperty("trapIndex",i);
            _theme.buttonTheme(btn);
            btn.addActionListener(actionEvent -> _gridPanel.setTypePlacement(null,(int)btn.getClientProperty("trapIndex"),-1));
            grpTypes.add(btn);
            shipPanel.add(btn);
        }

        shipPanel.add(Box.createRigidArea(new Dimension(10,20)));


        // Les armes à placer si mode île
        if (_model.isIslandModeActivated()) {
            for (int w=0; w< _model.getGlobalWeaponInventorySize(); w++) {
                WeaponTypes weaponType = _model.getWeaponInGlobalInventory(w);
                JRadioButton btn = new JRadioButton(weaponType.name());
                btn.putClientProperty("weaponIndex",w);
                _theme.buttonTheme(btn);
                btn.addActionListener(actionEvent -> _gridPanel.setTypePlacement(null,-1,(int)btn.getClientProperty("weaponIndex")));
                grpTypes.add(btn);
                shipPanel.add(btn);
            }
        }

        shipPanel.add(Box.createRigidArea(new Dimension(10,20)));


        // Les axis
        ButtonGroup grpAxis = new ButtonGroup();
        for (Axis axis : Axis.values()) {
            JRadioButton btn = new JRadioButton(axis.name());
            _theme.buttonTheme(btn);
            btn.addActionListener(actionEvent -> _gridPanel.setAxisPlacement(axis));
            grpAxis.add(btn);
            shipPanel.add(btn);
            if (axis == Axis.HORIZONTAL) {btn.setSelected(true);}
        }

        shipPanel.add(Box.createRigidArea(new Dimension(10,20)));



        //bouton suivant
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton next = new JButton("Suivant");
        _theme.buttonTheme(next);

        next.addActionListener(e -> validation());

        buttonPanel.add(next);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void validation() {
        _navigationController.showGame(_gridPanel);
    }
}

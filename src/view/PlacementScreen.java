package view;

import controller.GameController;
import controller.NavigationController;
import model.Game;
import view.components.GridPanel;
import view.components.TitleBanner;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

public class PlacementScreen extends JPanel {
    private GameController _controller;
    private Game _model;
    private NavigationController _navigationController;

    public PlacementScreen(GameController controller, Game model, NavigationController navigationController) {
        _controller = controller;
        _model = model;
        _navigationController = navigationController;
        setLayout(new BorderLayout());

        //titre
        add(new TitleBanner(), BorderLayout.NORTH);

        //panel du content principal
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());

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
        content.add(new GridPanel(_model.getGridSize()));

        //bateaux à drag n drop
        JPanel shipPanel = new JPanel();
        shipPanel.setLayout(new BoxLayout(shipPanel, BoxLayout.Y_AXIS));

        content.add(shipPanel);

        add(content, BorderLayout.CENTER);

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
        _navigationController.showGame();
    }
}

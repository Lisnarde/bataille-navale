package view;

import controller.GameController;
import controller.NavigationController;
import model.Game;
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

        //grille de placement


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

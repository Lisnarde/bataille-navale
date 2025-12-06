package view;

import view.components.TitleBanner;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

public class PlacementScreen extends JPanel {
    private GraphicalView _parent;

    public PlacementScreen(GraphicalView parent) {
        _parent = parent;
        setLayout(new BorderLayout());

        //titre
        add(new TitleBanner(), BorderLayout.NORTH);

        

        //bouton suivant
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        Button next = new Button("suivant");
        next.setPreferredSize(new Dimension(120, 40));
        next.setFont(new Font("Arial", Font.BOLD, 16));
        next.setBackground(new Color(107, 97, 210));
        next.setForeground(Color.WHITE);

        next.addActionListener(e -> {
            /*
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

            _parent.configureGame(pseudo, gridSize, isIslandMode);

             */

            _parent.showGameScreen();
        });

        buttonPanel.add(next);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}

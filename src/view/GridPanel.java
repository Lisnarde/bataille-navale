package view;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GridPanel extends JPanel implements GridObserver {

    private List<List<JButton>> _grid;

    public GridPanel(int gridSize) {

        _grid = new ArrayList<>();

        // +1 pour ajouter la ligne et colonne des labels
        setLayout(new GridLayout(gridSize + 1, gridSize + 1));

        // Première case vide (coin haut-gauche)
        add(new JLabel(""));

        // --- Ligne des colonnes : A, B, C, ...
        for (int col = 0; col < gridSize; col++) {
            JLabel label = new JLabel(String.valueOf((char) ('A' + col)), SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 16));
            add(label);
        }

        // --- Grille principale
        for (int row = 0; row < gridSize; row++) {

            // Label de ligne (1, 2, 3, ...)
            JLabel rowLabel = new JLabel(String.valueOf(row + 1), SwingConstants.CENTER);
            rowLabel.setFont(new Font("Arial", Font.BOLD, 16));
            add(rowLabel);

            List<JButton> rowButtons = new ArrayList<>();

            for (int col = 0; col < gridSize; col++) {

                JButton button = new JButton();
                button.setBackground(new Color(50, 150, 200));
                button.setOpaque(true);
                button.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                // Hover effect
                button.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseEntered(java.awt.event.MouseEvent e) {
                        button.setBackground(new Color(0, 200, 225));
                    }

                    @Override
                    public void mouseExited(java.awt.event.MouseEvent e) {
                        button.setBackground(new Color(50, 150, 200));
                    }
                });

                rowButtons.add(button);
                add(button);
            }

            _grid.add(rowButtons);
        }
    }



    @Override
    public void updateShoot(int joueur, int posx, int posy, boolean hit) {
        /*_grid.get(posy).get(posx).dis;
        if (hit) {
            rouge
        } else {
            autre couleur
        }*/
    }

    @Override
    public void updateTrapActivated(int joueur, int posx, int posy) {

    }
    @Override
    public void updateSearch(int joueur, int posx, int posy, PlaceableTypes objectFound){

    }
}
